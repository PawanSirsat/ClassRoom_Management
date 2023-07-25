<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pojo.Student"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User List</title>
<style>
table {
	border-collapse: collapse;
	width: 100%;
}

th, td {
	text-align: left;
	padding: 8px;
}

th {
	background-color: #5c5e5e;
	color: white;
	position: sticky;
	top: 0;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

.user-photo {
	width: 100px;
	height: 70px;
	border-radius: 10px;
}

h2 {
	text-align: center;
}

.collected-fees {
	padding-top: 10px;
	color: #4CAF50;
	font-weight: bold;
}

.course-fees {
	padding-top: 10px;
	color: #5c5e5e;
	font-weight: bold;
}

.pending-fees {
	padding-top: 5px;
	color: #FF5722;
	font-weight: bold;
}

.edit-button {
	display: inline-block;
	background-color: #08ABEA;
	color: white;
	padding: 6px 10px;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none; /* Remove underline from the anchor tag */
}

/* Styling for the icon */
.edit-icon {
	margin-right: 5px;
}

.delete-button {
	display: inline-block;
	background-color: #ff6666; /* Redish color */
	color: white;
	padding: 6px 10px;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none; /* Remove underline from the anchor tag */
}

.button {
	display: inline-block;
	padding: 6px 10px;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none;
	transition: transform 0.2s, box-shadow 0.2s;
}
/* Styling for the icons */
.button-icon {
	margin-right: 5px;
}

.button:hover {
	transform: scale(1.1); /* Increase size on hover */
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.2); /* Add shadow on hover */
	color: white; /* Change text color to white on hover */
}
/* Remove underline on hover */
.edit-button:hover, .delete-button:hover {
	text-decoration: none;
}

.full-name {
	font-weight: bold;
	color: #5c5e5e; /* Blue color */
}

.student-name {
	font-weight: 500;
	color: #08ABEA; /* Green color */
}

.faculty-name {
	font-weight: 500;
	color: #08ABEA; /* Orange color */
}

/* Styling for the pagination buttons */
.pagination-button {
	background-color: #08ABEA; /* Blue color */
	color: white;
	margin-right: 8px;
	border:none;
}

  .pagination {
    display: flex;
    justify-content: center;
    position: fixed;
    bottom: 10px;
    left: 50%;
    transform: translateX(-50%);
  }

.pagination-button:hover {
	background-color: #004c99; /* Darken the color on hover */
}

/* Hide all rows by default */
.table-row {
	display: none;
}

/* Show the rows for the selected page */
.table-row.page-active {
	display: table-row;
}
.heading-container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 20px;
  }

  /* Styling for the "Add" button */
  .add-button {
    margin-left:50px;
	background-color: #08ABEA;
	color: white;
	padding: 6px 10px;
	border-radius: 5px;
	cursor: pointer;
	text-decoration: none;
  }
  
  .add-button:hover {
	text-decoration: none;
}

</style>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>
	<script>
      // Fetch the navbar content
      fetch('AdminNavBar.jsp')
        .then(response => response.text())
        .then(data => {
          // Insert the navbar content into the navbarContainer div
          document.getElementById('adminnavbar').innerHTML = data;
        })
        .catch(error => console.error('Error fetching navbar:', error));
    </script>

	<div id="adminnavbar"></div>
	<div class="heading-container">
    <h2>Student List</h2>
      <a class="add-button" href="AdminAddStudent.jsp">Add Student</a>
  </div>
	<table>
	<thead>
		<tr>
			<th>Alumni ID</th>
			<th>Full Name</th>
			<th>Batch Name</th>
			<th>Faculty Name</th>
			<th>Course</th>

			<th>Course Fees</th>
			<th>Paid Fees</th>
			<th>Unpaid Fees</th>
			<th>Username</th>
			<th>Password</th>
			<th>Mobile</th>
			<th>Photo</th>
			<th>Actions</th>

		</tr>
		</thead>
		   <tbody>
		<c:forEach var="user" items="${userList}">
			<tr class="table-row">
				<td class="full-name">${user.id}</td>
				<td class="full-name">${user.fullName}</td>
				<td class="faculty-name">${user.batchName}</td>
				<td class="faculty-name">${user.facultyName}</td>
				<td class="faculty-name">${user.course}</td>

				<td><h5 class="course-fees formattedOutput">
						${user.coursefees}</h5></td>
				<td><h5 class="collected-fees formattedOutput">
						${user.paidfees}</h5></td>
				<td>
					<h5 class="pending-fees formattedOutput">${user.unpaidfees}</h5>
				</td>

				<td class="full-name">${user.username}</td>
				<td class="full-name">${user.password}</td>
				<td class="student-name"><i class="fa fa-phone"></i>${user.phone}</td>
				<td><c:choose>

						<c:when test="${not empty user.imagePath}">
							<img src="${user.imagePath}" alt="User Photo" class="user-photo" />
						</c:when>

						<c:otherwise>
                            No image available
                        </c:otherwise>

					</c:choose></td>
				<td><a href="edit?id=<c:out value='${user.id}' />"
					class="button edit-button"> <i class="fa fa-pencil button-icon"></i>Edit
				</a> <a href="delete?id=<c:out value='${user.id}' />"
					class="button delete-button"> <i
						class="fa fa-trash button-icon"></i>Delete
				</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">
		<!-- Pagination buttons go here, generated dynamically using JavaScript -->
	</div>
	<script>
	 const itemsPerPage = 5; // Number of items to display per page
	  const tableRows = document.querySelectorAll(".table-row");
	  const paginationContainer = document.querySelector(".pagination");

	  function showPage(pageNumber) {
	    const startIndex = (pageNumber - 1) * itemsPerPage;
	    const endIndex = startIndex + itemsPerPage;

	    tableRows.forEach((row, index) => {
	      if (index >= startIndex && index < endIndex) {
	        row.style.display = "table-row";
	      } else {
	        row.style.display = "none";
	      }
	    });
	  }

	  function createPaginationButtons(totalPages) {
	    for (let i = 1; i <= totalPages; i++) {
	      const button = document.createElement("button");
	      button.textContent = i;
	      button.classList.add("button", "pagination-button");
	      button.addEventListener("click", () => showPage(i));
	      paginationContainer.appendChild(button);
	    }
	  }

	  const totalPages = Math.ceil(tableRows.length / itemsPerPage);
	  createPaginationButtons(totalPages);
	  showPage(1); // Show the first page by default
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
