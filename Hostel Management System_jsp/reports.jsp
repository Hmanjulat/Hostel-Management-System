<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reports</title>
</head>
<body>
    <h2>Select Report Type</h2>
    <ul>
        <!-- Direct report: Students with Pending Fees -->
        <li>
            <form action="generateReport" method="post" style="display:inline;">
                <input type="hidden" name="reportType" value="pendingFees">
                <input type="submit" value="Students with Pending Fees">
            </form>
        </li>

        <!-- Reports needing input -->
        <li><a href="report_form.jsp?type=room">Students by Room</a></li>
        <li><a href="report_form.jsp?type=date">Students by Admission Date Range</a></li>
    </ul>
</body>
</html>
