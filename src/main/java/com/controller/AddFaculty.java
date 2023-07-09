package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AdminDao;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/addFaculty")
public class AddFaculty extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{

		String Facultyname = req.getParameter("facultyname");

		String FacultyUserName = req.getParameter("facultyusername");
		String FacultyPassword = req.getParameter("facultypassword");

		AdminDao adao;

		HttpSession session = req.getSession();
		String token = (String) session.getAttribute("jwtToken");
		if (token != null && AuthenticationPoint.validateToken(token))
		{
			try
			{
				adao = new AdminDao(JDBC.getConnection());

				if (adao.addFaculty(Facultyname, FacultyUserName, FacultyPassword))
				{
					res.sendRedirect("AdminWelcome.jsp");
				} else
				{
					res.sendRedirect("AddFaculty.jsp");
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
		} else
		{
			session.invalidate();
			res.sendRedirect("index.jsp");
		}
		

	}
}
