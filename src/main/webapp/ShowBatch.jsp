<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pojo.Student"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Batch List</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 1px;
}

th {
	background-color: #5c5e5e;
	color: white;
	position: sticky;
	top: 0;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

.user-photo {
	width: 100px;
	height: 70px;
	border-radius: 10px;
}

h2 {
	text-align: center;
}

.collected-fees {
	padding-top: 10px;
	color: #4CAF50;
	font-weight: bold;
}

.course-fees {
	padding-top: 10px;
	color: #5c5e5e;
	font-weight: bold;
}

.pending-fees {
	padding-top: 5px;
	color: #FF5722;
	font-weight: bold;
}

.edit-button {
	display: inline-block;
	background-color: #08ABEA;
	color: white;
	padding: 6px 10px;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none; /* Remove underline from the anchor tag */
}

/* Styling for the icon */
.edit-icon {
	margin-right: 5px;
}

.delete-button {
	display: inline-block;
	background-color: #ff6666; /* Redish color */
	color: white;
	padding: 6px 10px;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none; /* Remove underline from the anchor tag */
}

.button {
	display: inline-block;
	padding: 6px 10px;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none;
	transition: transform 0.2s, box-shadow 0.2s;
}
.button-icon {
	margin-right: 5px;
}

.button:hover {
	transform: scale(1.1); /* Increase size on hover */
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2); /* Add shadow on hover */
	color: white; /* Change text color to white on hover */
}
/* Remove underline on hover */
.edit-button:hover, .delete-button:hover {
	text-decoration: none;
}

.full-name {
	font-weight: bold;
	color: #5c5e5e; /* Blue color */
}

.student-name {
	font-weight: 500;
	color: #08ABEA; /* Green color */
}

.faculty-name {
	font-weight: 500;
	color: #08ABEA; /* Orange color */
}

/* Styling for the pagination buttons */
.pagination-button {
	background-color: #08ABEA; /* Blue color */
	color: white;
	margin-right: 8px;
	border:none;
}

  .pagination {
    display: flex;
    justify-content: center;
    position: fixed;
    bottom: 10px;
    left: 50%;
    transform: translateX(-50%);
  }

.pagination-button:hover {
	background-color: #004c99; /* Darken the color on hover */
}

/* Hide all rows by default */
.table-row {
	display: none;
}

/* Show the rows for the selected page */
.table-row.page-active {
	display: table-row;
}
.heading-container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
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
	<h2>
		Batch Name :
		<%=session.getAttribute("batchname")%></h2>
	
	<h2>
		 Faculty Name :
		<%=session.getAttribute("batchteacher")%></h2>


	<table>
		<tr>
		    <th>ID</th>
			<th>Username</th>
			<th>Email</th>
			<th>Password</th>
			<th>Mobile</th>
			<th>City</th>
			<th>Age</th>
			<th>Course</th>
			<th>Photo</th>
		</tr>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.email}</td>
				<td>${user.password}</td>
				<td>${user.phone}</td>
				<td>${user.city}</td>
				<td>${user.age}</td>
				<td>${user.course}</td>
				<td><c:choose>
						<c:when test="${not empty user.imagePath}">
							<img src="${user.imagePath}" alt="User Photo" class="user-photo" />
						</c:when>
						<c:otherwise>
                            No image available
                        </c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
