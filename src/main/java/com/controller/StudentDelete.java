package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StudentDao;
import com.sql.JDBC;

@WebServlet("/delete")
public class StudentDelete extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StudentDao sdao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		int id = Integer.parseInt(request.getParameter("id"));

		System.out.println("delete");
		try
		{
			try
			{
				sdao = new StudentDao(JDBC.getConnection());
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (sdao.deleteUser(id))
			{
				response.sendRedirect("AdminWelcome.jsp");
				
			} else
			{
				response.sendRedirect("AdminWelcome.jsp");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
}
