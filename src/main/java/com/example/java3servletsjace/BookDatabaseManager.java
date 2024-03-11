package com.example.java3servletsjace;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that manages database operations related to books and authors.
 */
public class BookDatabaseManager {
    private Connection connection;
    private String url;
    private String username;
    private String password;
    /**
     * Constructs a BookDatabaseManager with the specified URL, username, and password
     for the database.
     *
     * @param url the URL of the database
     * @param username the username for accessing the database
     * @param password the password for accessing the database
     */
    public BookDatabaseManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    /**
     * Establishes a connection to the database.
     */
    public void connect() {
        try {
            connection = DriverManager.getConnection(BooksDBProperties.JAVA_BOOKS_DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Closes the connection to the database.
     */
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Adds new author into the database into memory.
     */

    public void addNewAuthor(Author author) {
        try {
            String query = "INSERT INTO authors (firstName, lastName) VALUES(?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, author.getFirstName());
            statement.setString(2, author.getLastName());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Adds new books for existing authors into the database into memory.
     */
    public void addBookForExistingAuthor(Book book) {
        try {
            // Insert book into the titles table
            String insertBookQuery = "INSERT INTO titles (isbn, title, editionNumber, copyright) VALUES(?, ?, ?, ?)";
            PreparedStatement insertBooks = connection.prepareStatement(insertBookQuery);
            insertBooks.setString(1, book.getIsbn());
            insertBooks.setString(2, book.getTitle());
            insertBooks.setString(3, String.valueOf(book.getEditionNumber()));
            insertBooks.setString(4, book.getCopyright());
            insertBooks.executeUpdate();

            String insertAuthorISBNQuery = "INSERT INTO authorISBN (authorID, isbn) VALUES (?, ?)";
            PreparedStatement insertAuthorISBN = connection.prepareStatement(insertAuthorISBNQuery);
            for (Author author : book.getAuthorList()) {
                insertAuthorISBN.setInt(1, author.getAuthorID());
                insertAuthorISBN.setString(2, book.getIsbn());
                insertAuthorISBN.executeUpdate();
            }
            insertBooks.close();
            insertAuthorISBN.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    /**
     * Loads all authors from the database into memory.
     */
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM authors");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int authorID = resultSet.getInt("authorID");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Author author = new Author(authorID, firstName, lastName);
                authors.add(author);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authors;
    }
    /**
     * Loads all books from the database into memory.
     */
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        Map<String, Author> authorMap = new HashMap<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM titles");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String title = rs.getString("title");
                int editionNumber = rs.getInt("editionNumber");
                String copyright = rs.getString("copyright");
                Book book = new Book(isbn, title, editionNumber, copyright);
                books.add(book);
            }
            statement = connection.prepareStatement("SELECT a.* FROM authors a JOIN authorISBN ai ON a.authorID = ai.authorID WHERE ai.isbn = ?");
            for (Book book : books) {
                statement.setString(1, book.getIsbn());
                rs = statement.executeQuery();
                while (rs.next()) {
                    int authorID = rs.getInt("authorID");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    Author author = authorMap.get(authorID);
                    if (author == null) {
                        author = new Author(authorID, firstName, lastName);
                        authorMap.put(String.valueOf(authorID), author);
                    }
                    book.addAuthor(author);
                    author.addBook(book);
                }
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }
}