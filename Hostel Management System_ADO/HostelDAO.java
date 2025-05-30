package dao;



import java.sql.*;
import java.sql.Date;
import java.util.*;

import model.Student;

public class HostelDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/hostel management";
    private String jdbcUsername = "root";
    private String jdbcPassword = ""; // Change this

    private static final String INSERT_SQL = "INSERT INTO HostelStudents VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE HostelStudents SET StudentName=?, RoomNumber=?, AdmissionDate=?, FeesPaid=?, PendingFees=? WHERE StudentID=?";
    private static final String DELETE_SQL = "DELETE FROM HostelStudents WHERE StudentID=?";
    private static final String SELECT_ALL = "SELECT * FROM HostelStudents";
    private static final String SELECT_BY_ID = "SELECT * FROM HostelStudents WHERE StudentID=?";
    private static final String SELECT_PENDING_FEES = "SELECT * FROM HostelStudents WHERE PendingFees > 0";
    private static final String SELECT_BY_ROOM = "SELECT * FROM HostelStudents WHERE RoomNumber=?";
    private static final String SELECT_BY_DATE_RANGE = "SELECT * FROM HostelStudents WHERE AdmissionDate BETWEEN ? AND ?";

    public HostelDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }

    // Add Student
    public void addStudent(Student student) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {
            stmt.setInt(1, student.getStudentID());
            stmt.setString(2, student.getStudentName());
            stmt.setString(3, student.getRoomNumber());
            stmt.setDate(4, Date.valueOf(student.getAdmissionDate()));
            stmt.setDouble(5, student.getFeesPaid());
            stmt.setDouble(6, student.getPendingFees());
            stmt.executeUpdate();
        }
    }

    // Update Student
    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE HostelStudents SET StudentName=?, RoomNumber=?, AdmissionDate=?, FeesPaid=?, PendingFees=? WHERE StudentID=?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getStudentName());
            stmt.setString(2, student.getRoomNumber());
            stmt.setDate(3, java.sql.Date.valueOf(student.getAdmissionDate()));
            stmt.setDouble(4, student.getFeesPaid());
            stmt.setDouble(5, student.getPendingFees());
            stmt.setInt(6, student.getStudentID());

            return stmt.executeUpdate() > 0;
        }
    }


    // Delete Student
    public void deleteStudent(int studentID) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, studentID);
            stmt.executeUpdate();
        }
    }

    // Get Student by ID
    public Student getStudentById(int id) throws SQLException {
        Student student = null;
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                student = extractStudent(rs);
            }
        }
        return student;
    }

    // Get All Students
    public List<Student> getAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(extractStudent(rs));
            }
        }
        return students;
    }

    // Get Students with Pending Fees
    public List<Student> getStudentsWithPendingFees() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_PENDING_FEES)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(extractStudent(rs));
            }
        }
        return students;
    }

    // Get Students by Room
    public List<Student> getStudentsByRoom(String roomNumber) throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ROOM)) {
            stmt.setString(1, roomNumber);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(extractStudent(rs));
            }
        }
        return students;
    }

    // Get Students by Date Range
    public List<Student> getStudentsByDateRange(String startDate, String endDate) throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_DATE_RANGE)) {
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                students.add(extractStudent(rs));
            }
        }
        return students;
    }

    // Helper Method to Extract Student Object
    private Student extractStudent(ResultSet rs) throws SQLException {
        Student student = new Student();
        student.setStudentID(rs.getInt("StudentID"));
        student.setStudentName(rs.getString("StudentName"));
        student.setRoomNumber(rs.getString("RoomNumber"));
        student.setAdmissionDate(rs.getDate("AdmissionDate").toString());
        student.setFeesPaid(rs.getDouble("FeesPaid"));
        student.setPendingFees(rs.getDouble("PendingFees"));
        return student;
    }
}
