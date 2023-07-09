package com.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.AdminDao;
import com.sql.JDBC;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/adminLogin")
public class LoginAdmin extends HttpServlet
{
	AdminDao adao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = request.getParameter("adminid");
	    String password = request.getParameter("adminpassword");
	    
	    try {
	        adao = new AdminDao(JDBC.getConnection());
	        String adminName = adao.admin_login(username, password);

	        if (adminName != null) {
	            String token = AuthenticationPoint.generateToken(username, adminName);

	            HttpSession session = request.getSession();
	            session.setAttribute("jwtToken", token);
	            session.setAttribute("adminname", adminName);
	            
	            // Counting users, faculty, and batch
	            int totalUsers = adao.countUsers();
	            int totalFaculty = adao.countFaculty();
	            int totalBatches = adao.countBatches();

	            session.setAttribute("totalUsers", totalUsers);
	            session.setAttribute("totalFaculty", totalFaculty);
	            session.setAttribute("totalBatches", totalBatches);

	            System.out.println("Admin stored in session: " + adminName);
	            request.getRequestDispatcher("AdminWelcome.jsp").forward(request, response);
	        } else {
	            response.sendRedirect("AdminLogin.jsp");
	        }
	    } catch (ClassNotFoundException e1) {
	        e1.printStackTrace();
	    } catch (SQLException e1) {
	        e1.printStackTrace();
	    }
	}
}
