package com.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/ReportCardServlet")
public class ReportCardServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/codenera";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "ieytsak7eq@123";

    String Stdid = null;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Retrieve student ID from the request parameter
            String studentId = request.getParameter("studentId");
            Stdid = studentId;

            // Get the necessary data from the database
            String batchId = getBatchIdForStudent(studentId);
            String studentName = getStudentName(studentId);
            String facultyName = getFacultyName(batchId);
            String batchName = getBatchName(batchId);
            List<Assignment> assignments = getAssignmentsForBatch(batchId);

            // Calculate total marks and total obtained marks
            int totalMarks = assignments.size() * 100;
            int obtainedMarks = 0;
            for (Assignment assignment : assignments) {
                int marks = getMarksForAssignmentAndStudent(assignment.getId(), studentId);
                obtainedMarks += marks;
            }

            // Calculate percentage
            double percentage = (double) obtainedMarks / totalMarks * 100;

            String formattedPercentage = String.format("%.2f", percentage);

            // Create the PDF document
            Document document = new Document();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);

            // Set document metadata
            document.addTitle("Report Card");
            document.addAuthor("Your Name");

            document.open();

            // Add content to the PDF document
            addReportCardContent(document, studentName, batchName, facultyName, assignments, obtainedMarks, totalMarks, formattedPercentage);

            document.close();

            // Set response headers
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=ReportCard.pdf");

            // Send the PDF to the client
            OutputStream os = response.getOutputStream();
            baos.writeTo(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error generating report card.");
        }
    }

    private String getBatchIdForStudent(String studentId) throws SQLException {
        String batchId = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT batch_id FROM alumni WHERE student_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                batchId = resultSet.getString("batch_id");
            }
        }
        return batchId;
    }

    private String getStudentName(String studentId) throws SQLException {
        String studentName = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT fullname FROM user WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                studentName = resultSet.getString("fullname");
            }
        }
        return studentName;
    }

    private String getFacultyName(String batchId) throws SQLException {
        String facultyName = null;
        String facultyId = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
        	
        	 String query1 = "SELECT faculty_id FROM batch WHERE batch_id = ?";
             PreparedStatement statement1 = connection.prepareStatement(query1);
             statement1.setString(1, batchId);
             ResultSet resultSet1 = statement1.executeQuery();
             if (resultSet1.next()) {
            	 facultyId = resultSet1.getString("faculty_id");
             }
             
            String query = "SELECT faculty_name FROM faculty WHERE faculty_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, facultyId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                facultyName = resultSet.getString("faculty_name");
            }
        }
        return facultyName;
    }
    
    private String getBatchName(String batchId) throws SQLException {
        String batchName = null;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
        	
        	 String query1 = "SELECT batch_name FROM batch WHERE batch_id = ?";
             PreparedStatement statement1 = connection.prepareStatement(query1);
             statement1.setString(1, batchId);
             ResultSet resultSet1 = statement1.executeQuery();
             if (resultSet1.next()) {
            	 batchName = resultSet1.getString("batch_name");
             }

        }
        return batchName;
    }

    private List<Assignment> getAssignmentsForBatch(String batchId) throws SQLException {
        List<Assignment> assignments = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT assignment_id, title, due_date FROM assignments WHERE batch_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, batchId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int assignmentId = resultSet.getInt("assignment_id");
                String title = resultSet.getString("title");
                String dueDate = resultSet.getString("due_date");
                Assignment assignment = new Assignment(assignmentId, title, dueDate);
                assignments.add(assignment);
            }
        }
        return assignments;
    }

    private int getMarksForAssignmentAndStudent(int assignmentId, String studentId) throws SQLException {
        int marks = 0;
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String query = "SELECT marks FROM submission WHERE assignment_id = ? AND std_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, assignmentId);
            statement.setString(2, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                marks = resultSet.getInt("marks");
            }
        }
        return marks;
    }

    private void addReportCardContent(Document document, String studentName, String batchName, String facultyName,
            List<Assignment> assignments, int obtainedMarks, int totalMarks, String percentage) throws DocumentException, SQLException {
        // Implement this method to add the content to the PDF document
        // You can use iTextPDF's methods to add text, tables, and other elements to the document
        // For example, you can create a table for the assignment details and marks
        // You can also add the student name, batch name, faculty name, obtained marks, total marks, and percentage
        // to the document as text
        // Here's a sample code to get you started, customize it as per your requirement:
        Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
        Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

        Paragraph heading = new Paragraph("Report Card", headingFont);
        heading.setAlignment(Element.ALIGN_CENTER);
        document.add(heading);

        Paragraph studentInfo = new Paragraph("Student Name: " + studentName + "\nBatch Name: " + batchName
                + "\nFaculty Name: " + facultyName+"\n ",contentFont);
        document.add(studentInfo);

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setWidths(new int[] { 2, 3, 3, 2 });

        PdfPCell cell;

        cell = new PdfPCell(new Phrase("Assignment ID", contentFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Title", contentFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Due Date", contentFont));
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Marks", contentFont));
        table.addCell(cell);

        for (Assignment assignment : assignments) {
            cell = new PdfPCell(new Phrase(String.valueOf(assignment.getId()), contentFont));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(assignment.getTitle(), contentFont));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase(assignment.getDueDate(), contentFont));
            table.addCell(cell);

            int marks = getMarksForAssignmentAndStudent(assignment.getId(), Stdid);
            cell = new PdfPCell(new Phrase(String.valueOf(marks), contentFont));
            table.addCell(cell);
        }

        document.add(table);

        Paragraph totalMarksParagraph = new Paragraph("Total Obtained Marks: " + obtainedMarks + "/" + totalMarks,
                contentFont);
        document.add(totalMarksParagraph);

        Paragraph percentageParagraph = new Paragraph("Percentage: " + percentage + "%", contentFont);
        document.add(percentageParagraph);
    }

    private class Assignment {
        private int id;
        private String title;
        private String dueDate;

        public Assignment(int id, String title, String dueDate) {
            this.id = id;
            this.title = title;
            this.dueDate = dueDate;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getDueDate() {
            return dueDate;
        }
    }
}

