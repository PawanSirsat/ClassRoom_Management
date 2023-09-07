<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pojo.Student"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<title>Student Information</title>
<style>
.signup-container {
    width: 256px;
    top: 85px;
    position: absolute;
    left: 950px;
    background-color: white;
    border-radius: 8px;
    padding: -13px; /* Add some padding to the container */
}


.inline-container {
    display: inline-flex; /* Display elements in the same line */
    vertical-align: middle; /* Align elements vertically in the middle */
}

select {
    /* Add any specific styling for the select element if needed */
    padding: 5px;
}

.signup-heading {
	text-align: center;
	font-size: 24px;
	font-weight: bold;
	margin-bottom: 20px;
}

.signup-form {
	flex-direction: column;
}

.form-group {
    display: inline-block; /* Make the form inline-block to keep elements on the same line */
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
	display: inline-block;
	flex-direction: row;
	align-items: center;
	justify-content: space-between;
}

select {
	width: 100%;
	padding: 5px;
	margin-bottom: 20px;
}

input[type="submit"] {
    padding-top: 50px;
    padding: 5px 19px;
    margin-left:10px;
    background-color: #4CAF50;
    color: #fff;
    border: none;
    cursor: pointer;
    border-radius:20px;
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
    background-color: #f2f2f2;
}
</style>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

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
		<form class="signup-form" action="showBatch" method="post">

			<div class="form-group">
    <div class="inline-container">
        <select name="batchid" id="batchdropdown" onchange="handleFacultySelection()">
            <option value="">Select Batch</option>
            <c:forEach var="batch" items="${ShowBatch}">
                <option value="${batch.batchId}">${batch.batchName}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Show">
    </div>
</div>

		</form>
	</div>

 <h1>Batch Information</h1>
    <table>
        <thead>
            <tr>
                <th>Batch ID</th>
                <th>Batch Name</th>
                <th>Batch Faculty</th>
                <th>Batch Course Name</th>
                <th>Batch Course Fees</th>
                <th>Batch Year</th>
                <th>Student Count</th>
                
            </tr>
        </thead>
        <tbody>
            <% for (Student student : (List<Student>) request.getAttribute("batchInfo")) { %>
                <tr>
                    <td><%= student.getBatchId() %></td>
                    <td><%= student.getBatchName() %></td>
                    <td><%= student.getFacultyName() %></td>
                    <td><%= student.getBatchcourse() %></td>
                    <td><%= student.getCoursefees() %></td>
                    <td><%= student.getBatchyear() %></td>
                    <td><%= student.getStdCount() %></td>
                    
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
