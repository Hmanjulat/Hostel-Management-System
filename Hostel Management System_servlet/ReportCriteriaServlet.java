package servlet;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/reportCriteria")
public class ReportCriteriaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reportType = request.getParameter("reportType");

        switch (reportType) {
            case "pendingFees":
                request.setAttribute("reportType", "pendingFees");
                request.getRequestDispatcher("report_result.jsp").forward(request, response);
                break;

            case "roomWise":
                request.setAttribute("reportType", "roomWise");
                request.getRequestDispatcher("report_form.jsp").forward(request, response);
                break;

            case "dateRange":
                request.setAttribute("reportType", "dateRange");
                request.getRequestDispatcher("report_form.jsp").forward(request, response);
                break;

            default:
                request.setAttribute("message", "Invalid report type selected.");
                request.getRequestDispatcher("reports.jsp").forward(request, response);
        }
    }
}
