<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.java3servletsjace.Author" %>
<%@ page import="com.example.java3servletsjace.Book" %>
<!DOCTYPE html>
<html>
<head>
  <title>Author View</title>
</head>
<body>
<h2>Authors View</h2>
<table border="1">
  <tr>
    <th>First Name</th>
    <th>Last Name</th>
    <th>Books</th>
  </tr>
  <% List<Author> authors = (List<Author>) request.getAttribute("authors"); %>
  <% for (Author author : authors) { %>
  <tr>
    <td><%= author.getFirstName() %></td>
    <td><%= author.getLastName() %></td>
    <td>
      <% List<Book> books = author.getBookList(); %>
      <% for (Book book : books) { %>
      <%= book.getTitle() %><br/>
      <% } %>
    </td>
  </tr>
  <% } %>
</table>
<br/>
<a href="index.jsp">Back to Main Page</a>
</body>
</html>

