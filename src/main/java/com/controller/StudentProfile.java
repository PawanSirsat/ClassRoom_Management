package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PaymentDao;
import com.pojo.Payment;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

public class StudentProfile extends HttpServlet
{
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PaymentDao pdao = null;
		int loggedInStudentId = (int) request.getSession().getAttribute("studentId");

		List<Payment> payments= new ArrayList();
		HttpSession session = request.getSession();
		try
		{
			pdao = new PaymentDao(JDBC.getConnection());
			String token = (String) session.getAttribute("jwtToken");

			if (token != null && AuthenticationPoint.validateToken(token))
			{
				Payment payment = pdao.payment_details(loggedInStudentId);				
		        
				session.setAttribute("coursefees", payment.getCourse_fees()); // Assuming 'username' is the variable
				session.setAttribute("paidfees", payment.getPaid_fees()); // Assuming 'username' is the variable that
				session.setAttribute("unpaidfees", payment.getUnpaid_fees()); // Assuming 'username' is the variable

				System.out.println("JWT Tokken 2 : " + token);
				request.getRequestDispatcher("StudentProfile.jsp").forward(request, response);
			} else
			{
				session.invalidate();
				response.sendRedirect("Studentlogin.html");
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

	}
}
