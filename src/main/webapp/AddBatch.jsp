<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pojo.Faculty"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<style>

.signup-container {
	width: 450px;
	top: 100px;
	position: absolute;
	left: 500px;
	padding: 40px;
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 8px 12px 5px rgba(0, 0, 0, 0.6);
}

.signup-heading {
	text-align: center;
	font-size: 24px;
	font-weight: bold;
	margin-bottom: 20px;
}

.signup-form {
	display: flex;
	flex-direction: column;
}

.form-group {
	margin-bottom: 20px;
	margin-left: 20px;
}

.form-label {
	font-weight: bold;
	margin-bottom: 4px;
}

.form-input {
	padding: 8px 16px;
	border: 1px solid #ccc;
	border-radius: 4px;
	margin: 5px;
}

.form-row {
	display: flex;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
}

.form-button {
	padding: 8px 16px;
	background-color: #4CAF50;
	color: white;
	border: none;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
	text-align: center;
}

.si5 {
	text-decoration: none; /* Remove underline by default */
	padding: 5px 10px;
	color: #4CAF50;
	border: none;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
	border: 5px solid #4CAF50;
	border-radius: 25px;
}

.si5:hover {
	text-decoration: none; /* Remove underline on hover */
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

		// Function to enable/disable the faculty ID input based on the selected option
		function handleFacultySelection() {
			const facultyDropdown = document.getElementById('facultyDropdown');
			const facultyInput = document.getElementById('facultyInput');
			
			// Check if a faculty is selected
			if (facultyDropdown.value !== '') {
				// Disable the faculty ID input
				facultyInput.disabled = true;
			} else {
				// Enable the faculty ID input
				facultyInput.disabled = false;
			}
		}
		
	</script>

	<div id="adminnavbar"></div>
	<div class="signup-container">
		<h2 class="signup-heading">New Batch</h2>
		<form class="signup-form" action="addbatch" method="post">

			<div class="form-row">
				<div class="form-group">
					<input class="form-input" type="text" id="batchname"
						placeholder="Batch Name" name="batchname" required>
				</div>
				<div class="form-group">
					<input class="form-input" type="text" id="batchyear"
						placeholder="Batch Year" name="batchyear" required>
				</div>
			</div>

			<div class="form-row">
				<div class="form-group">
					<select  class="form-input" id="course" name="course">
						<option value="">Select Course</option>
						<c:forEach var="name" items="${courseList}">
							<option value="${name.courseName}">${name.courseName} &#8377; ${name.courseFees}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-row">
				<div class="form-group">
					<select id="facultyDropdown" name="faculty" class="form-input" onchange="handleFacultySelection()">
						<option value="">Select Faculty</option>
						<c:forEach var="name" items="${facultyList}">
							<option value="${name.facultyID}">${name.facultyName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label class="form-label" for="faculty">or</label>
					<input id="facultyInput" class="form-input" type="number" placeholder="Faculty ID" name="faculty" required>
				</div>
			</div>

			<input class="form-button" type="submit" value="Add Batch">
		</form>
	</div>

</body>
</html>
