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
import javax.servlet.http.HttpSession;

import com.dao.FacultyDao;
import com.pojo.Student;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/facultyStudent")
public class FacultyStudent extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String url = "jdbc:mysql://localhost:3306/codenera";
		String dbUsername = "root";
		String dbPassword = "ieytsak7eq@123";
		
		List<Student> mystd = new ArrayList<>();

		FacultyDao fdao;
		int loggedInFacultyId = (int) request.getSession().getAttribute("facultyId");

		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("jwtToken");
		if (token != null && AuthenticationPoint.validateToken(token))
		{
			try
			{
				Class.forName("com.mysql.jdbc.Driver");

				Connection conn = DriverManager.getConnection(url, dbUsername, dbPassword);

				String sql = "SELECT u.id, u.username, u.email, b.batch_name, b.batch_id " +
			             "FROM user u " +
			             "JOIN alumni a ON u.id = a.student_id " +
			             "JOIN batch b ON a.batch_id = b.batch_id " +
			             "WHERE b.faculty_id = ? " +
			             "ORDER BY b.batch_name ASC";


				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setInt(1, loggedInFacultyId); // Replace 1 with the actual faculty ID

				ResultSet result = statement.executeQuery();

				List<Student> students = new ArrayList<>();
				List<Student> batchList = new ArrayList<>();

				while (result.next())
				{
					int id = result.getInt("id");
					String username = result.getString("username");
					String email = result.getString("email");
					String batchName = result.getString("batch_name");
					int batchId = result.getInt("batch_id");

					Student student = new Student(id, username, email, batchName, batchId);
					students.add(student);
				}

				String sql1 = "SELECT batch_id, batch_name FROM batch WHERE faculty_id = ?";

				PreparedStatement statement1 = conn.prepareStatement(sql1);
				statement1.setInt(1, loggedInFacultyId); // Set the faculty_id to 1
				ResultSet resultSet = statement1.executeQuery();

				while (resultSet.next())
				{
					int batchId = resultSet.getInt("batch_id");
					String batchName = resultSet.getString("batch_name");

					Student batchname = new Student(batchName, batchId);
					batchList.add(batchname);
				}

				request.setAttribute("batch", batchList);
				request.setAttribute("students", students);

				String callingPage = request.getParameter("callingPage");

				if (callingPage != null && callingPage.equals("page1"))
				{
					request.getRequestDispatcher("FacultyStudents.jsp").forward(request, response);
				} else if (callingPage.equals("page2"))
				{
					request.getRequestDispatcher("Select_Batch_Message_faculty.jsp").forward(request, response);
				} else if (callingPage.equals("page3"))
				{
					request.getRequestDispatcher("AddAssignment.jsp").forward(request, response);
				}
			} catch (ClassNotFoundException | SQLException e)
			{
				e.printStackTrace();
			}

		} else
		{
			session.invalidate();
			response.sendRedirect("index.jsp");
		}

	}
}
