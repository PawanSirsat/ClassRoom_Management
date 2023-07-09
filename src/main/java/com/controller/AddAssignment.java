package com.controller;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)
@WebServlet("/addAssignment")
public class AddAssignment extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	// Database connection parameters
	String url = "jdbc:mysql://localhost:3306/codenera";
	String dbUsername = "root";
	String dbPassword = "ieytsak7eq@123";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		String dueDate = request.getParameter("dueDate");
		String BatchId = request.getParameter("batchid");
		int BatchIdInt = Integer.parseInt(BatchId);

		HttpSession session = request.getSession();

		// Retrieve the faculty ID from the session
		int loggedInFacultyId = (int) session.getAttribute("facultyId");

		Part pdfFilePart = request.getPart("pdfFile");
		byte[] pdfContent = null;

		if (pdfFilePart != null)
		{
			InputStream inputStream = pdfFilePart.getInputStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			// Read the PDF file content into a byte array
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1)
			{
				outputStream.write(buffer, 0, bytesRead);
			}

			pdfContent = outputStream.toByteArray();
			outputStream.close();
			inputStream.close();
		}

		Connection conn = null;
		PreparedStatement stmt = null;

		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(url, dbUsername, dbPassword);

			String sql = "INSERT INTO assignments (title, description, due_date, faculty_id, pdf_data,batch_id) VALUES (?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, description);
			stmt.setString(3, dueDate);
			stmt.setInt(4, loggedInFacultyId);
			stmt.setBytes(5, pdfContent);
			stmt.setInt(6, BatchIdInt);

			int rowsUpdated = stmt.executeUpdate();

			if (rowsUpdated == 1)
			{
				response.sendRedirect("FacultyHomePage.jsp");
			}
		} catch (SQLException | ClassNotFoundException e)
		{
			System.out.println(e);
			response.sendRedirect("CreateAssignment.jsp");
		}
	}
}
