<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
body {
	margin: 0;
	padding: 0;
	justify-content: center;
	align-items: center;
	height: 100vh;
	background-image: linear-gradient(-225deg, #473B7B 0%, #3584A7 51%, #30D2BE 100%);
}

.signup-container {
	width: 700px;
	top: 100px;
	position: absolute;
	left: 400px;
	padding: 40px;
	background-color: white;
	border-radius: 8px;
	background-image: linear-gradient(-225deg, #FFFEFF 0%, #D7FFFE 100%);
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

.hidden {
	display: none;
}
</style>
</head>

<body>
	<script>
// Fetch the navbar content
        fetch('/FirstWeb2/WelcomeNavBar.jsp')
	  .then(response => response.text())
  .then(data => {
    // Insert the navbar content into the navbarContainer div
    document.getElementById('adminnavbar').innerHTML = data;
  })
  .catch(error => console.error('Error fetching navbar:', error));


	 function validateForm() {
         var username = document.getElementById("username").value;
         var password = document.getElementById("password").value;
         var confirmPassword = document.getElementById("confirm-password").value;

         if (username === "") {
             alert("Username can't be empty");
             return false;
         }

         if (password === "") {
             alert("Password can't be empty");
             return false;
         }

         if (username.length > 30) {
             alert("Username must be 30 characters or less");
             return false;
         }

         if (password.length < 6) {
             alert("Password must be at least 6 characters");
             return false;
         }

         if (password !== confirmPassword) {
             alert("Password and Confirm Password must match");
             return false;
         }

         alert("SignUp Succesfully")
         return true;
     }

</script>


	<div id="adminnavbar"></div>

	<div class="signup-container">
		<h2 class="signup-heading">Update Form</h2>
		<form class="signup-form" action="update" method="post"
			onsubmit="return validateForm()">

			<div class="form-row">
				<div class="form-group">
					<fieldset class="form-group">
						<label>User Name</label> <input type="text"
							value="<c:out value='${StdProfile.username}' />"
							class="form-control" name="username" required="required">
					</fieldset>


				</div>
				<div class="form-group">
					<fieldset class="form-group">
						<label>Full Name</label> <input type="text"
							value="<c:out value='${StdProfile.fullName}' />"
							class="form-control" name="fullname" required="required">
					</fieldset>
				</div>
			</div>

			<div class="form-row">

				<div class="form-group">
					<fieldset class="form-group">
						<label>Email</label> <input type="text"
							value="<c:out value='${StdProfile.email}' />"
							class="form-control" name="email" required="required">
					</fieldset>
				</div>

				<input type="hidden" name="id"
					value="<c:out value='${StdProfile.id}' />" />
				<div class="form-group">
					<div class="form-group">
						<fieldset class="form-group">
							<label>Age</label> <input type="text"
								value="<c:out value='${StdProfile.intage}' />"
								class="form-control" name="age" required="required">
						</fieldset>
					</div>
				</div>
			</div>

			<div class="form-row">
				<div class="form-group">
					<fieldset class="form-group">
						<label>Phone</label> <input type="text"
							value="<c:out value='${StdProfile.phone}' />"
							class="form-control" name="mobile" required="required">
					</fieldset>

				</div>

				<div class="form-group">
					<fieldset class="form-group">
						<label>City</label> <input type="text"
							value="<c:out value='${StdProfile.city}' />" class="form-control"
							name="city" required="required">
					</fieldset>
				</div>
			</div>

			<div class="form-row">
				<div class="form-group">
					<fieldset class="form-group">
						<label>Password</label> <input type="text"
							value="<c:out value='${StdProfile.password}' />"
							class="form-control" name="password" required="required">
					</fieldset>
				</div>

				<div class="form-group">
					<fieldset class="form-group">
						<label>Confirm Password</label> <input type="text"
							value="<c:out value='${StdProfile.password}' />"
							class="form-control" name="password" required="required">
					</fieldset>
				</div>
			</div>
			<input type="hidden" name="callingPage" value="studentPage" /> 
			<input
				class="form-button" type="submit" value="Save">

		</form>

	</div>

</body>
</html>
