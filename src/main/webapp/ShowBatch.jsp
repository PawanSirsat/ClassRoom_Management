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
.user-photo {
    width: 100px; /* Adjust the width as per your requirement */
    height: 100px;
    border-radius:10px; /* Maintain the aspect ratio */
}
th, td {
	text-align: left;
	padding: 8px;
}

th {
	background-color: #4CAF50;
	color: white;
}

tr:nth-child(even) {
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
	<h2>
		Batch Name :
		<%=session.getAttribute("batchname")%></h2>
	<h2>
		Batch Year :
		<%=session.getAttribute("batchyear")%></h2>
	<h2>
		Batch Faculty :
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
