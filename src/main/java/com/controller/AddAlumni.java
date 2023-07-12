package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import JwtAuthentication.AuthenticationPoint;

@WebServlet("/addAlumni")
public class AddAlumni extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        String batchID = req.getParameter("batchid");
        int batchIdInt = Integer.parseInt(batchID);

        String studentId = req.getParameter("studentid");
        int studentIdInt = Integer.parseInt(studentId);

        PrintWriter pw = res.getWriter();

        String url = "jdbc:mysql://localhost:3306/codenera";
        String user = "root";
        String password = "ieytsak7eq@123";

        HttpSession session = req.getSession();
        String token = (String) session.getAttribute("jwtToken");
        if (token != null && AuthenticationPoint.validateToken(token)) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, password);

                // Insert into Alumni table
                String insertAlumni = "INSERT INTO alumni (batch_id, student_id) VALUES (?, ?)";
                PreparedStatement pstmtAlumni = con.prepareStatement(insertAlumni);

                pstmtAlumni.setInt(1, batchIdInt);
                pstmtAlumni.setInt(2, studentIdInt);

                pstmtAlumni.executeUpdate();

                // Find the course ID based on the user ID in the Users table
                String findCourseIdQuery = "SELECT c.course_id FROM user u " +
                        "INNER JOIN courses c ON u.course = c.course_name " +
                        "WHERE u.id = ?";
                PreparedStatement pstmtFindCourseId = con.prepareStatement(findCourseIdQuery);
                pstmtFindCourseId.setString(1, studentId);
                ResultSet rs = pstmtFindCourseId.executeQuery();

                int courseId = 0;
                if (rs.next()) {
                    courseId = rs.getInt("course_id");
                }

                // Insert into Payments table
                String insertPaymentQuery = "INSERT INTO Payments (course_id, paid_fees, user_id) VALUES (?, 0, ?)";
                PreparedStatement pstmtInsertPayment = con.prepareStatement(insertPaymentQuery);
                pstmtInsertPayment.setInt(1, courseId);
                pstmtInsertPayment.setString(2, studentId);
                pstmtInsertPayment.executeUpdate();

                System.out.println("Data Inserted");
                res.sendRedirect("AdminWelcome.jsp");
            } catch (Exception e) {
                boolean check = true;
                session.setAttribute("check", check);
                req.setAttribute("AddAlumni", check);

                res.sendRedirect("AddAlumni.jsp");

                System.out.println("Data Not Inserted");
                System.out.println(e);
            }
        } else {
            session.invalidate();
            res.sendRedirect("index.jsp");
        }
    }
}
