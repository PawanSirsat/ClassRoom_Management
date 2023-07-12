package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.PaymentDao;
import com.sql.JDBC;

@WebServlet("/payfees")
public class PayFees extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PaymentDao pdao = null;
		int loggedInStudentId = (int) request.getSession().getAttribute("studentId");

		try
		{
			String amount = request.getParameter("amount");
			int amountInt = Integer.parseInt(amount);

			pdao = new PaymentDao(JDBC.getConnection());
			boolean check1 = pdao.makePayment(amountInt,loggedInStudentId);
			boolean check2 = pdao.maketransaction(amountInt,loggedInStudentId);

			if (check1)
			{
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else
			{
				request.getRequestDispatcher("Payment.jsp").forward(request, response);
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
