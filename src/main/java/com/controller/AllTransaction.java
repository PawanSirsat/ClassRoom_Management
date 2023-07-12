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

@WebServlet("/allTransaction")
public class AllTransaction extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		List<Transaction> transactionDetailsList = new ArrayList<>();

		String url = "jdbc:mysql://localhost:3306/codenera";
		String user = "root";
		String password = "ieytsak7eq@123";

		try (Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT a.student_id, u.fullname AS student_name, b.batch_name, t.transaction_date, t.payment_amount "
					+ "FROM alumni a " + "INNER JOIN user u ON a.student_id = u.id "
					+ "INNER JOIN batch b ON a.batch_id = b.batch_id " + "INNER JOIN transaction t ON u.id = t.user_id";

			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				int studentId = rs.getInt("student_id");
				String studentName = rs.getString("student_name");
				String batchName = rs.getString("batch_name");
				String transactionDate = rs.getString("transaction_date");
				int paymentAmount = rs.getInt("payment_amount");

				Transaction transactionDetails = new Transaction(studentId, studentName, batchName, transactionDate,
						paymentAmount);
				transactionDetailsList.add(transactionDetails);
			}
			request.setAttribute("transactionDetailsList", transactionDetailsList);
			request.getRequestDispatcher("AllTransaction.jsp").forward(request, response);

		} catch (SQLException e)
		{
			e.printStackTrace();

		}
	}
}
