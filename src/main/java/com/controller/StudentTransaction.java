package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pojo.Transaction;

@WebServlet("/studentTransaction")
public class StudentTransaction extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int loggedInStudentId = (int) request.getSession().getAttribute("studentId");
		List<Transaction> transactions = new ArrayList<>();

		String url = "jdbc:mysql://localhost:3306/codenera";
		String user = "root";
		String password = "ieytsak7eq@123";

		try (Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT transaction_date, payment_amount FROM Transaction WHERE user_id = ?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, loggedInStudentId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				String transactionDate = rs.getString("transaction_date");
				int paymentAmount = rs.getInt("payment_amount");
				Transaction transaction = new Transaction(transactionDate, paymentAmount);
				transactions.add(transaction);
			}
			
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		request.setAttribute("transactions", transactions);
		request.getRequestDispatcher("transactionDetails.jsp").forward(request, response);
	}
}
