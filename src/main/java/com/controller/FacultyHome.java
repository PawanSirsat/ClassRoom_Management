package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/facultyHome")
public class FacultyHome extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("jwtToken");

		if (token != null && AuthenticationPoint.validateToken(token))
		{
			request.getRequestDispatcher("FacultyHomePage.jsp").forward(request, response);
		} else
		{
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
	}

}
