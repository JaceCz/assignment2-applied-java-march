package com.example.java3servletsjace;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet implementation class LibraryData
 * This servlet handles requests related to managing books and authors.
 */
@WebServlet(name = "LibraryData", value = "/LibraryData")
public class LibraryData extends HttpServlet {
    private BookDatabaseManager databaseManager;

    /**
     * Initializes the servlet by loading the JDBC driver and connecting to the database.
     * @throws ServletException if an error occurs during initialization
     */
    @Override
    public void init() throws ServletException {
        super.init();
        try{
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Failed to load JDBC driver", e);
        }
        // Initialize the database manager
        String url = BooksDBProperties.DB_URL;
        String username = BooksDBProperties.USER;
        String password = BooksDBProperties.PASS;
        databaseManager = new BookDatabaseManager(url, username, password);
        databaseManager.connect();
    }

    /**
     * Handles HTTP GET requests.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     * @throws ServletException if an error occurs while processing the request
     * @throws IOException if an I/O error occurs while processing the request
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String view = request.getParameter("view");
        if ("books".equals(view)) {
            List<Book> books = databaseManager.getAllBooks();
            request.setAttribute("books", books);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/bookView.jsp");
            dispatcher.forward(request, response);
        } else if ("authors".equals(view)) {
            List<Author> authors = databaseManager.getAllAuthors();
            request.setAttribute("authors", authors);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/authorView.jsp");
            dispatcher.forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }

    /**
     * Handles HTTP POST requests.
     * @param request the HTTP servlet request
     * @param response the HTTP servlet response
     * @throws ServletException if an error occurs while processing the request
     * @throws IOException if an I/O error occurs while processing the request
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("book".equals(action)) {
            // Add a new book
            String isbn = request.getParameter("isbn");
            String title = request.getParameter("title");
            int editionNumber = Integer.parseInt(request.getParameter("editionNumber"));
            String copyright = request.getParameter("copyright");
            String authorFirstName = request.getParameter("authorFirstName");
            String authorLastName = request.getParameter("authorLastName");
            Author author = new Author(0, authorFirstName, authorLastName);
            Book book = new Book(isbn, title, editionNumber, copyright);
            book.addAuthor(author);
            databaseManager.addBookForExistingAuthor(book);
            PrintWriter out = response.getWriter();
            out.println("Book added successfully!");
        } else if ("author".equals(action)) {
            // Add a new author
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            Author author = new Author(0, firstName, lastName);
            databaseManager.addNewAuthor(author);
            PrintWriter out = response.getWriter();
            out.println("Author added successfully!");
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    /**
     * Cleans up resources and closes the database connection when the servlet is destroyed.
     */
    @Override
    public void destroy() {
        super.destroy();
        // Close the database connection
        databaseManager.disconnect();
    }
}
