package com.example.java3servletsjace;



/**
 * Store all the MariaDB connection properties here.
 */
public class BooksDBProperties {

    static final String DB_URL = "jdbc:mariadb://localhost:3308";

    //?user=root&password=RootPwJoSh2024
    static final String USER = "root";
    static final String PASS = "root1";

    //Specific test database URL
    static final String JAVA_BOOKS_DB_URL = "jdbc:mariadb://localhost:3308/books?user=root&password=root1";

    static final String QUERY_ALL_AUTHORS = "SELECT * FROM authors;";
    static final String QUERY_ALL_TITLES = "SELECT * FROM titles;";

}
