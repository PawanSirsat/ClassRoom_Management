<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<title>Coder's Home Page</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
body {
	background-image: linear-gradient(to top, #fff1eb 0%, #ace0f9 100%);
}

.header-container {
	position: relative;	
}

/* Scrollbar Styles */
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

.container {
	width: 400px;
	padding: 20px;
	border: 1px solid #ccc;
	border-radius: 10px;
	margin: 30px;
	background-color: #fff;
}

.welcome {
	justify-content: space-between;
	width: 100%;
	height: 130px;
	text-align: center;
	background: linear-gradient(to top, rgba(142, 215, 108, -0.8),
		rgba(41, 135, 169, 1));
	z-index: 2;
}

.char {
	margin-top: 170px;
}

.welcome h1 {
	margin-top: 15px;
	text-align: center;
	font-family: 'Ubuntu', sans-serif;
}

.header {
	font-family: 'Montserrat', sans-serif;
	font-size: 20px;
	font-weight: bold;
	margin-bottom: 2px;
}

.header2 {
	margin-bottom: 2px;
	font-family: 'Roboto', sans-serif;
	font-weight: bold;
}

.header2 span {
	font-weight: normal;
}

.small-text {
	font-size: 12px;
	color: #888;
	margin-bottom: 10px;
	display: inline-block;
}

.description {
	font-size: 16px;
	background-color: #f5f5f5;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	margin-bottom: 10px;
	font-family: 'Inconsolata', monospace;
}

.date {
	font-weight: 700;
	font-size: 13px;
	color: #5e5e5e;
}

.user-photo {
	margin: 0 0px;
	width: 50px;
	height: 50px;
	border-radius: 50%;
	object-fit: cover; /* Maintain the aspect ratio */
}

.delete-button {
  position: absolute;
  top: 5px; /* Adjust the top position as desired */
  right: 5px;
  width: 80px;
  height: 30px;
  background-color: #ebe8e8;
  color: red;
    font-weight: bold;
  
  border: none;
  border-radius: 40px;
  font-size: 16px;
  text-align: center;
  z-index: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
}

button{
  cursor: pointer;

}

</style>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Alata&family=Gloria+Hallelujah&family=Itim&family=Josefin+Sans:wght@600&family=Lexend+Mega&family=Permanent+Marker&family=Press+Start+2P&family=Ubuntu:wght@500&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
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

	<div class="welcome" style="position: fixed; top: 80px; left: 0;">
		<h1>Welcome to the Coding Challenges !</h1>
	</div>

	<div class="char">
  <c:forEach items="${challenges}" var="challenge">
    <div class="container">
      <div class="header">
       <c:if test="${challenge.stdid eq sessionScope.studentId}">
        <div class="header-container">
          <a href="deletechallenge?id=<c:out value='${challenge.challengeID}' />" class="delete-link">
  <button class="delete-button" onclick="return confirm('Are you sure you want to delete this challenge?')">Delete</button>
</a>
        </div>
      </c:if>
        <c:choose>
          <c:when test="${not empty challenge.imagePath}">
            <img src="${challenge.imagePath}" alt="User Photo" class="user-photo" />
          </c:when>
          <c:otherwise>
            No image available
          </c:otherwise>
        </c:choose>
        ${challenge.studentName}
        <div class="small-text">${challenge.batchName}</div>
      </div>
      <div class="header2">
        <span>Challenge : </span> ${challenge.challengeName}
      </div>
       <div class="header2">
        <span>Type : </span> ${challenge.challengeType}
      </div>
      <div>
        <span>Difficulty : </span>
        <span style="font-weight: bold; color: red;">${challenge.difficultyLevel}</span>
      </div>
      <div class="description">${challenge.description}</div>
      <div class="date">
        <b>Posted</b> ${challenge.createdDate}
      </div>
    </div>
  </c:forEach>
</div>

</body>
</html>
