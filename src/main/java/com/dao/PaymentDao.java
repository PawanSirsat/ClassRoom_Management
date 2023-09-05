package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pojo.Payment;

public class PaymentDao
{
	private Connection con;

	public PaymentDao(Connection con)
	{
		this.con = con;
	}

	public PaymentDao()
	{
		// TODO Auto-generated constructor stub
	}

	public Payment showPending()
	{
		String query = "SELECT c.course_name, COALESCE(SUM(c.fees_structure), 0) AS total_fees, COALESCE(SUM(p.paid_fees), 0) AS total_paid_fees, (COALESCE(SUM(c.fees_structure), 0) - COALESCE(SUM(p.paid_fees), 0)) AS total_unpaid_fees FROM Courses c LEFT JOIN Payments p ON c.course_id = p.course_id GROUP BY c.course_name";
		try
		{
			double pending = 0;
			double collected = 0;
			double total = 0;
			PreparedStatement statement = con.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			
			System.out.println("Fees:/n");

			while (resultSet.next())
			{
				double totalFees = resultSet.getInt("total_fees");
				double totalPaidFees = resultSet.getInt("total_paid_fees");
				double totalUnpaidFees = resultSet.getInt("total_unpaid_fees");

				pending += totalUnpaidFees;
				collected +=totalPaidFees;
				total +=totalFees;
			}
			System.out.println("total  : "+total);
			System.out.println("----totalpaid  : "+collected);
			System.out.println("----unpaidfees : "+pending);
	
			Payment courseFees = new Payment(total, collected, pending);	
			
			return courseFees;
		} catch (SQLException e)
		{
			// Handle any database errors
			e.printStackTrace();
		}

		return null;
	}
	
	public List<Payment> showPayments()
	{
		String sqlQuery = "SELECT " +
			    "c.course_name, " +
			    "COALESCE(SUM(c.fees_structure), 0) * CASE WHEN COUNT(p.course_id) = 0 THEN 0 ELSE 1 END AS total_fees, " +
			    "COALESCE(SUM(p.paid_fees), 0) AS total_paid_fees, " +
			    "COALESCE(SUM(c.fees_structure), 0) * CASE WHEN COUNT(p.course_id) = 0 THEN 0 ELSE 1 END AS total_unpaid_fees " +
			"FROM " +
			    "Courses c " +
			"LEFT JOIN " +
			    "Payments p ON c.course_id = p.course_id " +
			"GROUP BY " +
			    "c.course_name;";
		try
		{
			System.out.println("\nPawan");
			PreparedStatement statement = con.prepareStatement(sqlQuery);
			ResultSet resultSet = statement.executeQuery();

			List<Payment> courseFeesList = new ArrayList<>();

			System.out.println("Course /n");
			while (resultSet.next())
			{
				String courseName = resultSet.getString("course_name");
				double totalFees = resultSet.getInt("total_fees");
				double totalPaidFees = resultSet.getInt("total_paid_fees");
				double totalUnpaidFees = resultSet.getInt("total_unpaid_fees");
				
				System.out.println("Course Name : "+courseName);
				System.out.println("total fees : "+totalFees);
				System.out.println("totalpaid fees : "+totalPaidFees);
				System.out.println("total unpaidfees : "+totalUnpaidFees);

				Payment courseFees = new Payment(courseName, totalFees, totalPaidFees, totalUnpaidFees);
				courseFeesList.add(courseFees);
			}
			return courseFeesList;
		} catch (SQLException e)
		{
			// Handle any database errors
			e.printStackTrace();
		}

		return null;
	}

	public Payment payment_details(int studentId)
	{

		Payment payment = new Payment();
		String sqlQuery = "SELECT " + "u.id, " + "c.course_name, " + "c.fees_structure AS course_fees, "
				+ "COALESCE(SUM(p.paid_fees), 0) AS paid_fees, "
				+ "(c.fees_structure - COALESCE(SUM(p.paid_fees), 0)) AS unpaid_fees " + "FROM Payments p "
				+ "JOIN user u ON p.user_id = u.id " + "JOIN Courses c ON p.course_id = c.course_id "
				+ "WHERE u.id = ? " + "GROUP BY u.id, c.course_name, c.fees_structure";
		try
		{
			PreparedStatement stmt = con.prepareStatement(sqlQuery);
			stmt.setInt(1, studentId);
			ResultSet rs = stmt.executeQuery();

			if (rs.next())
			{
				int userId = rs.getInt("id");
				String courseName = rs.getString("course_name");
				double courseFees = rs.getDouble("course_fees");
				double paidFees = rs.getDouble("paid_fees");
				double unpaidFees = rs.getDouble("unpaid_fees");

				payment = new Payment(courseFees, paidFees, unpaidFees);
				// Use the retrieved values as needed
				System.out.println("User ID: " + userId);
				System.out.println("Course Name: " + courseName);
				System.out.println("Course Fees: " + courseFees);
				System.out.println("Paid Fees: " + paidFees);
				System.out.println("Unpaid Fees: " + unpaidFees);
			} else
			{
				System.out.println("No results found.");
			}
			return payment;
		} catch (Exception e)
		{
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}

	public boolean makePayment(int amount, int userid)
	{
		String sqlQuery = "UPDATE Payments " + "SET paid_fees = paid_fees + ? " + "WHERE user_id = ?";
		try
		{
			PreparedStatement stmt = con.prepareStatement(sqlQuery);
			stmt.setInt(1, amount);
			stmt.setInt(2, userid);
			return stmt.executeUpdate() > 0;

		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return false;
	}
	
	public boolean maketransaction(int amount, int userid)
	{
        String insertData = "INSERT INTO Transaction (user_id, payment_amount, transaction_date) VALUES (?, ?, CURRENT_TIMESTAMP)";
		try
		{
			PreparedStatement pstmt = con.prepareStatement(insertData);
			pstmt.setInt(1, userid);
            pstmt.setDouble(2, amount);
			return pstmt.executeUpdate() > 0;

		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return false;
	}
}
