package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StudentDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.Message;
import com.sql.JDBC;

import Encryption.CodeGenerator;
import Encryption.Encrypt;
import JwtAuthentication.AuthenticationPoint;

@WebServlet("/sendMessage")
public class SendMessage extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	String url = "jdbc:mysql://localhost:3306/codenera";
	String user = "root";
	String password = "ieytsak7eq@123";

	StudentDao sdao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		int loggedInFacultyId = (int) request.getSession().getAttribute("facultyId");	
		int loggedInStudentId = (int) request.getSession().getAttribute("studentId");

		String batchName = null;	
		int batchId = Integer.parseInt(request.getParameter("batchId"));
		
		String messageEncrypt = request.getParameter("messageText");	
		CodeGenerator convert = new CodeGenerator();
		String messageText = convert.Generator(messageEncrypt);
		String Decryption = Encrypt.Generator(convert,messageText);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String serializationData = null;
		try
		{
			serializationData = objectMapper.writeValueAsString(convert);
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		
		String callingPage = request.getParameter("callingPage");

		HttpSession session = request.getSession();
		String token = (String) session.getAttribute("jwtToken");

		if (token != null && AuthenticationPoint.validateToken(token))
		{
			
			try
			{
				sdao = new StudentDao(JDBC.getConnection());
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
			

			List<Message> messages = sdao.send_message(loggedInFacultyId,loggedInStudentId,batchId,messageText,callingPage,serializationData);

			request.setAttribute("messages", messages);

			if (sdao.rowsAffected > 0)
			{
				request.getRequestDispatcher("StudentMessage.jsp?success=true").forward(request, response);
			} else
			{
				request.getRequestDispatcher("StudentMessage.jsp?success=false").forward(request, response);
			}
			
		} else
		{
			session.invalidate();
			response.sendRedirect("Studentlogin.html");
		}
		
	}
}
