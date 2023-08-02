<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pojo.StudentSubmission"%>
<%@ page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Submission Details</title>
<style>
body {
	font-family: Arial, sans-serif;
}

.con1 {
	margin-top: 50px;
	padding-left: 100px;
	padding-right: 100px;
}

h2 {
	text-align: center;
	margin-bottom: 20px;
}

.truncate {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	max-width: 50px;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
	max-width: 50px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
	font-weight: bold;
}

.submitted {
	color: Green;
	font-weight: 500;
	font-size: 16px;
}

.late {
	color: red;
	font-weight: 500;
	font-size: 16px;
}

.marks-input {
	width: 50px;
}

.grade-button {
	padding: 5px 10px;
	background-color: #4CAF50;
	border: none;
	border-radius: 25px;
	color: white;
	cursor: pointer;
}

.grade-button:hover {
	background-color: #45a049;
}

a:hover {
    text-decoration: none;
}

</style>

</head>
<body>
	<div id="navbarContainer"></div>

	<script>

  // Fetch the navbar content
  fetch('FacultyNavbar.jsp')
    .then(response => response.text())
    .then(data => {
      // Insert the navbar content into the navbarContainer div
      document.getElementById('navbarContainer').innerHTML = data;
    })
    .catch(error => console.error('Error fetching navbar:', error));
</script>

	<div class="con1">
		<h2>Submission Details</h2>
		<table>
			<tr>
				<th>Student Name</th>
				<th>Status</th>
				<th>Due Date</th>
				<th>Submission Date</th>
				<th>Submission</th>
				<th>Assignment</th>

				<th>Marks</th>
			</tr>
			<c:forEach var="submission" items="${studentSubmissions}">
				<tr>
					<td>${submission.studentName}</td>
					<!-- Use conditional expressions to apply specific styles -->
					<td
						class="${submission.status eq 'Turned in' ? 'submitted' : 'late'}">
						${submission.status}</td>

					<td>${submission.due_date}</td>
					<td>${submission.submissionDate}</td>

					<!-- Use a conditional expression to show an input box for marks if status is "Turned in" -->
					<td class="${submission.done eq 'On Time' ? 'submitted' : 'late'}">
						${submission.done}</td>

					<td>
					    <c:choose>
							<c:when test="${submission.status eq 'Turned in'}">
<a href="downloadfaculty?submissionId=${submission.sub_id}" download>Show</a>
							</c:when>
							<c:otherwise>
                               Not Available
                        </c:otherwise>
						</c:choose>
						
					</td>

					<td
						class="${submission.status eq 'Turned in' ? 'submitted' : 'late'}">
						<c:choose>
							<c:when
								test="${submission.status eq 'Turned in' && submission.marks > 0}">
            ${submission.marks}
        </c:when>
							<c:when
								test="${submission.status eq 'Turned in' && submission.marks == 0}">
								<form action="Grade" method="post"
									onsubmit="return validateMarks(this)">
									100/ <input type="number" class="marks-input" name="marks">
									<input type="hidden" name="submissionId"
										value="${submission.sub_id}">
									<button class="grade-button">Grade</button>
								</form>
							</c:when>
							<c:otherwise>
            0
        </c:otherwise>
						</c:choose>
					</td>


				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>
