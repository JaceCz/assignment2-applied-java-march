<%@ page import="com.example.java3servletsjace.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.java3servletsjace.Author" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book View</title>
</head>
<body>
<h2>Books View</h2>
<table border="1">
    <tr>
        <th>Title</th>
        <th>Authors</th>
    </tr>
    <% List<Book> books = (List<Book>) request.getAttribute("books"); %>
    <% for (int i = 0; i < books.size(); i++) { %>
    <tr>
        <td><%= books.get(i).getTitle() %></td>
        <td>
            <% List<Author> authors = books.get(i).getAuthorList(); %>
            <% for (int j = 0; j < authors.size(); j++) { %>
            <%= authors.get(j).getFirstName() %> <%= authors.get(j).getLastName() %><br/>
            <% } %>
        </td>
    </tr>
    <% } %>
</table>
<br/>
<a href="index.jsp">Back to Main Page</a>
</body>
</html>