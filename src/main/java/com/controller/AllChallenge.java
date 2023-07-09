package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.GetCodingData;
import com.pojo.CodingData;
import com.sql.JDBC;


public class AllChallenge extends HttpServlet
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{ 
		GetCodingData udao;
		try
		{
			udao = new GetCodingData(JDBC.getConnection());
			List <CodingData> challenges = udao.getCodingChallenges();
			
			request.setAttribute("challenges", challenges);	
			request.getRequestDispatcher("WelcomePage.jsp").forward(request, response);

		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
	}
}
