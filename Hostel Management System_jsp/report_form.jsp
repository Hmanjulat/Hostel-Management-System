<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Report Form</title>
</head>
<body>

<%
    String type = request.getParameter("type");
    if (type == null) {
%>
    <p style="color:red;">Error: Missing report type!</p>
    <a href="reports.jsp">Go back</a>
<%
    } else {
%>

    <h2>Report: <%= "room".equals(type) ? "Students by Room" : "Admission Date Range" %></h2>

    <form action="generateReport" method="post">
        <input type="hidden" name="reportType" value="<%= "room".equals(type) ? "roomWise" : "dateRange" %>">

        <% if ("room".equals(type)) { %>
            Room Number: <input type="text" name="roomNumber" required><br><br>
        <% } else { %>
            From: <input type="date" name="startDate" required><br><br>
            To: <input type="date" name="endDate" required><br><br>
        <% } %>

        <input type="submit" value="Generate Report">
    </form>

<% } %>

</body>
</html>
