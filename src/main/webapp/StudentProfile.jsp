<!DOCTYPE html>
<html>
<head>
<title>Welcome</title>

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
	top: 100px;
	left: 250px;
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
	height: 200px;
	object-fit: cover;
	border-top-left-radius: 25px;
	border-top-right-radius: 25px;
}

.profile-image {
	position: absolute;
	top: 130px;
	left: 50px;
	width: 150px;
	height: 150px;
	border-radius: 50%;
	object-fit: cover;
	border: 5px solid #ffffff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
}

.profile-info {
	margin-top: 170px;
	margin-left: 230px;
	padding: 40px;
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 6px 10px 3px rgba(0, 0, 0, 0.3);
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
	display: flex;
	align-items: center;
	margin-top: 5px;
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
	top: 285px;
	left: 37px;
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
	padding: 20px;
}

.additional-info p {
	margin-bottom: 10px;
}

.additional-info .info-icon {
	margin-right: 5px;
}

.pay-button {
	background-color: #18BD5B;
	color: white;
	border: none;
	padding: 10px 20px;
	margin-left: 5px;
	font-size: 16px;
	border-radius: 5px;
	cursor: pointer;
}

.edit-button {
	background-color: #5BC0DE;
	color: white;
	border: none;
	padding: 10px 20px;
	margin-left: 5px;
	font-size: 16px;
	border-radius: 5px;
	cursor: pointer;
}

.green-text {
	color: #11c20e;
}

.red-text {
	color: red;
}

.button {
	display: inline-block;
	padding: 6px 10px;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none;
	transition: transform 0.2s, box-shadow 0.2s;
}

.button-icon {
	margin-right: 5px;
}

.delete-button {
	display: inline-block;
	background-color: #4287f5; /* Redish color */
	color: white;
	padding: 10px 10px;
	margin-left: 5px;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none; /* Remove underline from the anchor tag */
}

.button:hover {
	transform: scale(1.1); /* Increase size on hover */
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2); /* Add shadow on hover */
	color: white; /* Change text color to white on hover */
}

.delete-button:hover {
	text-decoration: none;
}
</style>

</head>

<body>
	<div id="navbarContainer"></div>
	<script>
        fetch('/FirstWeb2/WelcomeNavBar.jsp')
        .then(response => response.text())
        .then(data => {
            // Insert the navbar content into the navbarContainer div
            document.getElementById('navbarContainer').innerHTML = data;
        })
        .catch(error => console.error('Error fetching navbar:', error));
    </script>

	<div class="profile-container">
		<%
		byte[] imageBytes = (byte[]) session.getAttribute("imageBytes");
		String base64Image = null;
		if (imageBytes != null) {
			base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);
		}
		%>
		<img class="cover-photo" src="Images/cover.png" alt="Cover Photo">
		<img class="profile-image"
			src="data:image/jpeg;base64, <%=base64Image%>" alt="Student Image">

		<div class="name-container">
			<h2 class="name"><%=session.getAttribute("fullname")%></h2>
		</div>

		<div class="profile-info">
			<div class="info-container">
				<i class="fas fa-envelope info-icon"></i>
				<p>
					Email:
					<%=session.getAttribute("email")%></p>
			</div>
			<div class="info-container">
				<i class="fas fa-birthday-cake info-icon"></i>
				<p>
					Student Age:
					<%=session.getAttribute("age")%></p>
			</div>

			<div class="info-container">
				<i class="fas fa-phone info-icon"></i>
				<p>
					Phone:
					<%=session.getAttribute("phone")%></p>
			</div>
			<div class="info-container">
				<i class="fas fa-book info-icon"></i>
				<p>
					Course:
					<%=session.getAttribute("course")%></p>
			</div>
			<div class="info-container">
				<i class="fas fa-map-marker-alt info-icon"></i>
				<p>
					Current City:
					<%=session.getAttribute("city")%></p>
			</div>
			<div class="info-container">
				<i class="fas fa-book info-icon"></i>
				<p>
					Course Fees: <span class="green-text "><strong
						class=" formattedOutput"><%=session.getAttribute("coursefees")%></strong></span>
				</p>
			</div>

			<div class="info-container">
				<a href="Payment.jsp">
					<button class="pay-button">
						<strong>Pay <span class=" formattedOutput"><%=session.getAttribute("unpaidfees")%></span></strong>
					</button>
				</a>
				<form action="editStudent" method="get">
					<input type="hidden" name="stdid"
						value="<%=session.getAttribute("studentId")%>">
					<button class="edit-button" type="submit">
						<i class="fas fa-edit"></i>&nbsp;&nbsp;<strong>Edit
							Profile</strong>
					</button>
				</form>
				<form action="studentTransaction" method="get">
					<input type="hidden" name="stdid"
						value="<%=session.getAttribute("studentId")%>">
					<button class="edit-button" type="submit">
						<i class="fas fa-list"></i>&nbsp;&nbsp;<strong>Transactions
						</strong>
					</button>
				</form>


				<a
					href="ReportCardServlet?studentId=<%=session.getAttribute("studentId")%>"
					class="button delete-button"> <i class="fa fa-file button-icon"></i>Report
					Card
				</a>

			</div>

			<div class="additional-info">
				<p>
					<i class="fas fa-university info-icon"></i> Batch Name:
					<%=session.getAttribute("batchName")%></p>

				<p>
					<i class="fas fa-book info-icon"></i> Course:
					<%=session.getAttribute("batchcourse")%></p>
				<p>
					<i class="fas fa-calendar-alt info-icon"></i> Batch Year:
					<%=session.getAttribute("batchyear")%></p>
				<p>
					<i class="fas fa-users info-icon"></i> Batch Faculty:
					<%=session.getAttribute("batchfaculty")%></p>

				<p>
					<i class="fa fa-credit-card-alt" aria-hidden="true"></i> Paid Fees:
					<span class="green-text "><strong class=" formattedOutput"><%=session.getAttribute("paidfees")%></strong></span>
				</p>
				<p>
					<i class="fa fa-credit-card-alt" aria-hidden="true"></i> Unpaid
					Fees: <span class="red-text "><strong
						class="formattedOutput"> <%=session.getAttribute("unpaidfees")%>
					</strong></span>
				</p>

			</div>
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
