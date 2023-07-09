package com.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dao.StudentDao;
import com.pojo.Student;
import com.sql.JDBC;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
@WebServlet("/signup")
public class StudentSignup extends HttpServlet
{
	StudentDao sdao;
	public void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException
	{
		
		try
		{
		sdao = new StudentDao(JDBC.getConnection());
		
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String username = req.getParameter("username");
		String FullName = req.getParameter("fullname");
		String email = req.getParameter("email");
		String pass = req.getParameter("password");
		String mobileS = req.getParameter("mobile");
		int mobile = Integer.parseInt(mobileS);
		String city = req.getParameter("city");
		String ageS = req.getParameter("age");
		int age = Integer.parseInt(ageS);
		String course = req.getParameter("course");
		Part filePart = req.getPart("imageFile");
		InputStream fileInputStream = filePart.getInputStream();
		ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();

		byte[] buffer = new byte[8192];
		int bytesRead;
		while ((bytesRead = fileInputStream.read(buffer)) != -1)
		{
			byteOutputStream.write(buffer, 0, bytesRead);
		}

		byte[] imageBytes = byteOutputStream.toByteArray();

		PrintWriter pw = response.getWriter();
		Student users = new Student(username,email,city,mobile,age,course,imageBytes,FullName,pass);

		if(sdao.Signup(users))
		{
			RequestDispatcher dispatcher = req.getRequestDispatcher("Studentlogin.html");
	        dispatcher.forward(req, response);	
		}
		else {
			response.sendRedirect("signup.html");
		}
		
	}
}

