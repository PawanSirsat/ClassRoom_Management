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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pojo.Transaction;

@WebServlet("/downloadTransaction")
public class DownloadTransaction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve transaction details from the database (code omitted for brevity)
        List<Transaction> transactionDetailsList = retrieveTransactionDetails();

        // Generate PDF
        Document document = new Document();

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, baos);
            document.open();

            // Add title
            Paragraph title = new Paragraph("Transaction Details", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add table
            PdfPTable table = new PdfPTable(5);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            // Add table headers
            addTableHeader(table);

            // Add table rows
            addTableRows(table, transactionDetailsList);

            document.add(table);
            document.close();

            // Set response headers
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=transaction_details.pdf");

            // Write PDF content to response output stream
            OutputStream out = response.getOutputStream();
            baos.writeTo(out);
            out.flush();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private List<Transaction> retrieveTransactionDetails() {
    	List<Transaction> transactionDetailsList = new ArrayList<>();

		String url = "jdbc:mysql://localhost:3306/codenera";
		String user = "root";
		String password = "ieytsak7eq@123";

		try (Connection con = DriverManager.getConnection(url, user, password))
		{
			String query = "SELECT a.student_id, u.fullname AS student_name, b.batch_name, t.transaction_date, t.payment_amount "
					+ "FROM alumni a " + "INNER JOIN user u ON a.student_id = u.id "
					+ "INNER JOIN batch b ON a.batch_id = b.batch_id " + "INNER JOIN transaction t ON u.id = t.user_id";

			PreparedStatement pstmt = con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next())
			{
				int studentId = rs.getInt("student_id");
				String studentName = rs.getString("student_name");
				String batchName = rs.getString("batch_name");
				String transactionDate = rs.getString("transaction_date");
				int paymentAmount = rs.getInt("payment_amount");

				Transaction transactionDetails = new Transaction(studentId, studentName, batchName, transactionDate,
						paymentAmount);
				transactionDetailsList.add(transactionDetails);
			}
		return transactionDetailsList;
		} catch (SQLException e)
		{
			e.printStackTrace();

		}
		return transactionDetailsList;
    }

    private void addTableHeader(PdfPTable table) {
        table.addCell("Student ID");
        table.addCell("Student Name");
        table.addCell("Batch Name");
        table.addCell("Transaction Date");
        table.addCell("Payment Amount");
    }

    private void addTableRows(PdfPTable table, List<Transaction> transactionDetailsList) {
        for (Transaction transaction : transactionDetailsList) {
            table.addCell(String.valueOf(transaction.getStudentId()));
            table.addCell(transaction.getStudentName());
            table.addCell(transaction.getBatchName());
            table.addCell(transaction.getTransactionDate());
            table.addCell(String.valueOf(transaction.getPaymentAmount()));
        }
    }
}

