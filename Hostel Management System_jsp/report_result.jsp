<%@ page language="java" import="java.util.*,model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Report Results</title>
</head>
<body>
    <h2>Report Results</h2>

    <%
        List<Student> students = (List<Student>) request.getAttribute("students");
        if (students == null || students.isEmpty()) {
    %>
        <p>No records found for this report.</p>
    <%
        } else {
    %>
        <table border="1">
            <tr>
                <th>ID</th><th>Name</th><th>Room</th><th>Admission Date</th><th>Fees Paid</th><th>Pending Fees</th>
            </tr>
            <%
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
    <%
        }
    %>
</body>
</html>
