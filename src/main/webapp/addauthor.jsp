<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Author</title>
</head>
<body>
<h2>Add New Author</h2>
<form action="LibraryData" method="post">
    <input type="hidden" name="action" value="author">
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required><br>
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br>
    <button type="submit">Add Author</button>
</form>
<br>
<a href="index.jsp">Back to Home</a>
</body>
</html>

