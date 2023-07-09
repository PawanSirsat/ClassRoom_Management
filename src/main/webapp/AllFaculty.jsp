<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pojo.Faculty"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Faculty List</title>
<style>
/* styles.css */

body {
  font-family: Arial, sans-serif;
  background-color: #f2f2f2;
  margin: 0;
  padding: 0;
}

.container {
	position:absolute;
	top:100px;
	left:200px;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  background-color: #fff;
  box-shadow: 0 3px 15px rgba(0, 0, 0.3, 0.8);
}

h1 {
  text-align: center;
}

table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
}

th,
td {
  padding: 10px;
  text-align: left;
}

th {
  background-color: #f5f5f5;
  border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
  background-color: #f9f9f9;
}

tr:hover {
  background-color: #f2f2f2;
}

</style>
</head>
<body>

	<script>
  // Fetch the navbar content
  fetch('AdminNavBar.jsp')
    .then(response => response.text())
    .then(data => {
      // Insert the navbar content into the navbarContainer div
      document.getElementById('adminnavbar').innerHTML = data;
    })
    .catch(error => console.error('Error fetching navbar:', error));
</script>


	<div id="adminnavbar"></div>

	<div class="container">
		<h1>List of Faculties</h1>
		<table>
			<thead>
				<tr>
					<th>Faculty ID</th>
					<th>Faculty Name</th>
					<th>Faculty Username</th>
					<th>Faculty Password</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach var="faculty" items="${facultyList}">
					<tr>
						<td>${faculty.facultyID}</td>
						<td>${faculty.facultyName}</td>
						<td>${faculty.facultyUserName}</td>
						<td>${faculty.facultyPassword}</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>
	</div>
</body>
</html>
