<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Book</title>
</head>
<body>
<h2>Add New Book</h2>
<form action="LibraryData" method="post">
    <input type="hidden" name="action" value="book">
    <label for="isbn">ISBN:</label>
    <input type="text" id="isbn" name="isbn" required><br>
    <label for="title">Title:</label>
    <input type="text" id="title" name="title" required><br>
    <label for="editionNumber">Edition Number:</label>
    <input type="number" id="editionNumber" name="editionNumber" required><br>
    <label for="copyright">Copyright:</label>
    <input type="text" id="copyright" name="copyright" required><br>
    <label for="authorFirstName">Author First Name:</label>
    <input type="text" id="authorFirstName" name="authorFirstName" required><br>
    <label for="authorLastName">Author Last Name:</label>
    <input type="text" id="authorLastName" name="authorLastName" required><br>
    <button type="submit">Add Book</button>
</form>
<br>
<a href="index.jsp">Back to Home</a>
</body>
</html>
