<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Welcome</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f8f8f8;
	margin: 0;
	padding: 0;
}

.profile-container {
	position: relative;
	width: 1000px;
	top: 30px;
	left: 250px;
	height: 530px;
	padding: 50px;
	background-color: #ffffff;
	border-radius: 25px;
	box-shadow: 0 6px 10px 3px rgba(0, 0, 0, 0.6);
}

.cover-photo {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 230px;
	object-fit: cover;
	border-top-left-radius: 25px;
	border-top-right-radius: 25px;
}

.profile-image {
	position: absolute;
	top: 250px;
	left: 120px;
	width: 150px;
	height: 150px;
	border-radius: 50%;
	object-fit: cover;
	border: 5px solid #ffffff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.profile-info {
	margin-top: 230px;
	margin-left: 350px;
	padding: 30px;
	background-color: #ffffff;
	border-radius: 10px;
}

.profile-info h2 {
	font-size: 24px;
	font-weight: bold;
	margin-bottom: 10px;
}

.profile-info p {
	font-size: 16px;
	margin-bottom: 5px;
}

.info-container {
	position: absolute;
	top: 240px;
	right: 130px;
	width: 200px;
	height: 200px;
	padding: 5px;
	background-color: #ffffff;
	border-radius: 5px;
	overflow-y: auto;
}

.additional-info p {
	margin-bottom: 5px;
}

.info-icon {
	margin-right: 10px;
}

.button-container {
	text-align: center;
	margin-top: 20px;
}

.button {
	background-color: #4CAF50;
	border: none;
	color: #fff;
	padding: 12px 24px;
	font-size: 16px;
	cursor: pointer;
	border-radius: 4px;
	text-decoration: none;
}

.button:hover {
	background-color: #45a049;
}

.name-container {
	position: absolute;
	top: 425px;
	left: 108px;
}

.name {
	font-size: 24px;
	font-weight: bold;
	margin-left: 20px;
}

.additional-info {
	position: absolute;
	top: 240px;
	right: 130px;
	padding: 10px;
}

.additional-info .info-icon {
	margin-right: 5px;
}

.stats-container {
	margin-bottom: 20px;
}

.stats-container label {
	font-weight: bold;
}

.stats-container .bar {
	width: 200px;
	height: 20px;
	background-color: #f2f2f2;
	border-radius: 5px;
	overflow: hidden;
}

.stats-container .bar .progress {
	height: 100%;
	background-color: #4CAF50;
}

.stats-container .bar .progress span {
	display: inline-block;
	height: 100%;
	width: 0;
	transition: width 1s ease-in-out;
}

.stats-container .bar .count {
	display: flex;
	align-items: center;
	justify-content: center;
	color: #ffffff;
	font-weight: bold;
	padding: 0 8px;
}
</style>

</head>

<body>
	<div id="navbarContainer"></div>
	<script>
	 fetch('FacultyNavbar.jsp')      
	 .then(response => response.text())
        .then(data => {
            // Insert the navbar content into the navbarContainer div
            document.getElementById('navbarContainer').innerHTML = data;
        })
        .catch(error => console.error('Error fetching navbar:', error));
	
	    function calculateAndDisplayResult() {
	    	const totalUsers = <%=session.getAttribute("totalUsers")%>;
	    	const totalBatches = <%=session.getAttribute("totalBatches")%>;

	    	const userPercentage = ((totalUsers / 20) * 100).toFixed(2);
	        const batchPercentage = ((totalBatches / 10) * 100).toFixed(2);
	        
	    	document.getElementById("userProgressBar").style.width = userPercentage + '%';
	    	userProgressBar.innerText = userPercentage + '%';
	    	userProgressBar.style.color = 'white';
	    	userProgressBar.style.fontWeight = 'bold';
	    	userProgressBar.style.paddingLeft = '15px';

	    	document.getElementById("batchProgressBar").style.width = batchPercentage + '%';
	    	batchProgressBar.innerText = batchPercentage + '%';
	    	batchProgressBar.style.color = 'white';
	    	batchProgressBar.style.fontWeight = 'bold';
	    	batchProgressBar.style.paddingLeft = '15px';
	    	
	    	document.getElementById("courseProgressBar").style.width = 40 + '%';
	    	courseProgressBar.innerText = 40 + '%';
	    	courseProgressBar.style.color = 'white';
	    	courseProgressBar.style.fontWeight = 'bold';
	    	courseProgressBar.style.paddingLeft = '15px';	
 }
      window.addEventListener("load", calculateAndDisplayResult);
	 </script>
	<div class="profile-container">
		<%
		byte[] imageBytes = (byte[]) session.getAttribute("imageBytes");
		String base64Image = null;
		if (imageBytes != null)
		{
			base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
		}
		%>
		<img class="cover-photo" src="Images/facultyCover.png"
			alt="Cover Photo"> <img class="profile-image"
			src="Images/FacultyLogo.png" alt="Student Image">
		<div class="name-container">
			<h2 class="name">
				<%=session.getAttribute("facultyname")%></h2>
		</div>

		<div class="profile-info">

			<div class="stats-container">
				<label><i class="fas fa-users info-icon"></i> My Total
					Students: <%=session.getAttribute("totalUsers")%></label>
				<div class="bar">
					<div id="userProgressBar" class="progress">
						<span></span>
					</div>
				</div>
			</div>
		
			<div class="stats-container">
				<label><i class="fas fa-graduation-cap info-icon"></i>My Total
					Batches: <%=session.getAttribute("totalBatches")%></label>
				<div class="bar">
					<div id="batchProgressBar" class="progress">
						<span></span>
					</div>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
