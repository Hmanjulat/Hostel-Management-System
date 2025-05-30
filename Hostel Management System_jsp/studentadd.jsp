<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>
</head>
<body>
    <h2>Add Student</h2>

    <%-- Show message if present --%>
    <p style="color: red;"><%= request.getAttribute("message") != null ? request.getAttribute("message") : "" %></p>

    <form action="addStudent" method="post">
        Student ID: <input type="number" name="studentID" required><br>
        Name: <input type="text" name="studentName" required><br>
        Room Number: <input type="text" name="roomNumber" required><br>
        Admission Date: <input type="date" name="admissionDate" required><br>
        Fees Paid: <input type="number" step="0.01" name="feesPaid" required><br>
        Pending Fees: <input type="number" step="0.01" name="pendingFees" required><br>
        <input type="submit" value="Add Student">
    </form>
</body>
</html>
