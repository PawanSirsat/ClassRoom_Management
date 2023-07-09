package com.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/download")
public class DownloadFile extends HttpServlet {
	String url = "jdbc:mysql://localhost:3306/codenera";
	String dbUsername = "root";
	String dbPassword = "ieytsak7eq@123";
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int assignmentId = Integer.parseInt(request.getParameter("assignmentId"));

        HttpSession session = request.getSession();

		String token = (String) session.getAttribute("jwtToken");

		if (token != null && AuthenticationPoint.validateToken(token))
		{
			 try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword)) {
		            String query = "SELECT * FROM assignments WHERE assignment_id = ?";
		            PreparedStatement statement = connection.prepareStatement(query);
		            statement.setInt(1, assignmentId);
		            ResultSet resultSet = statement.executeQuery();
		            if (resultSet.next()) {
		                String fileName = resultSet.getString("title");
		                InputStream fileContent = resultSet.getBinaryStream("pdf_data");

		                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

		                OutputStream outputStream = response.getOutputStream();
		                byte[] buffer = new byte[4096];
		                int bytesRead;
		                while ((bytesRead = fileContent.read(buffer)) != -1) {
		                    outputStream.write(buffer, 0, bytesRead);
		                }
		                fileContent.close();
		                outputStream.close();
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		            // Handle database error
		        }
	
		} else
		{
			session.invalidate();
			response.sendRedirect("Studentlogin.html");
		}
       
    }
}

