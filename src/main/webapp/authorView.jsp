<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
  </tr>
  <c:forEach var="author" items="${authors}">
    <tr>
      <td>${author.firstName}</td>
      <td>${author.lastName}</td>
    </tr>
  </c:forEach>
</table>
<br/>
<a href="index.jsp">Back to Main Page</a>
</body>
</html>
