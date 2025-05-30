<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Student</title>
</head>
<body>
    <h2>Update Student Information</h2>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            String color = message.toLowerCase().contains("failed") ? "red" : "green";
    %>
        <p style="color:<%= color %>;"><%= message %></p>
    <%
        }
    %>

    <form action="updateStudent" method="post">
        Student ID (existing): <input type="number" name="studentID" required><br>
        Name: <input type="text" name="studentName" required><br>
        Room Number: <input type="text" name="roomNumber" required><br>
        Admission Date: <input type="date" name="admissionDate" required><br>
        Fees Paid: <input type="number" step="0.01" name="feesPaid" required><br>
        Pending Fees: <input type="number" step="0.01" name="pendingFees" required><br>
        <input type="submit" value="Update Student">
    </form>
</body>
</html>
