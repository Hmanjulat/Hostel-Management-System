<%@ page language="java" import="java.util.*,model.Student,dao.HostelDAO" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Display Students</title>
</head>
<body>
    <h2>All Students</h2>
    <table border="1">
        <tr>
            <th>ID</th><th>Name</th><th>Room</th><th>Admission Date</th><th>Fees Paid</th><th>Pending Fees</th>
        </tr>
        <%
            HostelDAO dao = new HostelDAO();
            List<Student> students = dao.getAllStudents();
            for(Student s : students) {
        %>
        <tr>
            <td><%= s.getStudentID() %></td>
            <td><%= s.getStudentName() %></td>
            <td><%= s.getRoomNumber() %></td>
            <td><%= s.getAdmissionDate() %></td>
            <td><%= s.getFeesPaid() %></td>
            <td><%= s.getPendingFees() %></td>
        </tr>
        <% } %>
    </table>
</body>
</html>
