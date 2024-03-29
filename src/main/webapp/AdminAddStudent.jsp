<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>
body {
	
	justify-content: center;
	align-items: center;
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
</style>
</head>

<body>
<script>
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
		<h2 class="signup-heading">Add Student</h2>
		<form class="signup-form" action="signup" method="post"
			onsubmit="return validateForm()" enctype="multipart/form-data">

			<div class="form-row">
				<div class="form-group">
					<input class="form-input" type="text" id="username"
						placeholder="UserName" name="username" required>
				</div>
				<div class="form-group">
					<input class="form-input" type="text" id="fullname"
						placeholder="Full Name" name="fullname" required>
				</div>
			</div>

			<div class="form-row">
				<div class="form-group">
					<select class="form-input" id="course" name="course">
						<option value="">Course</option>
						<option value="java">Java</option>
						<option value="python">Python</option>
						<option value="csharp">C#</option>
					</select>
				</div>
					<div class="form-group">
					<input class="form-input" type="email" id="email"
						placeholder="Email" name="email" required>
				</div>
			</div>

			<div class="form-row">

				<div class="form-group">
					<label for="imageFile">Photo:</label> <input type="file"
						name="imageFile" id="imageFile" class="file-input"
						accept="image/*" />
				</div>
				
				<div class="form-group">
					<input class="form-input" type="number" id="age" placeholder="Age"
						name="age" required>
				</div>
			</div>



			<div class="form-row">
				<div class="form-group">
					<input class="form-input" type="number" id="mobile"
						placeholder="Phone no" name="mobile" required>
				</div>

				<div class="form-group">
					<input class="form-input" type="text" id="city" placeholder="City"
						name="city" required>
				</div>
			</div>

			<div class="form-row">


				<div class="form-group">
					<input class="form-input" type="password" id="password"
						placeholder="Password" name="password" required>
				</div>

				<div class="form-group">
					<input class="form-input" type="password" id="confirm-password"
						placeholder="Confirm Password" name="confirm-password" required>
				</div>
			</div>
			<input type="hidden" name="callingPage" value="adminPage" /> 

			<input class="form-button" type="submit" value="Add Student">

		</form>

	</div>
<script>
function validateForm() {
    var username = document.getElementById("username").value;
    var fullname = document.getElementById("fullname").value;
    var course = document.getElementById("course").value;
    var email = document.getElementById("email").value;
    var imageFile = document.getElementById("imageFile").value;
    var age = document.getElementById("age").value;
    var mobile = document.getElementById("mobile").value;
    var city = document.getElementById("city").value;
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirm-password").value;

    if (username === "") {
        alert("Username can't be empty");
        return false;
    }

    if (fullname === "") {
        alert("Full Name can't be empty");
        return false;
    }

    if (course === "") {
        alert("Please select a course");
        return false;
    }

    if (email === "") {
        alert("Email can't be empty");
        return false;
    }

    if (imageFile === "") {
        alert("Please upload a photo");
        return false;
    }

    if (age === "") {
        alert("Age can't be empty");
        return false;
    }

    if (isNaN(age)) {
        alert("Age must be a number");
        return false;
    }

    if (mobile === "") {
        alert("Phone number can't be empty");
        return false;
    }

    if (isNaN(mobile)) {
        alert("Phone number must be a number");
        return false;
    }

    if (city === "") {
        alert("City can't be empty");
        return false;
    }

    if (password === "") {
        alert("Password can't be empty");
        return false;
    }

    if (confirmPassword === "") {
        alert("Confirm Password can't be empty");
        return false;
    }

    if (password !== confirmPassword) {
        alert("Password and Confirm Password must match");
        return false;
    }

    // All validations passed
    alert("Sign Up Successful");
    return true;
}

  // Fetch the navbar content

</script>
</body>
</html>
