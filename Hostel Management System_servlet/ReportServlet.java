package servlet;



import dao.HostelDAO;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/generateReport")
public class ReportServlet extends HttpServlet {

    private HostelDAO hostelDAO;

    @Override
    public void init() throws ServletException {
        hostelDAO = new HostelDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reportType = request.getParameter("reportType");
        List<Student> students = null;

        try {
            switch (reportType) {
                case "pendingFees":
                    students = hostelDAO.getStudentsWithPendingFees();
                    break;

                case "roomWise":
                    String roomNumber = request.getParameter("roomNumber");
                    students = hostelDAO.getStudentsByRoom(roomNumber);
                    break;

                case "dateRange":
                    String startDate = request.getParameter("startDate");
                    String endDate = request.getParameter("endDate");
                    students = hostelDAO.getStudentsByDateRange(startDate, endDate);
                    break;

                default:
                    request.setAttribute("message", "Invalid report type.");
                    request.getRequestDispatcher("report_result.jsp").forward(request, response);
                    return;
            }

            request.setAttribute("students", students);
            request.setAttribute("reportType", reportType);
            request.getRequestDispatcher("report_result.jsp").forward(request, response);

        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }
    }
}

