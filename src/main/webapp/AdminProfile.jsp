<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pojo.Payment"%>
<%@ page import="java.util.List"%>
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
	display: inline-flex;
	width: 1200px;
	top: 30px;
	left: 180px;
	height: 590px;
	padding: 40px;
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
	left: 80px;
	width: 150px;
	height: 150px;
	border-radius: 50%;
	object-fit: cover;
	border: 5px solid #ffffff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.profile-info {
	display: inline-block;
	position: relative;
	margin-top: 200px;
	margin-left: 250px;
	padding: 20px;
	background-color: #ffffff;
	border-radius: 10px;
	position: relative;
	box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.80);
}

.profile-info1 {
	display: inline-block;
	position: relative;
	margin-top: 205px;
	margin-left: 17px;
	padding-left: 25px;
	background-color: #ffffff;
	border-radius: 10px;
	position: relative;
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
	height: 250px;
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
	left: 45px;
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
	margin-bottom: 15px;
	 max-height: 200px; /* Adjust the maximum height as needed */
    overflow-y: auto;
}

.stats-container1 {
	margin-bottom: 15px;
	 max-height: 200px; /* Adjust the maximum height as needed */
    overflow-y: auto;
    border-radius: 10px;
    position: relative;
    box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.80);
}

.stats-container label {
	font-weight: bold;
}

.stats-container .bar {
	width: 200px;
	height: 20px;
	background-color: #f2f2f2;
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

::-webkit-scrollbar {
	width: 8px;
	background-color: #f5f5f5;
}

::-webkit-scrollbar-thumb {
	background-color: #888;
	border-radius: 4px;
}

::-webkit-scrollbar-thumb:hover {
	background-color: #555;
}
/* WebKit Scrollbar Styling (Chrome, Safari, Opera) */
.table-container::-webkit-scrollbar {
    width: 12px; /* Width of the scrollbar */
}

.table-container::-webkit-scrollbar-track {
    background: #f1f1f1; /* Track background color */
}

.table-container::-webkit-scrollbar-thumb {
    background-color: #888; /* Scrollbar thumb color */
    border-radius: 6px; /* Rounded corners on the thumb */
    border: 1px solid #f1f1f1; /* Border around the thumb */
}

.table-container::-webkit-scrollbar-thumb:hover {
    background-color: #555; /* Thumb color on hover */
}


table {
	border-collapse: separate;
	border-spacing: 0;
	box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.80);
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #4CAF50;
	color: white;
	position: sticky;
	top: 0; /* Stick the header to the top of the container */	
}


.collected-fees {
	padding-top: 10px;
	color: #4CAF50;
	font-weight: bold;
	color: #4CAF50;
}

.pending-fees {
	padding-top: 5px;
	color: #FF5722;
	font-weight: bold;
}

.total-fees {
	padding-top: 5px;
	color: #f0a00c;
	font-weight: bold;
}

.boldword {
	padding-top: 18px;
	color: #0F7CBB;
	font-weight: bold;
}

.cname {
	color: #5c5c5b;
	text-transform: uppercase;
	font-weight: bold;
	padding: 8px;
	text-align: left;
}
</style>

</head>

<body>
	<div id="navbarContainer"></div>
	<script>
	 fetch('AdminNavBar.jsp')      
	 .then(response => response.text())
        .then(data => {
            // Insert the navbar content into the navbarContainer div
            document.getElementById('navbarContainer').innerHTML = data;
        })
        .catch(error => console.error('Error fetching navbar:', error));
	
	    function calculateAndDisplayResult() {
	    	const totalUsers = <%=session.getAttribute("totalUsers")%>;
	    	const totalFaculty = <%=session.getAttribute("totalFaculty")%>;
	    	const totalBatches = <%=session.getAttribute("totalBatches")%>;
	    	const totalCourses = <%=session.getAttribute("totalCourses")%>;

	    	const userPercentage = ((totalUsers / 30) * 100).toFixed(2);
	        const facultyPercentage = ((totalFaculty / 15) * 100).toFixed(2);
	        const batchPercentage = ((totalBatches / 20) * 100).toFixed(2);
	        const coursePercentage = ((totalCourses / 12) * 100).toFixed(2);

	    	document.getElementById("userProgressBar").style.width = userPercentage + '%';
	    	userProgressBar.innerText = userPercentage + '%';
	    	userProgressBar.style.color = 'white';
	    	userProgressBar.style.fontWeight = 'bold';
	    	userProgressBar.style.paddingLeft = '15px';

	    	document.getElementById("facultyProgressBar").style.width = facultyPercentage + '%';
	    	facultyProgressBar.innerText = facultyPercentage + '%';
	    	facultyProgressBar.style.color = 'white';
	    	facultyProgressBar.style.fontWeight = 'bold';
	    	facultyProgressBar.style.paddingLeft = '15px';

	    	document.getElementById("batchProgressBar").style.width = batchPercentage + '%';
	    	batchProgressBar.innerText = batchPercentage + '%';
	    	batchProgressBar.style.color = 'white';
	    	batchProgressBar.style.fontWeight = 'bold';
	    	batchProgressBar.style.paddingLeft = '15px';
	    	
	    	document.getElementById("courseProgressBar").style.width = 40 + '%';
	    	courseProgressBar.innerText = coursePercentage + '%';
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
		if (imageBytes != null) {
			base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
		}
		%>
		<img class="cover-photo" src="Images/cover3.png" alt="Cover Photo">
		<img class="profile-image" src="Images/adminProfile.png"
			alt="Student Image">

		<div class="name-container">
			<h2 class="name">
				Admin :
				<%=session.getAttribute("adminname")%></h2>
		</div>

		<div class="profile-info">

			<div class="stats-container">
				<label><i class="fas fa-users info-icon"></i> Current Total
					Students: <%=session.getAttribute("totalUsers")%></label>
				<div class="bar">
					<div id="userProgressBar" class="progress">
						<span></span>
					</div>
				</div>
			</div>
			<div class="stats-container">
				<label><i class="fas fa-chalkboard-teacher info-icon"></i>
					Total Faculties: <%=session.getAttribute("totalFaculty")%></label>
				<div class="bar">
					<div id="facultyProgressBar" class="progress">
						<span></span>
					</div>
				</div>
			</div>
			<div class="stats-container">
				<label><i class="fas fa-graduation-cap info-icon"></i> Total
					Batches: <%=session.getAttribute("totalBatches")%></label>
				<div class="bar">
					<div id="batchProgressBar" class="progress">
						<span></span>
					</div>
				</div>
			</div>
			<div class="stats-container">
				<label><i class="fas fa-book info-icon"></i> Total Courses:
				<%=session.getAttribute("totalCourses")%></label>
				<div class="bar">
					<div class="progress" id="courseProgressBar">
						<span></span>
					</div>
				</div>
			</div>
		</div>

		<div class="profile-info1">
			<div class="stats-container1">
				<table>
					<tr>
						<th>Course Name</th>
						<th>Total Expected</th>
						<th>Collected Fees</th>
						<th>Pending Fees</th>
					</tr>
					<%-- Iterate through the courseFeesList attribute --%>
						<c:forEach items="${courseFeesList}" var="courseFees">
							<tr>
								<td><strong class="cname ">${courseFees.course_name}</strong></td>
								<td><strong class="total-fees formattedOutput">${courseFees.total_fees}</strong></td>
								<td><strong class="collected-fees formattedOutput">
										${courseFees.total_paidfees}</strong></td>
								<td><strong class="pending-fees formattedOutput">
										${courseFees.total_unpaidfees}</strong></td>
							</tr>
						</c:forEach>
				</table>
	
			</div>
			
				<h3 class="boldword">
					Amount Collected : <strong class="collected-fees formattedOutput"><%=session.getAttribute("totalcollected")%></strong>
				</h3>
				<h3 class="boldword">
					Amount Pending &nbsp;&nbsp;: <strong class="pending-fees formattedOutput"><%=session.getAttribute("totalpending")%></strong>
				</h3>
		</div>
	</div>
	<script>
	  function formatNumberWithCommas(number) {
    	  return number.toLocaleString("en-IN");
    	}

    	function formatAmountsWithCommas() {
    	  const formattedOutputElements = document.getElementsByClassName('formattedOutput');

    	  for (let i = 0; i < formattedOutputElements.length; i++) {
    	    const amount = formattedOutputElements[i].textContent;
    	    const parsedAmount = parseFloat(amount.replace(/₹ /, '').replace(/,/g, ''));
    	    const formattedAmount = formatNumberWithCommas(parsedAmount);

    	    formattedOutputElements[i].innerHTML = '&#8377; ' + formattedAmount;
    	  }
    	}

    	window.addEventListener('load', formatAmountsWithCommas);

	 </script>
</body>
</html>
