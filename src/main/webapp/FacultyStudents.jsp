<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>My Students</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f8f8;
}

.container {
	position: absolute;
	top: 120px;
	left: 180px;
	width: 600px;
	margin: 0 auto;
	padding: 20px;
	background-color: #ffffff;
	border-radius: 25px;
	box-shadow: 0 6px 10px 3px rgba(0, 0, 0, 0.6);
}

h1 {
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #dddddd;
}
</style>
</head>
<body>

	<script>
  // Fetch the navbar content
  fetch('FacultyNavbar.jsp')
    .then(response => response.text())
    .then(data => {
      // Insert the navbar content into the navbarContainer div
      document.getElementById('facultynavbar').innerHTML = data;
    })
    .catch(error => console.error('Error fetching navbar:', error));
</script>
	<div id="facultynavbar"></div>

	<div class="container">
		<h2>User List</h2>
		<table>
			<tr>
				<th>Alumni ID</th>
				<th>Alumni name</th>
				<th>Alumni Email</th>
				<th>Batch Name</th>
				<th>Batch ID</th>
			</tr>
			<c:forEach var="student" items="${students}">
				<tr>
					<td>${student.id}</td>
					<td>${student.username}</td>
					<td>${student.email}</td>
					<td>${student.batchName}</td>
					<td>${student.batchId}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
