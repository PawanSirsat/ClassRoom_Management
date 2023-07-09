<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Student Information</title>
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

select {
	width: 100%;
	padding: 5px;
	margin-bottom: 20px;
}

input[type="submit"] {
	padding-top: 50px;
	padding: 10px 20px;
	background-color: #4CAF50;
	color: #fff;
	border: none;
	cursor: pointer;
}

table {
	width: 400px;
	margin: 20px auto;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	text-align: left;
	border-bottom: 1px solid #ddd;
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
		<h2 class="signup-heading">Select Batch</h2>
		<form class="signup-form" action="showBatch" method="post">

			<div class="form-group">
				<label for="batchid">Batch:</label> <select name="batchid"
					id="batchdropdown" onchange="handleFacultySelection()">
					<option value="">Select Batch</option>
					<c:forEach var="batch" items="${ShowBatch}">
						<option value="${batch.batchId}">${batch.batchName}</option>
					</c:forEach>
				</select> <br> <br> <input type="submit" value="Submit">
			</div>
		</form>
		<hr>
		<form class="signup-form" action="showBatch" method="post">

			<div class="form-group">
				<label for="batchid">Enter Batch ID:</label> <br> <input
					type="text" name="batchid" id="batchinput"
					placeholder="Enter Batch Number"> <br> <br> <input
					type="submit" id="submitBtn" value="Submit">
			</div>
		</form>
	</div>


</body>
</html>
