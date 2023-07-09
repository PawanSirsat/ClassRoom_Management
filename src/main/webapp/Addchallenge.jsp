<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
    background-color: #f2f2f2;
  }
.signup-container {
	width: 700px;
	top: 100px;
	position: absolute;
	left: 400px;
	padding: 40px;
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 8px 12px 5px rgba(0, 0, 0, 0.6);
}

input[type="text"], input[type="number"], textarea, select {
	width: 100%;
	padding: 5px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

input[type="submit"] {
	margin-top: 20px;
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	border-radius: 4px;
	cursor: pointer;
}
</style>

</head>
<body>

	<script>
  // Fetch the navbar content
  fetch('WelcomeNavBar.jsp')
    .then(response => response.text())
    .then(data => {
      // Insert the navbar content into the navbarContainer div
      document.getElementById('navbarContainer').innerHTML = data;
    })
    .catch(error => console.error('Error fetching navbar:', error));
</script>
	<div id="navbarContainer"></div>

	<div class="signup-container">
		<form action="addchallenge" method="post">
			<label for="challenge-name">Challenge Name:</label> <input
				type="text" id="challenge-name" name="challengeName" required><br>

			<label for="description">Description:</label>
			<textarea id="description" name="description" rows="4" cols="50"
				required></textarea>
			<br> <label for="difficulty-level">Difficulty Level:</label> <select
				id="difficulty-level" name="difficultyLevel" required>
				<option value="Easy">Easy</option>
				<option value="Medium">Medium</option>
				<option value="Hard">Hard</option>
			</select><br> <label for="difficulty-level">Challege Type</label> <select
				id="difficulty-level" name="tags" required>
				<option value="SQL">SQL</option>
			    <option value="String">String</option>
				<option value="JavaScript">JavaScript</option>
				<option value="Pattern">Pattern</option>
				<option value="DSA">DSA</option>
				<option value="OOPS">OOPS</option>
				<option value="Array">Array</option>
			    <option value="Collection">Collection</option>
			    <option value="Thread">Thread</option>
			    
			</select><br> <input type="submit" value="Add Challenge">
		</form>

	</div>
</body>
</html>