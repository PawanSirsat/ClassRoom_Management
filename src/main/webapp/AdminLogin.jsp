<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Login</title>
<style>
@charset "ISO-8859-1";

body {
	margin: 0;
	padding: 0;
	display: flex;
	height: 100vh;
	background-image: linear-gradient(-225deg, #473B7B 0%, #3584A7 51%, #30D2BE 100%);
}

.login-container {
	margin-top: 150px;
	margin-left:600px;
	background-color: #fff;
	padding: 20px;
	height: 300px;
	width: 20%;
	border-radius: 5px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
}

h1 {
	text-align: center;
	margin-bottom: 20px;
}

form {
	display: flex;
	flex-direction: column;
}

input[type="text"], input[type="password"] {
	margin-top: 20px;
	margin-bottom: 10px;
	padding: 10px;
	border-radius: 3px;
	border: 1px solid #ccc;
}

button {
	padding: 10px;
	margin-top: 30px;
	background-color: #333;
	color: #fff;
	border: none;
	border-radius: 3px;
	cursor: pointer;
}

button:hover {
	background-color: #555;
}
</style>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Lexend+Mega&family=Permanent+Marker&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
</head>
<body>
	<div class="login-container">
		<i class="fa-solid fa-lock"></i>
		<h1>Admin Login</h1>
		<form class="login-form" action="adminLogin" method="post">
			<input type="text" placeholder="Username" id="adminid" name="adminid"
				required> <input type="password" placeholder="Password"
				id="adminpassword" name="adminpassword" required>
			<button type="submit">Login</button>
		</form>
	</div>
</body>
</html>
