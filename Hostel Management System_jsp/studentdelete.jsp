<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Student</title>
</head>
<body>
    <h2>Delete Student</h2>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            String color = message.toLowerCase().contains("not found") ? "red" : "green";
    %>
        <p style="color:<%= color %>;"><%= message %></p>
    <%
        }
    %>

    <form action="deleteStudent" method="post">
        Enter Student ID to Delete: <input type="number" name="studentID" required><br>
        <input type="submit" value="Delete Student">
    </form>
</body>
</html>
