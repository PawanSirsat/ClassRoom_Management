<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
.navbar {
	background-color: #8a8888;
}

.message-button {
	text-decoration: none;
	padding: 9px 13px;
	margin: 10px;
	color: #036ffc;
	border: none;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
	border-radius: 25px;
	margin-left: auto;
	color: #036ffc;
}

.challenge-button {
	text-decoration: none;
	padding: 9px 13px;
	color: #0fbd2d;
	margin: 10px;
	border: none;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
	border-radius: 25px;
	margin-left: auto;
}

.assignment-button {
	text-decoration: none;
	padding: 9px 9px;
	color: #f79320;
	border: none;
	text-decoration: none;
	cursor: pointer;
	margin: 10px;
	font-weight: bold;
	border-radius: 25px;
	margin-left: auto;
}

.list-button {
	text-decoration: none;
	padding: 9px 9px;
	color: #04b2d1;
	border: none;
	text-decoration: none;
	cursor: pointer;
	margin: 10px;
	font-weight: bold;
	border-radius: 25px;
	margin-left: auto;
}

.signout-button {
	text-decoration: none;
	padding: 9px 9px;
	color: red;
	border: none;
	text-decoration: none;
	cursor: pointer;
	margin: 10px;
	font-weight: bold;
	border-radius: 25px;
	margin-left: 20px;
}

.navbar-brand {
	color: #007bff;
	font-weight: bold;
}

.nav-link {
	color: #343a40;
}

.nav-link:hover, .nav-link:focus {
	color: #007bff;
}

.active {
	color: #007bff;
	font-weight: bold;
}

.btn-link {
	color: #007bff;
	text-decoration: none;
}

.btn-link:hover, .btn-link:focus {
	color: #0056b3;
	text-decoration: underline;
}
</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,500,0,0" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Alata&family=Gloria+Hallelujah&family=Josefin+Sans:wght@600&family=Lexend+Mega&family=Permanent+Marker&family=Press+Start+2P&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">


</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container-fluid">
			<a class="navbar-brand" href="AdminWelcome.jsp"> Admin Dashboard</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">

					<li class="nav-item">
						<form action="allStudent" method="post">
							<button class="challenge-button" type="submit">
								<i class="fa fa-user" aria-hidden="true"></i>&nbsp;
								Student
							</button>
						</form>
					</li>

					<li class="nav-item">
						<form action="allbatchname" method="get">
							<input type="hidden" name="callingPage" value="page2">
							<button class="message-button" type="submit">
								<i class="fa-solid fa-message fa-beat"></i>&nbsp;
								Messages
							</button>
						</form>
					</li>

					<li class="nav-item">
						<form action="facultyname" method="get">
							<input type="hidden" name="callingPage" value="faculty1">
							<button class="assignment-button" type="submit">
								<i class="fa-solid fa-plus"></i>Add Batch
							</button>
						</form>
					</li>

					<li class="nav-item">
						<form action="allbatchname" method="get">
							<input type="hidden" name="callingPage" value="page1">
							<button class="assignment-button" type="submit">
								<i class="fa-solid fa-plus"></i>Add Alumni
							</button>
						</form>
					</li>
					
					<li class="nav-item"><a href="AddCourse.jsp">
							<button class="assignment-button" type="submit">
								<i class="fa-solid fa-plus"></i>Add Course
							</button>
					</a></li>

					<li class="nav-item"><a href="AddFaculty.jsp">
							<button class="assignment-button" type="submit">
								<i class="fa-solid fa-plus"></i>Add Faculty
							</button>
					</a></li>
					<li class="nav-item">
						<form action="facultyname" method="get">
							<input type="hidden" name="callingPage" value="faculty2">
							<button class="list-button" type="submit">
								<i class="fa fa-list"></i>&nbsp;&nbsp; Faculty
							</button>
						</form>
					</li>
					<li class="nav-item">
						<form action="allTransaction" method="get">
							<button class="list-button" type="submit">
								<i class="fa fa-list"></i>&nbsp;&nbsp; Transactions
							</button>
						</form>
					</li>
					<li class="nav-item">
						<form action="allbatchname" method="get">
							<input type="hidden" name="callingPage" value="page3">
							<button class="list-button" type="submit">
								<i class="fa fa-list"></i>&nbsp;&nbsp;Batches
							</button>
						</form>
					</li>

					<li class="nav-item">
						<form action="logout" method="post">
							<button type="submit" class="signout-button">
								<i class="fa-solid fa-right-from-bracket fa-beat-fade"></i>
								Logout
							</button>
						</form>
					</li>
					<!-- Add more buttons for other servlets if needed -->
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>