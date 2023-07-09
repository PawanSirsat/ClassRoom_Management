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

.con1{
margin-top:100px;
padding-left:100px;
padding-right:100px;
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
		</tr>
		<%
		ResultSet assignments = (ResultSet) request.getAttribute("assignments");
		while (assignments.next())
		{
			int assignmentId = assignments.getInt("assignment_id");
			String title = assignments.getString("title");
			String Desc = assignments.getString("description");
			String Date = assignments.getString("due_date");
		%>
		<tr>
			<td><%=assignmentId%></td>
			<td><%=title%></td>
			<td><%=Desc%></td>
			<td><%=Date%></td>

			<td><a href="download?assignmentId=<%=assignmentId%>" download>Download</a></td>

		</tr>
		<%
		}
		%>
	</table>
    </div>
	
</body>
</html>
