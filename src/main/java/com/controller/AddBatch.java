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

@WebServlet("/addbatch")
public class AddBatch extends HttpServlet
{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{

		String batchName = req.getParameter("batchname");
		String batchYear = req.getParameter("batchyear");
		int batchYearINT = Integer.parseInt(batchYear);
		String course = req.getParameter("course");
		String faculty = req.getParameter("faculty");
		int facultyInt = Integer.parseInt(faculty);

		AdminDao adao;
		HttpSession session = req.getSession();
		String token = (String) session.getAttribute("jwtToken");
		if (token != null && AuthenticationPoint.validateToken(token))
		{
			try
			{
				adao = new AdminDao(JDBC.getConnection());

				if (adao.add_batch(batchName, batchYearINT, course, facultyInt))
				{
					res.sendRedirect("AdminWelcome.jsp");
				} else
				{
					res.sendRedirect("AddBatch.jsp");
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
