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
import com.dao.PaymentDao;
import com.pojo.Payment;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/adminHome")
public class AdminHome extends HttpServlet
{
	AdminDao adao;
	PaymentDao pdao = null;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try
		{
			List<Payment> payments= new ArrayList();

			pdao = new PaymentDao(JDBC.getConnection());
			adao = new AdminDao(JDBC.getConnection());
			HttpSession session = request.getSession();
			String token = (String) session.getAttribute("jwtToken");

			if (token != null && AuthenticationPoint.validateToken(token))
			{
				//all info related class faclty and courses
				int totalUsers = adao.countUsers();
				int totalFaculty = adao.countFaculty();
				int totalBatches = adao.countBatches();
				session.setAttribute("totalUsers", totalUsers);
				session.setAttribute("totalFaculty", totalFaculty);
				session.setAttribute("totalBatches", totalBatches);

				//all course payments
				payments = pdao.showPayments();
				request.setAttribute("courseFeesList", payments);


				Payment payments2 = new Payment();
				payments2 = pdao.showPending();
				session.setAttribute("totalfees",payments2.getTotal_fees() );
				session.setAttribute("totalcollected", payments2.getPaid_fees());
				session.setAttribute("totalpending", payments2.getUnpaid_fees());

				
				request.getRequestDispatcher("AdminProfile.jsp").forward(request, response);
			} else
			{
				session.invalidate();
				response.sendRedirect("index.jsp");
			}
		} catch (ClassNotFoundException e1)
		{
			e1.printStackTrace();
		} catch (SQLException e1)
		{
			e1.printStackTrace();
		}
	}

}
