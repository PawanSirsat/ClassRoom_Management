<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
/* CSS styles for the signup page */
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

select {
	width: 100%;
	padding: 5px;
	margin-bottom: 20px;
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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
	<script>
	
	function handleFacultySelection() {
		  const facultyDropdown = document.getElementById('batchdropdown');
		  const facultyInput = document.getElementById('batchinput');
		  const submitButton = document.getElementById('submitBtn');

		  // Check if a faculty is selected
		  if (facultyDropdown.value !== '') {
		    // Disable the faculty ID input
		    facultyInput.disabled = true;
		    // Disable the submit button
		    submitButton.disabled = true;
		  } else {
		    // Enable the faculty ID input
		    facultyInput.disabled = false;
		    // Enable the submit button
		    submitButton.disabled = false;
		  }
		}
	
    // Check if the duplicate status attribute is set
   // Check if the duplicate status attribute is set
var duplicateStatus = <%= session.getAttribute("check") %>;

// Check if the duplicate status is true
if (duplicateStatus) {
    // Show the pop-up message
    alert("This Student Already in Other batch.");
    <% session.setAttribute("check", false); %>   
    duplicateStatus = false;
}
    
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
	<div class="signup-container">
		<h2 class="signup-heading">New Alumni</h2>
		<form class="signup-form" action= "addAlumni" method="post">
	      <div class="form-group">
				<label for="batchid">Batch:</label> 
				<select name="batchid"
					id="batchdropdown" onchange="handleFacultySelection()">
					<option value="">Select Batch</option>
					<c:forEach var="batch" items="${ShowBatch}">
						<option value="${batch.batchId}">${batch.batchName}</option>
					</c:forEach>
				</select>
			</div>
			
			<div class="form-row">
			
				<div class="form-group">
					<input class="form-input" type="text" id="batchinput"
					placeholder="Batch Id" name="batchid" required>
				</div>
				
				<div class="form-group"> 
					<input class="form-input" type="text" id="studentid"
						placeholder="Student ID" name="studentid" required>
				</div>
				
			</div>

			<input class="form-button" type="submit" value="Add Alumni">

		</form>

	</div>

</body>
</html>