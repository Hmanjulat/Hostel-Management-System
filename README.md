 Hostel-Management-System

Project Purpose:
To manage hostel student admissions, room allocations, and fee payments. Functional modules include
adding student details, updating records, deleting students, and viewing current occupancy. Reports
include students with pending fees, students in a particular room, and students admitted in a specific
period.
Database Structure:
CREATE TABLE HostelStudents (
StudentID INT PRIMARY KEY,
StudentName VARCHAR(100),
RoomNumber VARCHAR(10),
AdmissionDate DATE,
FeesPaid DECIMAL(10,2),
PendingFees DECIMAL(10,2)
);


Modules & Functionalities:
StudentAdd: Register new students
StudentUpdate: Update student info
StudentDelete: Remove students
StudentDisplay: View all or individual student info
Reports:
Students with pending fees
Students in a particular room
Students admitted in a date range


Project Structure:
HostelWebApp/
├── WebContent/
│ ├── index.jsp
│ ├── studentadd.jsp
│ ├── studentupdate.jsp
│ ├── studentdelete.jsp
│ ├── studentdisplay.jsp
│ ├── reports.jsp
│ ├── report_form.jsp
│ └── report_result.jsp
├── src/
│ ├── com/
│ ├── dao/
│ │ └── HostelDAO.java
│ ├── model/
│ │ └── Student.java
│ └── servlet/
│ ├── AddStudentServlet.java
│ ├── UpdateStudentServlet.java
│ ├── DeleteStudentServlet.java
│ ├── DisplayStudentsServlet.java
│ ├── ReportServlet.java
│ └── ReportCriteriaServlet.java
└── WEB-INF/web.xml



 
