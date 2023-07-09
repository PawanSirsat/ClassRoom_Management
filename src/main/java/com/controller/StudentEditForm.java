package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StudentDao;
import com.pojo.Student;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/edit")
public class StudentEditForm extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		StudentDao sdao;

		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("jwtToken");
		if (token != null && AuthenticationPoint.validateToken(token))
		{

			try
			{
				int id = Integer.parseInt(request.getParameter("id"));
				System.out.println("Studetn ID : " + id);

				sdao = new StudentDao(JDBC.getConnection());
				Student std = sdao.show_profile(id);

				session.setAttribute("StdProfile", std);

				request.setAttribute("StdProfile", std);
				request.getRequestDispatcher("AdminUpdateForm.jsp").forward(request, response);

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
			response.sendRedirect("index.jsp");
		}

		

	}
}