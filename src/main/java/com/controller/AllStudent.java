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
import com.pojo.Student;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/allStudent")
public class AllStudent extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		AdminDao adao;

		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("jwtToken");
		if (token != null && AuthenticationPoint.validateToken(token))
		{
			try
			{
				adao = new AdminDao(JDBC.getConnection());
				List<Student> userList2 = adao.StudentList();

				session.setAttribute("userList", userList2);
				request.setAttribute("userList", userList2);
				request.getRequestDispatcher("AdminStudentList.jsp").forward(request, response);

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
