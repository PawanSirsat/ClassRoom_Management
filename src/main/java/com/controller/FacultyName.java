package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AdminDao;
import com.pojo.Course;
import com.pojo.Faculty;
import com.sql.JDBC;

@WebServlet("/facultyname")
public class FacultyName extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AdminDao adm = null;
		
		try
		{
			String callingPage = request.getParameter("callingPage");

			adm = new AdminDao(JDBC.getConnection());
			
			List<Faculty> allfaculty = adm.allfaculty();
			List<Course> allCourse = adm.allCourses();
			
			HttpSession session = request.getSession();
			session.setAttribute("facultyList", allfaculty);
			request.setAttribute("facultyList", allfaculty);
			
			session.setAttribute("courseList", allCourse);
			request.setAttribute("courseList", allCourse);
			
			if (callingPage != null && callingPage.equals("faculty1"))
			{
				request.getRequestDispatcher("AddBatch.jsp").forward(request, response);
			} else if (callingPage != null && callingPage.equals("faculty2"))
			{
				request.getRequestDispatcher("AllFaculty.jsp").forward(request, response);
			} 

		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
