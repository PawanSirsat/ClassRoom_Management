<%@ page import="JwtAuthentication.AuthenticationPoint"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	margin: 0;
}

.navbar {
	z-index: 2;
	background-color: rgb(33, 37, 41);
	padding: 20px;
	height: 80px;
	width: 100%;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.navbar-brand {
	font-size: 60px;
	font-weight: bold;
	font-family: 'Press Start 2P', cursive;
	color: #4CAF50;
}

.navbar-items-left {
	display: flex;
	align-items: center;
}

.navbar-items-right {
	display: flex;
	align-items: center;
}

#itemAdmin {
	color: #0b59e0; /* Text color */
	font-family: "Arial", sans-serif; /* Choose a suitable font family */
	font-size: 17px; /* Choose an appropriate font size */
	border-radius: 5px;
	font-weight: bold;
}

#itemAdmin: {
	text-decoration: none;
}

.navbar-item {
	text-decoration: none;
	margin-right: 15px;
	font-size: 15px;
	font-weight: bold;
	color: white;
}

button {
	text-decoration: none;
}

.navbar-item:hover {
	text-decoration: none;
}

.with-border {
	border: 4px solid #4CAF50;
	border-radius: 600px;
	text-decoration: none;
	padding: 3px;
}

.non {
	text-decoration: none;
}

.non:hover {
	text-decoration: none;
}

.signup-button1 {
	text-decoration: none;
	padding: 5px 10px;
	color: white;
	border: none;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
	border: 5px solid #4CAF50;
	border-radius: 25px;
	margin-right: 10px;
}

.signup-button1:hover {
	text-decoration: none;
}

.signin-button:hover {
	text-decoration: none;
}

.profile-image2 {
	display: block;
	margin: 0 0px;
	width: 50px;
	height: 50px;
	border-radius: 50%;
	object-fit: cover;
}

.bt1 {
	margin-right: 10px;
}

.message-button {
	text-decoration: none;
	padding: 5px 10px;
	color: #036ffc;
	border: none;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
	border-radius: 25px;
	margin-left: auto;
}

.challenge-button:hover {
    text-decoration: none;
    color: #06c46c;
}

.challenge-button:focus {
    outline: none; /* Added to remove the inner line when focused */
}

.btn1-button:hover {
    text-decoration: none;
    color: #06c46c;
}

.btn1-button:focus {
    outline: none; /* Added to remove the inner line when focused */
}
.challenge-button {
	text-decoration: none;
	padding: 5px 10px;
	color: #06c46c;
	border: none;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
	border-radius: 25px;
	margin-left: auto;
}

.assignment-button {
	text-decoration: none;
	padding: 5px 10px;
	color: #f79320;
	border: none;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
	border-radius: 25px;
	margin-left: auto;
}

.signin-button {
	text-decoration: none;
	padding: 5px 10px;
	color: #4CAF50;
	border: none;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
	border-radius: 25px;
	margin-left: auto;
}

.signout-button {
	text-decoration: none;
	padding: 5px 10px;
	color: red;
	border: none;
	text-decoration: none;
	cursor: pointer;
	font-weight: bold;
	border-radius: 25px;
	margin-left: auto;
}
</style>
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
</head>
<body>
	
	<div class="navbar" style="position: fixed; top: 0; left: 0;">
		<div class="navbar-items-left">
			<span class="navbar-brand">Codenera</span> <a class="navbar-item"
				href="index.jsp"><i class="fa-solid fa-house"></i> Home</a> <a
				class="navbar-item" href="About.jsp">About</a>
			<%
			String token = (String) session.getAttribute("jwtToken");
			if (!AuthenticationPoint.validateToken(token)) {
			%>
			<a id="itemAdmin" class="non" href="AdminLogin.jsp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<i class="fa-solid fa-lock fa-beat non"></i> Admin Login
			</a> <a id="itemAdmin" class="non" href="FacultyLogin.jsp">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<i class="fa-solid fa-user-plus non"></i> Team Login
			</a>
			<%
			} else {
			%>

			<form action="receiveMessage" method="post" class="message-button">
				<input type="hidden" name="callingPage" value="page1"> <input
					type="hidden" name="batchId" id="batchId"
					value="<%=session.getAttribute("batchId")%>" class="hidden-input">
				<button type="submit" class="message-button">
					<i class="fa-solid fa-message fa-beat"></i> Messages
				</button>
			</form>

			<form action="showAssignmentStd" method="post"
				class="assignment-button">
				<button class="assignment-button" type="submit">
					<i class="fa-solid fa-book"></i> Assignments
				</button>
			</form>
			<button class="challenge-button">
				<a class="challenge-button" href="Addchallenge.jsp"> <i
					class="fa-solid fa-plus"></i> Add Challenge
				</a>
			</button>
			<%
			}
			%>


		</div>
		<div class="navbar-items-right">
			<%
			if (!AuthenticationPoint.validateToken(token)) {
			%>
			<a class="signup-button1" href="signup.html">Sign Up</a> <a
				class="signup-button1" href="Studentlogin.html">Sign In</a>

			<%
			} else {
			%>

		<div class="bt1 d-sm-flex align-items-sm-center with-border">
    <a class="bt1 d-sm-flex align-items-sm-center" href="studentProfile" style="float: right; text-decoration: none;">
        <% 
        byte[] imageBytes = (byte[]) session.getAttribute("imageBytes");
        String base64Image = null;
        if (imageBytes != null) {
            base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
        }
        %>
        <img class="profile-image2" src="data:image/jpeg;base64, <%=base64Image%>" alt="Student Image">
        <strong class="d-none d-sm-block ms-1 non">&nbsp;&nbsp;<%=request.getSession().getAttribute("fullname")%></strong>
    </a>
</div>


			<form action="logout" method="post" class="signout-button">
				<button type="submit" class="signout-button">
					<i class="fa-solid fa-right-from-bracket fa-beat-fade"></i> Logout
				</button>
			</form>

			<%
			}
			%>
		</div>
	</div>

	<script>
		document.querySelector('.signin-button').addEventListener('click',
				function(event) {
					event.preventDefault();
					logout();
				});

		function logout() {
			sessionStorage.removeItem('username');
			document.querySelector('.signin-button').parentNode.submit();
		}
	</script>
</body>
</html>
