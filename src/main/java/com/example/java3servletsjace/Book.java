package com.example.java3servletsjace;

import java.util.ArrayList;
import java.util.List;
/**
 * Represents a book from the "titles" table in the Books database.
 * Each book object has a list of its authors.
 */
public class Book {
    private String isbn;
    private String title;
    private int editionNumber;
    private String copyright;
    private List<Author> authorList;
    /**
     * Constructs a Book object with the specified ISBN, title, edition number, and copyright.
     *
     * @param isbn the ISBN of the book
     * @param title the title of the book
     * @param editionNumber the edition number of the book
     * @param copyright the copyright of the book
     */
    public Book(String isbn, String title, int editionNumber, String copyright) {
        this.isbn = isbn;
        this.title = title;
        this.editionNumber = editionNumber;
        this.copyright = copyright;
        this.authorList = new ArrayList<>();
    }
    /**
     * Returns the ISBN of the book.
     *
     * @return the ISBN of the book
     */
    public String getIsbn() {
        return isbn;
    }
    /**
     * Sets the ISBN of the book.
     *
     * @param isbn the ISBN to set
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    /**
     * Returns the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the title of the book.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Returns the edition number of the book.
     *
     * @return the edition number of the book
     */
    public int getEditionNumber() {
        return editionNumber;
    }
    /**
     * Sets the edition number of the book.
     *
     * @param editionNumber the edition number to set
     */
    public void setEditionNumber(int editionNumber) {
        this.editionNumber = editionNumber;
    }
    /**
     * Returns the copyright of the book.
     *
     * @return the copyright of the book
     */
    public String getCopyright() {
        return copyright;
    }
    /**
     * Sets the copyright of the book.
     *
     * @param copyright the copyright to set
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
    /**
     * Returns the list of authors of the book.
     *
     * @return the list of authors of the book
     */
    public List<Author> getAuthorList() {
        return authorList;
    }
    /**
     * Sets the list of authors of the book.
     *
     * @param authorList the list of authors to set
     */
    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }
    /**
     * Adds an author to the list of authors of the book.
     *
     * @param author the author to add
     */
    public void addAuthor(Author author) {
        authorList.add(author);
    }
}
