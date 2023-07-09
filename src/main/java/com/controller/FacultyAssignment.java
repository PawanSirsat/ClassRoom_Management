package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/facultyAssignment")
public class FacultyAssignment extends HttpServlet
{
	String url = "jdbc:mysql://localhost:3306/codenera";
	String dbUsername = "root";
	String dbPassword = "ieytsak7eq@123";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int loggedInFacultyId = (int) request.getSession().getAttribute("facultyId");

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) 
        {
        	String query1 = "SELECT a.*, f.faculty_name, b.batch_name " +
                    "FROM assignments a " +
                    "JOIN faculty f ON a.faculty_id = f.faculty_id " +
                    "JOIN batch b ON a.batch_id = b.batch_id " +
                    "WHERE a.faculty_id = ?";

            String query = "SELECT * FROM assignments WHERE faculty_id = ?";
            PreparedStatement statement = connection.prepareStatement(query1);
            	statement.setInt(1, loggedInFacultyId);
                 ResultSet assignments = statement.executeQuery();
                 
                 HttpSession session = request.getSession();
                 session.setAttribute("assignments", assignments);
                 
                request.setAttribute("assignments", assignments);
                request.getRequestDispatcher("FacultyAssignment.jsp").forward(request, response);
            
        } catch (SQLException e) 
        {
            e.printStackTrace();
        }
    }
}
