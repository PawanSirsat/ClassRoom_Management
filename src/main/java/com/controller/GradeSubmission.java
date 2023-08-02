package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Grade")
public class GradeSubmission extends HttpServlet
{
	String url = "jdbc:mysql://localhost:3306/codenera";
	String dbUsername = "root";
	String dbPassword = "ieytsak7eq@123";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Retrieve the submission ID and marks from the request parameters
		String submissionId = request.getParameter("submissionId");
		String marks = request.getParameter("marks");

		 try {
	            // Convert the marks parameter to an integer
	            int marksValue = Integer.parseInt(marks);

	            // Call the method to update marks in the database
	            boolean success = updateMarks(submissionId, marksValue);

	            if (success) {
	                // Return a success response to the client
	                PrintWriter out = response.getWriter();
	                out.println("Marks inserted successfully!");
	                request.getRequestDispatcher("FacultyHomePage.jsp").forward(request, response);

	            } else {
	                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inserting marks.");
	            }
	        } catch (NumberFormatException e) {
	            e.printStackTrace();
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid marks value.");
	        }
	    }

	    // Method to update marks for a given submission ID in the database
	private boolean updateMarks(String submissionId, int marks) {
	    try {
	         Class.forName("com.mysql.jdbc.Driver");
	         Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

	        String qry1 ="UPDATE submission SET marks = ? WHERE submission_id = ?";
	         PreparedStatement statement = connection.prepareStatement(qry1);

	         statement.setInt(1, marks);
	         statement.setString(2, submissionId);
	        int rowsAffected = statement.executeUpdate();


	        return rowsAffected > 0; // Returns true if the insertion was successful
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	}
