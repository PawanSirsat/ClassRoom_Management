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

import com.dao.AdminDao;
import com.pojo.Batch;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/allbatchname")
public class AllBatchName extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		List<Batch> batchList = new ArrayList<>();

		AdminDao adao;

		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("jwtToken");
		if (token != null && AuthenticationPoint.validateToken(token))
		{
			try
			{
				adao = new AdminDao(JDBC.getConnection());
				batchList = adao.allBatch();

				request.setAttribute("ShowBatch", batchList);
				String callingPage = request.getParameter("callingPage");

				if (callingPage != null && callingPage.equals("page1"))
				{
					request.getRequestDispatcher("AddAlumni.jsp").forward(request, response);
				} else if (callingPage != null && callingPage.equals("page2"))
				{
					request.getRequestDispatcher("Select_Batch_Message.jsp").forward(request, response);
				} else
				{
					request.getRequestDispatcher("AllBatch.jsp").forward(request, response);
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
			response.sendRedirect("index.jsp");
		}
		
	}
}
