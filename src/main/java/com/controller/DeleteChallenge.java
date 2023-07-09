package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.GetCodingData;
import com.sql.JDBC;


@WebServlet("/deletechallenge")
public class DeleteChallenge extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GetCodingData cdao;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));

		try
		{
			try
			{
				cdao = new GetCodingData(JDBC.getConnection());
			} catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (cdao.deleteChallenge(id))
			{
				response.sendRedirect("index.jsp");
				
			} else
			{
				response.sendRedirect("index.jsp");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
}

