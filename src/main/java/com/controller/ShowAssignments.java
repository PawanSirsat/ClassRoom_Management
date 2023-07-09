package com.controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StudentDao;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/showAssignmentStd")
public class ShowAssignments extends HttpServlet
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int loggedInFacultyId = (int) request.getSession().getAttribute("facultyId");
		StudentDao sdao = null;
		HttpSession session = request.getSession();

		String token = (String) session.getAttribute("jwtToken");

		if (token != null && AuthenticationPoint.validateToken(token))
		{
			
			try
			{
				sdao = new StudentDao(JDBC.getConnection());

			} catch (Exception e)
			{
				System.out.println(e);
			}

			ResultSet result = sdao.show_Assignment(loggedInFacultyId);

			session.setAttribute("assignments", result);
			request.setAttribute("assignments", result);
			request.getRequestDispatcher("ShowAssignment.jsp").forward(request, response);

		} else
		{
			session.invalidate();
			response.sendRedirect("Studentlogin.html");
		}
		
	}

}
