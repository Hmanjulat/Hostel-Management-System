package servlet;



import dao.HostelDAO;
import model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/displayStudents")
public class DisplayStudentsServlet extends HttpServlet {

    private HostelDAO hostelDAO;

    @Override
    public void init() throws ServletException {
        hostelDAO = new HostelDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String studentIdParam = request.getParameter("studentID");
        try {
            List<Student> students;

            if (studentIdParam != null && !studentIdParam.trim().isEmpty()) {
                int studentID = Integer.parseInt(studentIdParam);
                Student student = hostelDAO.getStudentById(studentID);
                if (student != null) {
                    students = List.of(student);
                } else {
                    request.setAttribute("message", "Student not found!");
                    students = List.of();
                }
            } else {
                students = hostelDAO.getAllStudents();
            }

            request.setAttribute("students", students);
            request.getRequestDispatcher("studentdisplay.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid student ID!");
            request.getRequestDispatcher("studentdisplay.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Database error: " + e.getMessage(), e);
        }
    }
}

