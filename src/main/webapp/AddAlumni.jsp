<%@page import="com.pojo.Student"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
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
	top: 160px;
	position: absolute;
	left: 296px;
	padding: 40px;
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 8px 12px 5px rgba(0, 0, 0, 0.6);
}

.list-container {
	left: 713px;
	margin-bottom: 15px;
	max-height: 316px;
	overflow-y: auto;
	border-radius: 10px;
	position: relative;
	width: 555px;
	top: 83px;
	left: 850px;
	padding: 16px;
	background-color: white;
	box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.80);
}

::-webkit-scrollbar {
	width: 8px;
	background-color: #f5f5f5;
}

::-webkit-scrollbar-thumb {
	background-color: #888;
	border-radius: 4px;
}

::-webkit-scrollbar-thumb:hover {
	background-color: #555;
}
/* WebKit Scrollbar Styling (Chrome, Safari, Opera) */
.table-container::-webkit-scrollbar {
	width: 12px; /* Width of the scrollbar */
}

.table-container::-webkit-scrollbar-track {
	background: #f1f1f1; /* Track background color */
}

.table-container::-webkit-scrollbar-thumb {
	background-color: #888; /* Scrollbar thumb color */
	border-radius: 6px; /* Rounded corners on the thumb */
	border: 1px solid #f1f1f1; /* Border around the thumb */
}

.table-container::-webkit-scrollbar-thumb:hover {
	background-color: #555; /* Thumb color on hover */
}

h1 {
	text-align: center;
}

table {
	width: 80%;
	margin: 0 auto;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	border: 1px solid #ccc;
	text-align: center;
}

th {
	background-color: #4CAF50;
	color: white;
	position: sticky;
	top: 0; /* Stick the header to the top of the container */
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

.select-option {
	word-spacing: 10px; /* Adjust the value to control the spacing */
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
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

</head>
<body>

<script>
  function formatOption(option) {
    const maxLength = 20; // Adjust as needed for your layout
    const text = option.textContent;
    const padding = ' '.repeat(maxLength - text.length);
    return text + padding;
  }

  window.addEventListener('DOMContentLoaded', () => {
    const select = document.getElementById('batchdropdown');
    const options = select.getElementsByTagName('option');
    for (let i = 0; i < options.length; i++) {
      options[i].textContent = formatOption(options[i]);
    }
  });
</script>

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
var duplicateStatus = <%=session.getAttribute("check")%>;

// Check if the duplicate status is true
if (duplicateStatus) {
    // Show the pop-up message
    alert("This Student Already in Other batch.");
    <%session.setAttribute("check", false);%>   
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
		<form class="signup-form" action="addAlumni" method="post">
			<div class="form-group">
				<label for="batchid">Batch:</label> <select name="batchid"
					id="batchdropdown" onchange="handleFacultySelection()" name="batch">
					<option value="">Select Batch</option>
					<c:forEach var="batch" items="${ShowBatch}">
						<option value="${batch.batchId}">${batch.batchName}_________	
							${batch.batchcourse} _________&#8377;${batch.coursefees}</option>
					</c:forEach>
				</select>

			</div>

			<div class="form-row">

				<!--<div class="form-group">
					<input class="form-input" type="text" id="batchinput"
					placeholder="Batch Id" name="batchid" required>
				</div>-->

				<div class="form-group">
					<input class="form-input" type="text" id="studentid"
						placeholder="Student ID" name="studentid" required>
				</div>

			</div>

			<input class="form-button" type="submit" value="Add Alumni">

		</form>

	</div>

	<div class="list-container">
		<h1>Unassigned Student</h1>
		<table>
			<thead>
				<tr>
					<th>Student ID</th>
					<th>Student Name</th>
					<th>Course Preference</th>
				</tr>
			</thead>
			<tbody>
				<%
				for (Student stdStudent : (List<Student>) request.getAttribute("studentinfo"))
				{
				%>
				<tr>
					<td><%=stdStudent.getId()%></td>
					<td><%=stdStudent.getFullName()%></td>
					<td><%=stdStudent.getCourse()%></td>

				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

</body>
</html>