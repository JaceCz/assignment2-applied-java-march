package com.example.java3servletsjace;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents an author from the "authors" table in the Books database.
 * Each author object has a list of the books they have written.
 */
public class Author {
    private int authorID;
    private String firstName;
    private String lastName;
    private List<Book> bookList;
    /**
     * Constructs an Author object with the specified author ID, first name, and last name.
     *
     * @param authorID the ID of the author
     * @param firstName the first name of the author
     * @param lastName the last name of the author
     */
    public Author(int authorID, String firstName, String lastName) {
        this.authorID = authorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookList = new ArrayList<>();
    }
    /**
     * Returns the ID of the author.
     *
     * @return the ID of the author
     */
    public int getAuthorID() {
        return authorID;
    }
    /**
     * Sets the ID of the author.
     *
     * @param authorID the ID to set
     */
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }
    /**
     * Returns the first name of the author.
     *
     * @return the first name of the author
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets the first name of the author.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Returns the last name of the author.
     *
     * @return the last name of the author
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the last name of the author.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Returns the list of books written by the author.
     *
     * @return the list of books written by the author
     */
    public List<Book> getBookList() {
        return bookList;
    }
    /**
     * Sets the list of books written by the author.
     *
     * @param bookList the list of books to set
     */
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
    /**
     * Adds a book to the list of books written by the author.
     *
     * @param book the book to add
     */
    public void addBook(Book book) {
        bookList.add(book);
    }
}