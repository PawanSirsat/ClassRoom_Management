<%@ page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Assignments</title>
<style>
body {
	font-family: Arial, sans-serif;
}

.con1 {
	margin-top: 100px;
	padding-left: 100px;
	padding-right: 100px;
}

h1 {
	text-align: center;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
	font-weight: bold;
}

.download-link {
	display: inline-block;
	padding: 5px 10px;
	background-color: #f2f2f2;
	text-decoration: none;
	color: #000;
	border: 1px solid #ccc;
}

label {
	display: block;
	margin-bottom: 10px;
}

input[type="file"] {
	display: block;
	margin-bottom: -10px;
}

input[type="submit"] {
	background-color: #3f3f40;
	color: white;
	padding: 9px 23px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #45a049;
}
</style>
</head>
<body>

	<div id="navbarContainer"></div>

	<script>
        fetch('/FirstWeb2/WelcomeNavBar.jsp')
        .then(response => response.text())
        .then(data => {
            // Insert the navbar content into the navbarContainer div
            document.getElementById('navbarContainer').innerHTML = data;
        })
        .catch(error => console.error('Error fetching navbar:', error));
    </script>
	<div class="con1">
		<h1>Assignments</h1>
		<table>
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Description</th>
				<th>Due Date</th>
				<th>Download</th>
				<th>Status</th>
				<th>Marks</th>
				<th>Submission</th>
				<th>Faculty Status</th>
			</tr>
			<%
			ResultSet assignments = (ResultSet) request.getAttribute("assignments");
			while (assignments.next())
			{
				int assignmentId = assignments.getInt("assignment_id");
				String title = assignments.getString("title");
				String Desc = assignments.getString("description");
				String Date = assignments.getString("due_date");
				String status = assignments.getString("status");
				int marks = assignments.getInt("marks");
				String done = assignments.getString("done");
			%>
			<tr>
				<td><%=assignmentId%></td>
				<td><%=title%></td>
				<td><%=Desc%></td>
				<td><%=Date%></td>

				<td><a href="download?assignmentId=<%=assignmentId%>" download>Download</a></td>
				<td>
					<%
					if (status != null)
					{
					%> <span style="color: balck; font-size: 16px; font-weight: 500;"><%=status%></span>
					<%
					} else
					{
					%> <span style="color: red; font-size: 16px; font-weight: 500;">Missing</span>
					<%
					}
					%>
				</td>
				<td>
					<%
					if (marks != 0)
					{
					%><span style="color: Black; font-size: 16px; font-weight: 500;">100/<%=marks%></span>
					<%
					} else
					{
					%> <span style="color: Black;">100/0</span> <%
 }
 %>
				</td>
				<td>
					<%
					if (done != null && done.equals("On Time"))
					{
					%> <span style="color: #4CAF50; font-size: 18px; font-weight: 500;"><%=done%></span>
					<%
					} else if (done != null && done.equals("Done Late"))
					{
					%> <span style="color: red; font-size: 16px; font-weight: 500;"><%=done%></span>
					<%
					} else
					{
					%>
					<form action="FileUploadServlet" method="post"
						enctype="multipart/form-data">
						<input type="file" name="pdfFile" id="pdfFile" required><br>
						<input type="hidden" name="assignmentId" value="<%=assignmentId%>">
						<input type="submit" value="Turn in">
					</form> <%
 }
 %>
				</td>

				<td>
					<%
					if (marks != 0)
					{
					%> <span style="color: Green; font-size: 16px; font-weight: 500;">Checked</span>
					<%
					} else
					{
					%> <span style="color: red; font-size: 16px; font-weight: 500;">Not
						Checked</span> <%
 }
 %>
				</td>
			</tr>
			<%
			}
			%>


		</table>
	</div>

</body>
</html>
