<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pojo.Student"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

th {
	background-color: #4CAF50;
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
	color : #4CAF50;
	font-weight: bold;
	color: #4CAF50;
}

.pending-fees {
	padding-top: 5px;
	color: #FF5722;
	font-weight: bold;
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
	<h2>Student List</h2>
	<table>
		<tr>
			<th>Alumni ID</th>
			<th>Full Name</th>
			<th>Batch Name</th>
			<th>Faculty Name</th>
			<th>Course Fees</th>
			<th>Paid Fees</th>
			<th>Unpaid Fees</th>
			<th>Username</th>
			<th>Password</th>
			<th>Mobile</th>
			<th>City</th>
			<th>Age</th>
			<th>Course</th>
			<th>Photo</th>
			<th>Edit</th>
			<th>Delete</th>

		</tr>
		<c:forEach var="user" items="${userList}">
			<tr>
				<td><strong>${user.id}</strong></td>
				<td>${user.fullName}</td>

				<td>${user.batchName}</td>
				<td>${user.facultyName}</td>
				<td><h5 class="collected-fees">
						&#8377;${user.coursefees}</h5></td>
				<td><h5 class="collected-fees">
						&#8377;${user.paidfees}</h5></td>
				<td>
					<h5 class="pending-fees">
						&#8377;${user.unpaidfees}</h5>
				</td>
				<td>${user.username}</td>
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
				<td><a href="edit?id=<c:out value='${user.id}' />">Edit</a></td>
				<td><a href="delete?id=<c:out value='${user.id}' />">Delete</a></td>

			</tr>
		</c:forEach>
	</table>
</body>
</html>
