package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.GetCodingData;
import com.pojo.CodingData;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/addchallenge")
public class AddChallenge extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GetCodingData gdata = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int loggedInFacultyId = (int) request.getSession().getAttribute("facultyId");
		int loggedInStudentId = (int) request.getSession().getAttribute("studentId");

		int batchId = (int) request.getSession().getAttribute("batchId");

		// Retrieve form data
		String challengeName = request.getParameter("challengeName");
		String description = request.getParameter("description");
		String difficultyLevel = request.getParameter("difficultyLevel");
		String challengeType = request.getParameter("tags");

		CodingData cdCodingData = new CodingData(challengeName, description, difficultyLevel, challengeType,
				loggedInFacultyId, loggedInStudentId, batchId);

		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("jwtToken");
		if (token != null && AuthenticationPoint.validateToken(token))
		{
			try
			{
				gdata = new GetCodingData(JDBC.getConnection());

				if (gdata.addChallenge(cdCodingData))
				{
					request.getRequestDispatcher("index.jsp").forward(request, response);
				} else
				{
					response.sendRedirect("WelcomePage.jsp");
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
		{
			session.invalidate();
			response.sendRedirect("index.jsp");
		}
		
	}

}
