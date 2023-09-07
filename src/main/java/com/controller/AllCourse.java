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

import com.pojo.Course;

@WebServlet("/allCourse")
public class AllCourse extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Course> courselist = new ArrayList<>();

        String url = "jdbc:mysql://localhost:3306/codenera";
        String user = "root";
        String password = "ieytsak7eq@123";

        try (Connection con = DriverManager.getConnection(url, user, password)) {
            String query = "Select * from Courses;";
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                course.setCourseFees(rs.getInt("fees_structure"));

                courselist.add(course);
            }
            request.setAttribute("courseList", courselist);
            request.getRequestDispatcher("AddCourse.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
