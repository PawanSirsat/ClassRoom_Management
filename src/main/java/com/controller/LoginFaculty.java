package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.FacultyDao;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/facultyLogin")
public class LoginFaculty extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	FacultyDao fdao;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String FaultyID = request.getParameter("facultyid");
		String FacultyPass = request.getParameter("facultypassword");
	    try {
	        fdao = new FacultyDao(JDBC.getConnection());
	        String FacultyName = fdao.faculty_login(FaultyID, FacultyPass);

	        System.out.println("Name"+FacultyName);
	        if (FacultyName != null) {
	            String token = AuthenticationPoint.generateToken(FaultyID, FacultyName);

	            HttpSession session = request.getSession();
	           
	            int faculty_id = fdao.facuty_id(FaultyID);
	            System.out.println("ID : "+faculty_id);
	     	    int myUsers = fdao.countUsers(faculty_id);
	            int myBatches = fdao.countBatches(faculty_id);
	            
	            System.out.println("COunt u: "+myUsers+" Batch: "+myBatches);

	            session.setAttribute("jwtToken", token);
	            session.setAttribute("facultyname", FacultyName);
	            session.setAttribute("totalUsers", myUsers);
	            session.setAttribute("totalBatches", myBatches);
				session.setAttribute("facultyId", faculty_id); // Assuming 'username' is the variable that holds the


	            System.out.println("Faculty stored in session: " + FacultyName);
	            request.getRequestDispatcher("FacultyWelcome.jsp").forward(request, response);
	        } 
	    } catch (ClassNotFoundException e1) {
	        e1.printStackTrace();
	    } catch (SQLException e1) {
	        e1.printStackTrace();
	    }

	}
}
