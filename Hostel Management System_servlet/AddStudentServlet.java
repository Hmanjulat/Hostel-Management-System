package servlet;



import dao.HostelDAO;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {

    private HostelDAO hostelDAO;

    @Override
    public void init() throws ServletException {
        hostelDAO = new HostelDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int studentID = Integer.parseInt(request.getParameter("studentID"));
            String studentName = request.getParameter("studentName");
            String roomNumber = request.getParameter("roomNumber");
            String admissionDate = request.getParameter("admissionDate");
            double feesPaid = Double.parseDouble(request.getParameter("feesPaid"));
            double pendingFees = Double.parseDouble(request.getParameter("pendingFees"));

            // Input validation
            if (studentName == null || studentName.trim().isEmpty() || roomNumber == null || roomNumber.trim().isEmpty()) {
                request.setAttribute("message", "All fields are required!");
                request.getRequestDispatcher("studentadd.jsp").forward(request, response);
                return;
            }

            Student student = new Student(studentID, studentName, roomNumber, admissionDate, feesPaid, pendingFees);
            hostelDAO.addStudent(student);

            request.setAttribute("message", "Student added successfully!");
            request.getRequestDispatcher("studentadd.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid input format!");
            request.getRequestDispatcher("studentadd.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }
    }
}

