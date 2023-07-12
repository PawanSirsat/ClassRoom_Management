package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.Message;
import com.pojo.Student;

import Encryption.CodeGenerator;
import Encryption.Encrypt;

public class StudentDao
{
	public static int rowsAffected = 0;
	private Connection con;

	public StudentDao(Connection con)
	{
		this.con = con;
	}

	public Boolean Signup(Student users)
	{
		Boolean check = false;
		try
		{	
			String insertData = "INSERT INTO user (email,password,username,fullname,phone,city,age,course,img) VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(insertData);

			
			pstmt.setString(1, users.getEmail());
			pstmt.setString(2, users.getPassword());
			pstmt.setString(3, users.getUsername());
			pstmt.setString(4, users.getFullName());
			pstmt.setString(5, users.getPhone());
			pstmt.setString(6, users.getCity());
			pstmt.setInt(7, users.getIntage());
			pstmt.setString(8, users.getCourse());
			pstmt.setBytes(9, users.getImageBytes());			
		    check = pstmt.executeUpdate() > 0;

		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return check;	
	}
	public boolean deleteUser(int id) throws SQLException {
	    String DELETE_USERS_SQL = "DELETE FROM user WHERE id = ?";
	    boolean rowDeleted = false;
	    try {
	        // Check if the user exists
	        if (userExists(id)) {
	            // Disable foreign key checks temporarily
	            String disableFKChecksSQL = "SET FOREIGN_KEY_CHECKS = 0";
	            PreparedStatement disableFKChecksStmt = con.prepareStatement(disableFKChecksSQL);
	            disableFKChecksStmt.executeUpdate();
	            
	            // Delete the user
	            PreparedStatement deleteUserStmt = con.prepareStatement(DELETE_USERS_SQL);
	            deleteUserStmt.setInt(1, id);
	            int rowsAffected = deleteUserStmt.executeUpdate();
	            if (rowsAffected > 0) {
	                rowDeleted = true;
	            }
	            
	            // Enable foreign key checks again
	            String enableFKChecksSQL = "SET FOREIGN_KEY_CHECKS = 1";
	            PreparedStatement enableFKChecksStmt = con.prepareStatement(enableFKChecksSQL);
	            enableFKChecksStmt.executeUpdate();
	        }
	    } catch (Exception e) {
	        // Handle any exceptions
	    }
	    return rowDeleted;
	}

	private boolean userExists(int id) throws SQLException {
	    String SELECT_USER_SQL = "SELECT id FROM user WHERE id = ?";
	    PreparedStatement selectUserStmt = con.prepareStatement(SELECT_USER_SQL);
	    selectUserStmt.setInt(1, id);
	    ResultSet resultSet = selectUserStmt.executeQuery();
	    return resultSet.next();
	}

	
	public boolean updateProfile(Student user) {
	    boolean rowUpdated = false;
	    String UPDATE_USERS_SQL = "UPDATE user SET username = ?, email = ?, fullname = ?, city = ?, phone = ?, age = ?, password = ? WHERE id = ?;";

	    try {
	        PreparedStatement statement = con.prepareStatement(UPDATE_USERS_SQL);
	        System.out.println("Updated User: " + user.getId());
	        statement.setString(1, user.getUsername());
	        statement.setString(2, user.getEmail());
	        statement.setString(3, user.getFullName());
	        statement.setString(4, user.getCity());
	        statement.setString(5, user.getPhone());
	        statement.setInt(6, user.getIntage());
	        statement.setString(7, user.getPassword());
	        statement.setInt(8, user.getId());
	        
	        System.out.println("COurse "+user.getCourse());

	        rowUpdated = statement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        // Handle the exception (e.g., log the error or throw a custom exception)
	        e.printStackTrace();
	    }

	    return rowUpdated;
	}

	public ResultSet show_Assignment(int loggedInFacultyId)
	{
		ResultSet assignments = null;
		try
		{
			String query1 = "SELECT a.*, f.faculty_name, b.batch_name " + "FROM assignments a "
					+ "JOIN faculty f ON a.faculty_id = f.faculty_id " + "JOIN batch b ON a.batch_id = b.batch_id "
					+ "WHERE a.faculty_id = ?";

			String query = "SELECT * FROM assignments WHERE faculty_id = ?";
			PreparedStatement statement = con.prepareStatement(query1);
			statement.setInt(1, loggedInFacultyId);
			assignments = statement.executeQuery();
			return assignments;

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return assignments;
	}

	public List<Message> send_message(int loggedInFacultyId, int loggedInStudentId, int batchId, String messageText,
			String callingPage, String serializationData)
	{
		List<Message> messages = new ArrayList<>();

		if (callingPage != null && callingPage.equals("page1"))
		{
			try
			{
				String insertSql = "INSERT INTO messages (faculty_id, batch_id, message_text, data) VALUES (?, ?, ?, ?)";

				PreparedStatement insertStatement = con.prepareStatement(insertSql);
				insertStatement.setInt(1, loggedInFacultyId);
				insertStatement.setInt(2, batchId);
				insertStatement.setString(3, messageText);
				insertStatement.setString(4, serializationData);


				rowsAffected = insertStatement.executeUpdate();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}

			return messages = read_message(batchId);
		} else if (callingPage.equals("page2"))
		{
			try
			{
				String insertSql = "INSERT INTO messages (student_id, batch_id, message_text, data) VALUES (?, ?, ?, ?)";

				PreparedStatement insertStatement = con.prepareStatement(insertSql);
				insertStatement.setInt(1, loggedInStudentId);
				insertStatement.setInt(2, batchId);
				insertStatement.setString(3, messageText);
				insertStatement.setString(4, serializationData);


				rowsAffected = insertStatement.executeUpdate();

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			return messages = read_message(batchId);

		}else if (callingPage.equals("page3"))
		{
			try
			{
				String insertSql = "INSERT INTO messages (admin_id, batch_id, message_text, data) VALUES (?, ?, ?, ?)";

				PreparedStatement insertStatement = con.prepareStatement(insertSql);
				insertStatement.setInt(1, 001);
				insertStatement.setInt(2, batchId);
				insertStatement.setString(3, messageText);
				insertStatement.setString(4, serializationData);

				rowsAffected = insertStatement.executeUpdate();

			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			return messages = read_message(batchId);
		}
		return messages = read_message(batchId);
	}

	public List<Message> read_message(int batchIdInt2)
	{
		String batchName = null;
		String tempBatchName = null;

		try
		{
			String selectSql = "SELECT m.batch_id, m.message_id, m.message_text, m.created_at, m.data, u.username, u.img, f.faculty_name, b.batch_name "
					+ "FROM messages m " + "JOIN batch b ON m.batch_id = b.batch_id "
					+ "LEFT JOIN user u ON m.student_id = u.id " + "LEFT JOIN faculty f ON m.faculty_id = f.faculty_id "
					+ "WHERE m.batch_id = ?";

			PreparedStatement selectStatement = con.prepareStatement(selectSql);
			selectStatement.setInt(1, batchIdInt2);

			ResultSet result = selectStatement.executeQuery();

			List<Message> messages = new ArrayList<>();

			while (result.next())
			{
				String messageText1 = result.getString("message_text");
				String createdAt = result.getString("created_at");
				int batch_id = result.getInt("batch_id");

				String studentName = result.getString("username");
				String facultyName = result.getString("faculty_name");
				String deserialized = result.getString("data");
				
				ObjectMapper objectMapper = new ObjectMapper();			
				CodeGenerator convert = objectMapper.readValue(deserialized,CodeGenerator.class);			
				String DecryptionMessage = Encrypt.Generator(convert,messageText1);
	
				String senderName = null;
				byte[] imageBytes = result.getBytes("img");

				String imagePath = null;
				if (imageBytes != null && imageBytes.length > 0)
				{
					// Convert imageBytes to Base64-encoded string
					String base64Image = Base64.getEncoder().encodeToString(imageBytes);
					// Construct the image URL
					imagePath = "data:image/jpeg;base64," + base64Image;
				}

				if (studentName != null)
				{
					senderName = studentName;
				} else if (facultyName != null)
				{
					senderName = facultyName;
				} else
				{
					senderName = "Admin";
				}

				batchName = result.getString("batch_name");

				Message message = new Message(DecryptionMessage, createdAt, senderName, batchName, imagePath, batch_id);
				messages.add(message);
			}

			return messages;

		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public Student check_login(String userid, String password)
	{

		Student std = new Student();
		String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
		PreparedStatement statement = null;
		ResultSet result = null;

		try
		{
			statement = this.con.prepareStatement(sql);
			statement.setString(1, userid);
			statement.setString(2, password);

			result = statement.executeQuery();

			if (result.next())
			{
				std.setId(result.getInt("id"));
				std.setUsername(result.getString("username"));
				std.setEmail(result.getString("email"));
				std.setFullName(result.getString("fullname"));

				std.setCity(result.getString("city"));
				std.setPhone(result.getString("phone"));
				std.setAge(result.getString("age"));
				std.setCourse(result.getString("course"));
				std.setImageBytes(result.getBytes("img"));

				try
				{
					String sql1 = "SELECT a.student_id, a.batch_id, b.batch_name, b.batch_year, b.batch_course, f.faculty_name,f.faculty_id "
							+ "FROM alumni a " + "JOIN batch b ON a.batch_id = b.batch_id "
							+ "JOIN faculty f ON b.faculty_id = f.faculty_id " + "WHERE a.student_id = ?";
					PreparedStatement statement1 = con.prepareStatement(sql1);
					statement1.setInt(1, std.getId());

					ResultSet resultSet = statement1.executeQuery();

					if (resultSet.next())
					{
						std.setBatchId(resultSet.getInt("batch_id"));
						std.setBatchName(resultSet.getString("batch_name"));
						std.setBatchyear(resultSet.getInt("batch_year"));
						std.setCourse(resultSet.getString("batch_course"));
						std.setFacultyName(resultSet.getString("faculty_name"));
						std.setFacultyId(resultSet.getInt("faculty_id"));
					}
				} catch (Exception e)
				{
					System.out.println(e);
					System.out.println("Error in Login");
				}
			}
			return std;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return std;

	}
	
	public Student show_profile(int userid)
	{

		Student std = new Student();
		String sql = "SELECT * FROM user WHERE id = ?";
		PreparedStatement statement = null;
		ResultSet result = null;

		try
		{
			statement = this.con.prepareStatement(sql);
			statement.setInt(1, userid);

			result = statement.executeQuery();

			if (result.next())
			{
				std.setId(userid);
				std.setUsername(result.getString("username"));
				std.setEmail(result.getString("email"));
				std.setFullName(result.getString("fullname"));
				std.setCity(result.getString("city"));
				std.setPhone(result.getString("phone"));
				std.setIntage(result.getInt("age"));
				std.setCourse(result.getString("course"));
				
				byte[]  imageBytes = result.getBytes("img");
				
				  String imagePath = null;
				  
	                if (imageBytes != null && imageBytes.length > 0) {

	                	String base64Image = Base64.getEncoder().encodeToString(imageBytes);

	                    imagePath = "data:image/jpeg;base64," + base64Image;
	                }
	              

				std.setPassword(result.getString("password"));
				
				System.out.println(result.getString("fullname"));
			}
			
			return std;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		return std;

	}


}
