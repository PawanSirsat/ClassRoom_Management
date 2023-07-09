package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JwtAuthentication.AuthenticationPoint;

public class LogoutServlet extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();

		if (session != null)
		{
			try
			{
				String token = (String) session.getAttribute("jwtToken");
				AuthenticationPoint.invalidatedTokens.remove(token);

				session.invalidate();
				response.setStatus(HttpServletResponse.SC_OK);
				response.sendRedirect("Studentlogin.html");
			} catch (Exception e)
			{
				System.out.println(e);
			}

		} else
		{
			response.sendRedirect("Studentlogin.html");
		}
	}
	
	static void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();

		if (session != null)
		{
			try
			{
				session.invalidate();
				response.setStatus(HttpServletResponse.SC_OK);
				response.sendRedirect("Studentlogin.html");
			} catch (Exception e)
			{
				System.out.println(e);
			}

		} 
	}}
