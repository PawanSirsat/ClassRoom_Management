<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Messages</title>
<style>
/* CSS styles for the messages page */
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
}

.container1 {
	width: 450px;
	top: 150px;
	position: absolute;
	left: 500px;
	padding: 40px;
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 8px 12px 5px rgba(0, 0, 0, 0.6);
}

h1 {
	text-align: center;
}



label {
	font-weight: bold;
	display: block;
	margin-bottom: 5px;
}

select, textarea, input[type="submit"] {
	width: 100%;
	padding: 8px;
	border-radius: 4px;
	border: 1px solid #ccc;
}

input[type="submit"] {
	background-color: #007bff;
	color: white;
	cursor: pointer;
}

h2 {
	text-align: center;
}

.message-box {
	background-color: #fff;
	border-radius: 4px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	padding: 20px;
}

.message {
	margin-bottom: 10px;
	padding: 10px;
	border-radius: 4px;
	background-color: #f2f2f2;
}

.message.sent {
	background-color: #e2f3ff;
}

.message.received {
	background-color: #f2f2f2;
}

.message-text {
	margin: 0;
}

.success {
	color: green;
}

.error {
	color: red;
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
	<div class="container1">
		<h1>Select Batch</h1>
		<form action="receiveMessage" method="post">
			<input type="hidden" name="facultyId" value="${facultyId}"> <label
				for="batchId">Batch:</label> <input type="hidden" name="callingPage"
				value="page2"> <select name="batchId" id="batchId">
				<option value="">Select Batch</option>
				<c:forEach var="batch" items="${batch}">
					<option value="${batch.batchId}">${batch.batchName}</option>
				</c:forEach>
			</select> <br> <br> <input type="submit" value="Select">
		</form>
	</div>
</body>
</html>
