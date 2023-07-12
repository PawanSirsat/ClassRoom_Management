package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StudentDao;
import com.pojo.Message;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/receiveMessage")
public class StudentMessage extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		HttpSession session = request.getSession();

		String batchId = request.getParameter("batchId");
		int batchIdInt = Integer.parseInt(batchId);
		System.out.println(batchId);
		
		session.setAttribute("batchId", batchId);

		System.out.println("\n ___---___\n");

		String token = (String) session.getAttribute("jwtToken");
		List<Message> messages = new ArrayList<Message>();

		if (token != null && AuthenticationPoint.validateToken(token))
		{
			try
			{
				System.out.println("hello");
				StudentDao sdao = new StudentDao(JDBC.getConnection());
				messages = sdao.read_message(batchIdInt);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println(e);
			} catch (SQLException e)
			{
				e.printStackTrace();
			}

			try
			{
				session.setAttribute("batchId", messages.get(0).getBatch_id());
				session.setAttribute("batchName", messages.get(0).getBatchName());
			} catch (IndexOutOfBoundsException e)
			{
				System.out.println(e);
			}
			request.setAttribute("messages", messages);

			String callingPage = request.getParameter("callingPage");

			if (callingPage != null && callingPage.equals("page1"))
			{
				request.getRequestDispatcher("StudentMessage.jsp").forward(request, response);
			} else if (callingPage.equals("page2"))
			{
				request.getRequestDispatcher("Faculty_Message_Module.jsp").forward(request, response);
			} else if (callingPage.equals("page3"))
			{
				request.getRequestDispatcher("Admin_Message_Module.jsp").forward(request, response);
			}
		} else
		{
			session.invalidate();
			response.sendRedirect("Studentlogin.html");
		}
	}
}
