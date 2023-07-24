<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Messages</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.message-container-left {
	border: 0px solid #ccc;
	border-top-left-radius: 0;
	border-top-right-radius: 15px;
	border-bottom-right-radius: 15px;
	border-bottom-left-radius: 15px;
	background-color: #e0e0de;
	border-width: 2px;
	color: black;
	padding: 15px;
	margin-bottom: 5px;
	text-align: left;
	width: 300px;
	margin-left: 4%;
}

.message-container-right {
	border: 0px solid #ccc;
	border-radius: 15px;
	background-color: #0377fc;
	color: white;
	border-top-left-radius: 15px;
	border-top-right-radius: 15px;
	border-bottom-right-radius: 0px;
	border-bottom-left-radius: 15px;
	padding: 15px;
	margin-bottom: 15px;
	text-align: left;
	width: 300px;
	margin-left: 73%;
}

.message-container-admin {
	border: 0px solid #ccc;
	border-radius: 15px;
	background-color: #7ae6be;
	color: black;
	border-top-left-radius: 0;
	border-top-right-radius: 15px;
	border-bottom-right-radius: 15px;
	border-bottom-left-radius: 15px;
	padding: 15px;
	margin-bottom: 5px;
	text-align: left;
	width: 300px;
	margin-left: 4%;
}

.message-info {
	font-size: 13px;
	font-weight: bold; /* Adjust the font size as desired */
}

.message-date {
	font-size: 13px;
}

.message-text {
	margin-bottom: 5px;
	font-size: 20px;
}

.messages-wrapper {
	border: 20px solid #ccc;
	border-radius: 35px;
	max-height: 510px;
	margin-top: 20px;
	width: 1200px;
	overflow: auto;
	padding: 20px;
	scrollbar-width: thin;
	scrollbar-color: #888 #f5f5f5;
}

.messages-wrapper::-webkit-scrollbar {
	width: 8px;
}

.messages-wrapper::-webkit-scrollbar-track {
	background-color: #f5f5f5;
}

.messages-wrapper::-webkit-scrollbar-thumb {
	background-color: #888;
	border-radius: 4px;
}

.messages-wrapper::-webkit-scrollbar-thumb:hover {
	background-color: #555;
}

input {
	width: 100%;
	padding: 15px;
	border-radius: 4px;
	border: 5px solid #ccc;
	border-radius: 20px;
}

input[type="submit"] {
	background-color: #007bff;
	color: white;
	cursor: pointer;
	margin-top: 10px;
}

h2 {
	text-align: center;
}

.message-box {
	background-color: #fff;
	border-radius: 4px;
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

.hidden-input {
	display: none;
}

.bottom-container {
	position: fixed;
	bottom: 20px;
	left: 200px;
	right: 40px;
	display: flex;
	justify-content: flex-end; /* Align the send button to the right */
	align-items: center;
}

.text-input {
	flex: 1;
	display: flex;
	margin-right: 10px;
}

.send-button {
	width: 90px;
}

.user-photo {
	width: 50px; /* Adjust the width as per your requirement */
	height: 45px;
	border-radius: 50%; /* Maintain the aspect ratio */
}

.imgright {
	display: inline-block;
}

.imgleft {
	display: none;
	margin-left: 69%;
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
	<h1 class="text-center mt-2">
		Batch Name :
		<%=session.getAttribute("batchName")%>
	</h1>

	<div class="container mt-6">
		<div class="row">
			<div class="col-md-30 mx-auto">
				<div class="messages-wrapper">

					<c:forEach var="message" items="${messages}">
						<c:set var="messageClass"
							value="${message.senderName eq sessionScope.username ? 'message-container-right' : (message.senderName eq 'Admin' ? 'message-container-admin' : 'message-container-left')}" />

						<c:set var="imgClass"
							value="${message.senderName eq sessionScope.username ? 'imgleft' : (message.senderName eq 'Admin' ? 'imgright' : 'imgright')}" />


						<div class="${imgClass}">
							<c:choose>
								<c:when test="${not empty message.senderImg}">
									<img src="${message.senderImg}" alt="User Photo"
										class="user-photo" />
								</c:when>
								<c:otherwise>
									<i class="fa-solid fa-user fa-2xl"></i>
								</c:otherwise>
							</c:choose>
						</div>

						<div class="${messageClass}">
							<div class="message-info">From: ${message.senderName}</div>
							<div class="message-text">${message.messageText}</div>
							<div class="message-date">Sent at: ${message.createDate}</div>
						</div>

					</c:forEach>


					<div class="container">
						<div class="message-box">
							<h2>${message}</h2>
							<c:if test="${param.success == 'true'}">
								<p class="success">Message sent successfully!</p>
							</c:if>
							<c:if test="${param.success == 'false'}">
								<p class="error">Failed to send the message. Please try
									again.</p>
							</c:if>
						</div>
					</div>

					<div class="bottom-container">
						<form class="text-input" action="sendMessage" method="post">
							<input type="hidden" name="facultyId"
								value="<%=session.getAttribute("facultyId")%>"> <input
								type="hidden" name="studentId"
								value="<%=session.getAttribute("studentId")%>"> <input
								type="hidden" name="batchId"
								value="<%=session.getAttribute("batchId")%>"> <input
								type="hidden" name="callingPage" value="page2"> <input
								name="messageText" placeholder="Type Message..."> <input
								type="submit" value="Send" class="send-button">
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>
	<script>
		window.addEventListener('DOMContentLoaded', function() {
			var messagesWrapper = document.querySelector('.messages-wrapper');
			messagesWrapper.scrollTop = messagesWrapper.scrollHeight;
		});
	</script>

	<script>
  window.onbeforeunload = function(event) {
    // Check if the event is due to a page reload
    if (event.clientY < 0) {
      // Call your servlet here
      // You can use the fetch API to make an AJAX request to the servlet
      // For example:
      fetch('/receiveMessage')
        .then(response => {
          // Handle the response if needed
        })
        .catch(error => {
          // Handle any errors
          console.error('Error calling servlet:', error);
        });
    }
  };
</script>
</body>
</html>
