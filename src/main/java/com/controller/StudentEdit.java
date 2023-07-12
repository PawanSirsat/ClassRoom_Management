package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StudentDao;
import com.pojo.Student;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

public class StudentEdit extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StudentDao sdao;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			sdao = new StudentDao(JDBC.getConnection());

		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println("ID   : " + id);

		String fullname = request.getParameter("fullname");
		System.out.println("Check " + fullname);
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String city = request.getParameter("city");
		String mobile = request.getParameter("mobile");
		int age = Integer.parseInt(request.getParameter("age"));

		System.out.println("ageS: " + age);

		String course = request.getParameter("course");
		String imagePath = request.getParameter("imagepath");
		String password = request.getParameter("password");

		Student users = new Student(id, username, email, city, mobile, age, course, imagePath, fullname, password);

		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("jwtToken");
		if (token != null && AuthenticationPoint.validateToken(token))
		{
			String callingPage = request.getParameter("callingPage");

			if (callingPage != null && callingPage.equals("adminPage"))
			{
				if (sdao.updateProfile(users))
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/allStudent");
					dispatcher.forward(request, response);
				} else
				{
					response.sendRedirect("AdminWelcome.jsp");
				}
			} else 
			{
				if (sdao.updateProfile(users))
				{
					RequestDispatcher dispatcher = request.getRequestDispatcher("/StudentProfile.jsp");
					dispatcher.forward(request, response);
				} else
				{
					response.sendRedirect("WelcomePage.jsp");
				}
			} 
			
		} else
		{
			session.invalidate();
			response.sendRedirect("index.jsp");
		}

	}
}
