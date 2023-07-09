package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PaymentDao;
import com.dao.StudentDao;
import com.pojo.Payment;
import com.pojo.Student;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

public class StudentLogin extends HttpServlet
{
	StudentDao sdao;
	PaymentDao pdao;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try
		{
			sdao = new StudentDao(JDBC.getConnection());
			Student std = sdao.check_login(username, password);
			pdao = new PaymentDao(JDBC.getConnection());

			if (std.getFullName() != null)
			{
				String token = AuthenticationPoint.generateToken(std.getUsername(), std.getEmail());

				HttpSession session = request.getSession();
				session.setAttribute("jwtToken", token);

				System.out.println("\n1: " + token);

				session.setAttribute("studentId", std.getId()); // Assuming 'username' is the variable that holds the

				Payment payment = pdao.payment_details(std.getId());

				session.setAttribute("coursefees", payment.getCourse_fees()); // Assuming 'username' is the variable
																				// that holds // the
				session.setAttribute("paidfees", payment.getPaid_fees()); // Assuming 'username' is the variable that
																			// holds // the
				session.setAttribute("unpaidfees", payment.getUnpaid_fees()); // Assuming 'username' is the variable
																				// that holds // the

				System.out.println("USer:  "+std.getId());
				session.setAttribute("username", std.getUsername()); // Assuming 'username' is the variable that holds								// // the
				session.setAttribute("fullname", std.getFullName()); // Assuming 'username' is the variable that holds
																		// the
				session.setAttribute("imageBytes", std.getImageBytes());
				session.setAttribute("phone", std.getPhone());
				session.setAttribute("course", std.getCourse());
				session.setAttribute("age", std.getAge());
				session.setAttribute("city", std.getCity());
				session.setAttribute("email", std.getEmail());

				session.setAttribute("batchId", std.getBatchId()); // Assuming 'username' is the variable that holds the
																	// username
				session.setAttribute("batchName", std.getBatchName()); // Assuming 'username' is the variable that holds
																		// the
				session.setAttribute("batchyear", std.getBatchyear()); // Assuming 'username' is the variable that holds
																		// the
				session.setAttribute("batchfaculty", std.getFacultyName());
				session.setAttribute("batchcourse", std.getCourse());
				session.setAttribute("facultyId", std.getFacultyId());

				request.getRequestDispatcher("index.jsp").forward(request, response);
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
