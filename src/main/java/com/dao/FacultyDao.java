package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.pojo.Student;

public class FacultyDao
{
	private Connection con;

	public FacultyDao(Connection con)
	{
		this.con = con;
	}

	public String faculty_login(String FaultyID, String FacultyPass)
	{
		String facultyname = null;
		try
		{
			String sql = "SELECT * FROM faculty WHERE faculty_username = ? AND faculty_password = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, FaultyID);
			statement.setString(2, FacultyPass);

			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				 facultyname = result.getString("faculty_name");
				int facultyId = result.getInt("faculty_id");
				System.out.println("Faculty stored in session: " + facultyname);
			}
			return facultyname;
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return facultyname;
	}

	public int facuty_id(String FacultyID)
	{
		int faculty_id = 0;
		try
		{
			String sql = "SELECT * FROM faculty WHERE faculty_username = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, FacultyID);

			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				faculty_id = result.getInt("faculty_id");
				System.out.println("Faculty ID stored: " + faculty_id);
			}
			return faculty_id;
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return faculty_id;
	}

	public int countBatches(int faculty_id) throws SQLException
	{
		int count = 0;
		String query = "SELECT COUNT(*) AS count FROM batch WHERE faculty_id = ?";
		try (PreparedStatement statement = con.prepareStatement(query))
		{
			statement.setInt(1, faculty_id);
			try (ResultSet resultSet = statement.executeQuery())
			{
				if (resultSet.next())
				{
					count = resultSet.getInt("count");
					System.out.println("Total Batch : "+count);
				}
			}
		}
		return count;
	}

	public int countUsers(int faculty_id) throws SQLException {
	    int count = 0;
	    String query = "SELECT COUNT(*) AS count "
	            + "FROM user u "
	            + "JOIN alumni a ON u.id = a.student_id "
	            + "JOIN batch b ON a.batch_id = b.batch_id "
	            + "WHERE b.faculty_id = ?";
	    try (PreparedStatement statement = con.prepareStatement(query)) {
	        statement.setInt(1, faculty_id);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                count = resultSet.getInt("count");
					System.out.println("Total Student : "+count);

	            }
	        }
	    }
	    return count;
	}

	public List<Student> myStudents(int loggedInFacultyId) throws ClassNotFoundException
	{
		List<Student> userList = new ArrayList<>();

		try
		{
			String sql = "SELECT u.id, u.username, u.email, b.batch_name, b.batch_id " + "FROM user u "
					+ "JOIN alumni a ON u.id = a.student_id " + "JOIN batch b ON a.batch_id = b.batch_id "
					+ "WHERE b.faculty_id = ?";
			
			PreparedStatement statement = con.prepareStatement(sql);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				int id = resultSet.getInt("id");
				String username = resultSet.getString("username");
				String email = resultSet.getString("email");
				String fullname = resultSet.getString("fullname");

				String password = resultSet.getString("password");
				String mobile = resultSet.getString("phone");
				String city = resultSet.getString("city");
				String age = resultSet.getString("age");
				String course = resultSet.getString("course");
				byte[] imageBytes = resultSet.getBytes("img");
				String batchName = resultSet.getString("batch_name");
				String facultyName = resultSet.getString("faculty_name");

				if (batchName == null)
				{
					batchName = "-------";
					facultyName = "-------";
				}

				String imagePath = null;
				if (imageBytes != null && imageBytes.length > 0)
				{

					String base64Image = Base64.getEncoder().encodeToString(imageBytes);

					imagePath = "data:image/jpeg;base64," + base64Image;
				}

				Student users = new Student(id, username, fullname, email, city, mobile, age, course, imagePath,
						batchName, facultyName, password);
				userList.add(users);
			}

			return userList;

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return userList;
	}


}
