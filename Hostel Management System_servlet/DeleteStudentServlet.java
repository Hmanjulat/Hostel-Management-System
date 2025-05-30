/*
 * package servlet;
 * 
 * 
 * 
 * import dao.HostelDAO;
 * 
 * import javax.servlet.ServletException; import
 * javax.servlet.annotation.WebServlet; import javax.servlet.http.*; import
 * java.io.IOException; import java.sql.SQLException;
 * 
 * @WebServlet("/deleteStudent") public class DeleteStudentServlet extends
 * HttpServlet { private HostelDAO hostelDAO;
 * 
 * @Override public void init() throws ServletException { hostelDAO = new
 * HostelDAO(); }
 * 
 * @Override protected void doPost(HttpServletRequest request,
 * HttpServletResponse response) throws ServletException, IOException {
 * 
 * try { int studentID = Integer.parseInt(request.getParameter("studentID"));
 * boolean deleted = HostelDAO.deleteStudent(studentID);
 * 
 * if (deleted) { request.setAttribute("message",
 * "Student deleted successfully!"); } else { request.setAttribute("message",
 * "Student not found."); }
 * 
 * request.getRequestDispatcher("studentdelete.jsp").forward(request, response);
 * 
 * } catch (NumberFormatException e) { request.setAttribute("message",
 * "Invalid student ID format.");
 * request.getRequestDispatcher("studentdelete.jsp").forward(request, response);
 * } catch (SQLException e) { request.setAttribute("message", "Database error: "
 * + e.getMessage());
 * request.getRequestDispatcher("studentdelete.jsp").forward(request, response);
 * } } }
 * 
 */
package servlet;

import dao.HostelDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteStudent")
public class DeleteStudentServlet extends HttpServlet {
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

            // Check if student exists before deleting
            if (hostelDAO.getStudentById(studentID) != null) {
                hostelDAO.deleteStudent(studentID);
                request.setAttribute("message", "Student deleted successfully!");
            } else {
                request.setAttribute("message", "Student not found.");
            }

            request.getRequestDispatcher("studentdelete.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("message", "Invalid student ID format.");
            request.getRequestDispatcher("studentdelete.jsp").forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("message", "Database error: " + e.getMessage());
            request.getRequestDispatcher("studentdelete.jsp").forward(request, response);
        }
    }
}
