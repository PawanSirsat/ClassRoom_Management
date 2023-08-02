package com.controller;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/FileUploadServlet")
@MultipartConfig
public class SubmitAssignment extends HttpServlet {
    private static final long serialVersionUID = 1L;

    String url = "jdbc:mysql://localhost:3306/codenera";
	String user = "root";
	String password = "ieytsak7eq@123";
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve data from the form
		int loggedInStudentId = (int) request.getSession().getAttribute("studentId");

        int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));
        Part filePart = request.getPart("pdfFile");
        InputStream fileContent = filePart.getInputStream();

        // Get the current date and time as the submission date
        Date submissionDate = new Date();

        try
		{
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

        // Get the due date for the assignment from the database
        Date dueDate = null;
        String status = "Turned in";
        String done = null;
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "SELECT due_date FROM assignments WHERE assignment_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, assignmentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                dueDate = resultSet.getDate("due_date");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Determine the status based on submission date and due date
        if (dueDate != null) {
            if (submissionDate.after(dueDate)) {
                done = "Done Late";
            } else {
                done = "On Time";
            }
        }

     
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Insert the data into the "submission" table
            String query = "INSERT INTO submission (assignment_id, std_id, status, file, submission_date, done) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, assignmentId);
            statement.setInt(2, loggedInStudentId);
            statement.setString(3, status != null ? status : "Missing");
            statement.setBlob(4, fileContent);
            statement.setTimestamp(5, new java.sql.Timestamp(submissionDate.getTime()));
            statement.setString(6, done);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Redirect back to the assignments.jsp page
        System.out.println("Before redirect");
        response.sendRedirect(request.getContextPath() + "/showAssignmentStd");
        System.out.println("After redirect");
    }
}
