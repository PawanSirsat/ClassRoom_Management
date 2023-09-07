package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.pojo.Batch;
import com.pojo.Course;
import com.pojo.Faculty;
import com.pojo.Student;

public class AdminDao
{
	private Connection con;

	public AdminDao(Connection con)
	{
		this.con = con;
	}

	public boolean addFaculty(String facultyname, String facultyUserName, String facultyPassword)
	{
		try
		{
			String insertData = "INSERT INTO faculty (faculty_name,faculty_username,faculty_password) VALUES (?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(insertData);

			pstmt.setString(1, facultyname);
			pstmt.setString(2, facultyUserName);
			pstmt.setString(3, facultyPassword);
			pstmt.executeUpdate();
			System.out.println("Data Inserted");
			return true;
		}

		catch (Exception e)
		{
			System.out.println("Data Not Inserted");
			System.out.println(e);
			return false;
		}
	}

	public boolean addCourse(String name, int fees)
	{
		try
		{
			String insertData = "INSERT INTO courses (course_name,fees_structure) VALUES (?,?)";
			PreparedStatement pstmt = con.prepareStatement(insertData);

			pstmt.setString(1, name);
			pstmt.setInt(2, fees);
			pstmt.executeUpdate();
			System.out.println("Data Inserted");
			return true;
		}

		catch (Exception e)
		{
			System.out.println("Data Not Inserted");
			System.out.println(e);
			return false;
		}
	}

	public int countUsers() throws SQLException
	{
		int count = 0;
		String query = "SELECT COUNT(*) FROM user";
		try (PreparedStatement statement = con.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
		{
			if (resultSet.next())
			{
				count = resultSet.getInt(1);
			}
		}
		return count;
	}
	
	public int countCourse() throws SQLException
	{
		int count = 0;
		String query = "SELECT COUNT(*) FROM courses";
		try (PreparedStatement statement = con.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
		{
			if (resultSet.next())
			{
				count = resultSet.getInt(1);
			}
		}
		return count;
	}

	public int countFaculty() throws SQLException
	{
		int count = 0;
		String query = "SELECT COUNT(*) FROM faculty";
		try (PreparedStatement statement = con.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
		{
			if (resultSet.next())
			{
				count = resultSet.getInt(1);
			}
		}
		return count;
	}

	public int countBatches() throws SQLException
	{
		int count = 0;
		String query = "SELECT COUNT(*) FROM batch";
		try (PreparedStatement statement = con.prepareStatement(query); ResultSet resultSet = statement.executeQuery())
		{
			if (resultSet.next())
			{
				count = resultSet.getInt(1);
			}
		}
		return count;
	}

	public boolean addAlumni(int batchIdInt, int studentIdInt)
	{
		boolean added = false;
		try
		{
			String insertData = "INSERT INTO alumni (batch_id,student_id) VALUES (?,?)";
			PreparedStatement pstmt = con.prepareStatement(insertData);

			pstmt.setInt(1, batchIdInt);
			pstmt.setInt(2, studentIdInt);

			added = pstmt.executeUpdate() > 0;
			System.out.println("Data Inserted");

			return added;
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return false;
	}

	public List<Batch> allBatch()
	{
		ArrayList<Batch> allbatch = new ArrayList<Batch>();
		try
		{
			String sql = "SELECT * FROM batch";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			Batch batch = new Batch();
			while (resultSet.next())
			{
				int batchId = resultSet.getInt("batch_id");
				String batchName = resultSet.getString("batch_name");
				Batch batchname = new Batch(batchName, batchId);
				allbatch.add(batchname);
			}
			return allbatch;
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return allbatch;
	}

	public List<Student> BatchInfo()
	{
		ArrayList<Student> batchinfo = new ArrayList<Student>();

		try
		{
			 String query = "SELECT b.batch_id, b.batch_name, f.faculty_name, c.course_name, c.fees_structure, b.batch_year, COUNT(a.batch_id) AS Count_Batch " +
                     "FROM batch b " +
                     "LEFT JOIN faculty f ON b.faculty_id = f.faculty_id " +
                     "LEFT JOIN courses c ON b.batch_course = c.course_name " +
                     "LEFT JOIN alumni a ON b.batch_id = a.batch_id " +
                     "GROUP BY b.batch_id, b.batch_name, f.faculty_name, c.course_name, c.fees_structure, b.batch_year;";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			List<Batch> batchList = new ArrayList<>();

			while (rs.next())
			{
				Student batch = new Student();
				batch.setBatchId(rs.getInt("batch_id"));
				batch.setBatchName(rs.getString("batch_name"));
				batch.setFacultyName(rs.getString("faculty_name"));
				batch.setBatchcourse(rs.getString("course_name"));
				batch.setCoursefees(rs.getInt("fees_structure"));
				batch.setBatchyear(rs.getInt("batch_year"));
				batch.setStdCount(rs.getInt("Count_Batch"));

				batchinfo.add(batch);
			}
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return batchinfo;

	}
	
	public List<Student> StudentInfo()
	{
		ArrayList<Student> stdinfo = new ArrayList<Student>();

		try
		{
			String query = "SELECT id, fullname ,course " +
		               "FROM user " +
		               "WHERE id NOT IN (SELECT student_id FROM alumni);";

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next())
			{
				Student batch = new Student();
				batch.setId(rs.getInt("id"));
				batch.setFullName(rs.getString("fullname"));
				batch.setCourse(rs.getString("course"));

				stdinfo.add(batch);
			}
		} catch (Exception e)
		{
System.out.println(e);
		}
		return stdinfo;
	}

	public List<Faculty> allfaculty()
	{
		ArrayList<Faculty> al = new ArrayList<Faculty>();
		try
		{
			String sql = "SELECT * FROM faculty";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				int facultyId = resultSet.getInt("faculty_id");
				String facultyName = resultSet.getString("faculty_name");
				String facultyUserName = resultSet.getString("faculty_username");
				String facultyPassword = resultSet.getString("faculty_password");

				Faculty addFaculty = new Faculty(facultyId, facultyName, facultyUserName, facultyPassword);
				al.add(addFaculty);
			}
			return al;
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return al;
	}

	public List<Course> allCourses()
	{
		System.out.println("AllCourses");
		ArrayList<Course> al = new ArrayList<Course>();
		try
		{
			String sql = "SELECT * FROM courses";
			PreparedStatement statement = con.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				int CourseId = resultSet.getInt("course_id");
				String CourseName = resultSet.getString("course_name");
				int CourseFees = resultSet.getInt("fees_structure");

				Course addFaculty = new Course(CourseId, CourseName, CourseFees);

				al.add(addFaculty);
			}
			return al;
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return al;
	}

	public boolean add_batch(String batchName, int batchYearINT, String course, int facultyInt)
	{
		boolean rowAdded = false;
		try
		{
			String insertData = "INSERT INTO batch (batch_name,batch_year,batch_course,faculty_id) VALUES (?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(insertData);

			pstmt.setString(1, batchName);
			pstmt.setInt(2, batchYearINT);
			pstmt.setString(3, course);
			pstmt.setInt(4, facultyInt);

			rowAdded = pstmt.executeUpdate() > 0;
			System.out.println("Data Inserted");
			return rowAdded;
		} catch (Exception e)
		{
			System.out.println("Data Not Inserted");
			System.out.println(e);
		}
		return rowAdded;
	}

	public String admin_login(String AdminID, String AdminPass)
	{
		String adminName = null;

		try
		{
			String sql = "SELECT * FROM admin WHERE admin_id = ? AND admin_pass = ?";
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setString(1, AdminID);
			statement.setString(2, AdminPass);

			ResultSet result = statement.executeQuery();
			if (result.next())
			{
				adminName = result.getString("admin_name");
			}
			return adminName;
		} catch (Exception e)
		{
			System.out.println(e);
		}
		return adminName;
	}

	public List<Student> StudentList() throws ClassNotFoundException
	{
		List<Student> userList = new ArrayList<>();

		try
		{
			String sql = "SELECT * FROM user";

			String query = "SELECT " + "c.fees_structure AS course_fees, "
					+ "COALESCE(SUM(p.paid_fees), 0) AS paid_fees, "
					+ "c.fees_structure - COALESCE(SUM(p.paid_fees), 0) AS remaining_fees " + "FROM " + "user u "
					+ "LEFT JOIN Payments p ON u.id = p.user_id " + "INNER JOIN Courses c ON c.course_id = p.course_id "
					+ "WHERE " + "u.id = ? " + "GROUP BY " + "c.fees_structure";

			String sql1 = "SELECT u.*, b.batch_name, f.faculty_name " + "FROM user u "
					+ "LEFT JOIN alumni a ON u.id = a.student_id " + "LEFT JOIN batch b ON a.batch_id = b.batch_id "
					+ "LEFT JOIN faculty f ON b.faculty_id = f.faculty_id " + "ORDER BY u.username ASC";

			PreparedStatement statement = con.prepareStatement(sql1);

			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next())
			{
				double courseFees = 0;
				double paidFees = 0;
				double remainingFees = 0;

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

				try
				{
					PreparedStatement statement1 = con.prepareStatement(query);
//					System.out.println("ID "+id);
					statement1.setInt(1, id);

					ResultSet resultSet1 = statement1.executeQuery();

					while (resultSet1.next())
					{
						courseFees = resultSet1.getDouble("course_fees");
						paidFees = resultSet1.getDouble("paid_fees");
						remainingFees = resultSet1.getDouble("remaining_fees");
					}

//					System.out.println("C fees: " + courseFees);
//					System.out.println("P fees: " + paidFees);
//					System.out.println("R fees: " + remainingFees);

				} catch (Exception e)
				{

				}
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
						batchName, facultyName, password, courseFees, paidFees, remainingFees);
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
