package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pojo.Student;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/showBatch")
public class ShowBatch extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	// JDBC connection parameters

	String url = "jdbc:mysql://localhost:3306/codenera";
	String user = "root";
	String password = "ieytsak7eq@123";
	String batchTeacher;
	String batchYear;
	String batchName;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String batchId = request.getParameter("batchid");
		int batchIdInt = Integer.parseInt(batchId);
		List<Student> userList = new ArrayList<>();

		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("jwtToken");
		if (token != null && AuthenticationPoint.validateToken(token))
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conn = DriverManager.getConnection(url, user, password);

				String query = "SELECT u.* FROM user u JOIN alumni a ON u.id = a.student_id WHERE a.batch_id = ?";

				PreparedStatement statement = conn.prepareStatement(query);
				statement.setInt(1, batchIdInt);

				ResultSet resultSet = statement.executeQuery();

				while (resultSet.next())
				{
					int id = resultSet.getInt("id");
					String username = resultSet.getString("username");
					String email = resultSet.getString("email");
					String password = resultSet.getString("password");
					int mobile = resultSet.getInt("phone");
					String city = resultSet.getString("city");
					int age = resultSet.getInt("age");
					String course = resultSet.getString("course");
					byte[] imageBytes = resultSet.getBytes("img");

					String imagePath = null;
					if (imageBytes != null && imageBytes.length > 0)
					{
						// Convert imageBytes to Base64-encoded string
						String base64Image = Base64.getEncoder().encodeToString(imageBytes);

						// Construct the image URL
						imagePath = "data:image/jpeg;base64," + base64Image;
					}
					Student user = new Student(id, username, email, password, mobile, city, age, course, imagePath);
					userList.add(user);
				}

				String sql1 = "SELECT  b.batch_name, b.batch_year, b.batch_course, f.faculty_name,f.faculty_id "
						+ "FROM alumni a " + "JOIN batch b ON a.batch_id = b.batch_id "
						+ "JOIN faculty f ON b.faculty_id = f.faculty_id " + "WHERE b.batch_id = ?";

				String batchQuery = "SELECT * FROM batch WHERE batch_id = ?";
				PreparedStatement batchStatement = conn.prepareStatement(sql1);
				batchStatement.setInt(1, batchIdInt); // Set the batch ID parameter in the batch query

				ResultSet batchResultSet = batchStatement.executeQuery();

				if (batchResultSet.next())
				{
					batchName = batchResultSet.getString("batch_name");
					batchYear = batchResultSet.getString("batch_year");
					batchTeacher = batchResultSet.getString("faculty_name");
				}

				// Close the resources
				resultSet.close();
				statement.close();
				conn.close();
			} catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}
			session.setAttribute("userList", userList);
			session.setAttribute("batchname", batchName);
			session.setAttribute("batchyear", batchYear);
			session.setAttribute("batchteacher", batchTeacher);

			request.setAttribute("userList", userList);
			request.getRequestDispatcher("ShowBatch.jsp").forward(request, response);
		} 
		else
		{
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
	}	
}
