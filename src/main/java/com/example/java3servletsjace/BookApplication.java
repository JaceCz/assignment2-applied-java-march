package com.example.java3servletsjace;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class BookApplication {
    public static void main(String[] args) {
        // Database connection parameters
       /* String url = "jdbc:mariadb://localhost:3308/books?user=root&password=root1";
        String username = "root";
        String password = "root1"; **/
        final String DB_URL = "jdbc:mariadb://localhost:3308";

        //?user=root&password=RootPwJoSh2024
        final String USER = "root";
        final String PASS = "root1";
        BookDatabaseManager DBManager = new BookDatabaseManager(DB_URL,
                USER, PASS);
        DBManager.connect();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select an option for the Books database:");
            System.out.println("1. Print all the books from the database");
            System.out.println("2. Print all the authors from the database");
            System.out.println("3. Add a book to the database for an existing author");
            System.out.println("4. Add a new author");
            System.out.println("5. Quit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    printAllBooks(DBManager);
                    break;
                case 2:
                    printAllAuthors(DBManager);
                    break;
                case 3:
                    addBookForExistingAuthor(scanner, DBManager);
                    break;
                case 4:
                    addNewAuthor(scanner, DBManager);
                    break;
                case 5:
                    System.out.println("Have a Great Day!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid, Please select a valid option.");
            }
        }
    }
    private static void printAllBooks(BookDatabaseManager DBManager) {
        List<Book> books = DBManager.getAllBooks();
        books.get(0)
                .getAuthorList().get(0).setBookList(new ArrayList<>());
        for (Book book : books) {
            System.out.println("Title: " + book.getTitle());
            System.out.println();
        }
    }
    private static void printAllAuthors(BookDatabaseManager DBManager) {
        List<Author> authors = DBManager.getAllAuthors();
        for (Author author : authors) {
            System.out.println("Authors: " + author.getFirstName() + " " + author.getLastName());
            System.out.println();
        }
    }
    private static void addBookForExistingAuthor(Scanner scanner, BookDatabaseManager
            DBManager) {
        System.out.println("Enter the ISBN:");
        String isbn = scanner.nextLine();
        System.out.println("Enter the title:");
        String title = scanner.nextLine();
        System.out.println("Enter the edition number:");
        int editionNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.println("Enter the copyright:");
        String copyright = scanner.nextLine();
        System.out.println("Enter the author ID of the existing author:");
        int authorID = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        Book book = new Book(isbn, title, editionNumber, copyright);
        Author author = new Author(authorID, "", ""); // Dummy author with only ID
        book.addAuthor(author);
        DBManager.addBookForExistingAuthor(book);
        System.out.println("Book added successfully.");
    }
    private static void addNewAuthor(Scanner scanner, BookDatabaseManager
            DBManager) {
        System.out.println("Enter the first name of the new author:");
        String firstName = scanner.nextLine();
        System.out.println("Enter the last name of the new author:");
        String lastName = scanner.nextLine();
        Author author = new Author(0, firstName, lastName); // ID will be assigned by the database
        DBManager.addNewAuthor(author);
        System.out.println("New author added successfully.");
    }
}
