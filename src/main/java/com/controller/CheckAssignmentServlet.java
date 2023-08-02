package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.StudentSubmission;


@WebServlet("/checkAssignment")
public class CheckAssignmentServlet extends HttpServlet {
 private static final long serialVersionUID = 1L;
 String url = "jdbc:mysql://localhost:3306/codenera";
	String dbUsername = "root";
	String dbPassword = "ieytsak7eq@123";
 protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
     int batchId = Integer.parseInt(request.getParameter("batchId"));

     List<StudentSubmission> studentSubmissions = new ArrayList<>();
     try {
         Class.forName("com.mysql.jdbc.Driver");
         Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

    	 // for students whose "student_id" is in the "alumni" table for the given batch ID
         String query = "SELECT u.fullname, s.submission_id, s.status, s.submission_date, s.done, s.marks, a2.due_date " +
                 "FROM user u " +
                 "JOIN alumni a ON u.id = a.student_id " +
                 "LEFT JOIN submission s ON u.id = s.std_id AND s.assignment_id = ? " +
                 "LEFT JOIN assignments a2 ON s.assignment_id = a2.assignment_id " +
                 "WHERE a.batch_id = ?";

         
         PreparedStatement statement = connection.prepareStatement(query);
         statement.setInt(1, assignmentId);
         statement.setInt(2, batchId);
         ResultSet resultSet = statement.executeQuery();

         while (resultSet.next()) {
        	 int submission_id = resultSet.getInt("submission_id");
             String studentName = resultSet.getString("fullname");
             String status = resultSet.getString("status");
             String submissionDate = resultSet.getString("submission_date");
             String dueDate = resultSet.getString("due_date");

             String done = resultSet.getString("done");
             // Handle NULL value for "marks" column
             int marks = resultSet.getInt("marks");
             
             if (marks == 0) {
                 marks = 0;
             }

             // Handle NULL values from the result set for other fields
             if (studentName == null) {
                 studentName = "N/A";
             }
             if (status == null) {
                 status = "Not Submitted";
             }
             if (submissionDate == null) {
                 submissionDate = "N/A";
             }
             if (done == null) {
                 done = "N/A";
             }

             StudentSubmission submission = new StudentSubmission(studentName, status, submissionDate, done, marks,submission_id,dueDate);
             studentSubmissions.add(submission);
         }
     } catch (SQLException | ClassNotFoundException e) {
         e.printStackTrace();
     }

     // Set the list of student submissions as a request attribute
     request.setAttribute("studentSubmissions", studentSubmissions);

     // Forward the request to the JSP page to display the results
     request.getRequestDispatcher("SubmissionDetails.jsp").forward(request, response);
 }
}


