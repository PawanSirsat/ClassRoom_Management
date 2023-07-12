<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Transaction Details</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

.container {
	position: absolute;
	top: 150px;
	left: 200px;
	max-width: 800px;
	margin: 0 auto;
	padding: 20px;
	background-color: #fff;
	box-shadow: 0 3px 15px rgba(0, 0, 0.3, 0.8);
}

h1 {
	text-align: center;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 35px;
}

th, td {
	padding: 10px;
	text-align: left;
}

th {
	background-color: #f5f5f5;
	border-bottom: 1px solid #ddd;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

tr:hover {
	background-color: #f2f2f2;
}
.green-text {
	color: #11c20e;
}
.download-btn {
  display: inline-block;
  padding: 10px 20px;
  background-color: #4CAF50;
  color: #ffffff;
  text-decoration: none;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px; /* Adjust the font size as desired */
  font-weight: bold;
  
}

.download-btn:hover {
  background-color: #45a049;
  text-decoration: none;
}

</style>
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
	<div class="container">
		<h1>Transaction Details</h1>
<a href="downloadTransaction" class="download-btn">Download Transactions PDF</a>
		
		<table>
			<thead>
				<tr>
					<th>Student ID</th>
					<th>Student Name</th>
					<th>Batch Name</th>
					<th>Transaction Date</th>
					<th>Payment Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${transactionDetailsList}"
					var="transactionDetails">
					<tr>
						<td>${transactionDetails.studentId}</td>
						<td>${transactionDetails.studentName}</td>
						<td>${transactionDetails.batchName}</td>
						<td>${transactionDetails.transactionDate}</td>
						<td><span class="green-text "><strong
							class=" formattedOutput">${transactionDetails.paymentAmount}</strong></span></td>
				
					</tr>
				</c:forEach>
			</tbody>
		</table>
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
