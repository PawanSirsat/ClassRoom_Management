<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<title>Transaction Details</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f5f5f5;
	margin: 0;
	padding: 20px;
}
.con1{
margin-top:100px;
padding-left:100px;
padding-right:100px;
}


h1 {
	text-align: center;
	color: #333;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
	background-color: #fff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

th, td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #f5f5f5;
	font-weight: bold;
	color: #333;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

.green-text {
	color: #11c20e;
}
</style>
</head>
<body>
	<div id="navbarContainer"></div>

	<script>
	
      // Fetch the navbar content
fetch('/FirstWeb2/WelcomeNavBar.jsp')
        .then(response => response.text())
        .then(data => {
          // Insert the navbar content into the navbarContainer div
          document.getElementById('navbarContainer').innerHTML = data;
        })
        .catch(error => console.error('Error fetching navbar:', error));
    </script>
    
        <div class="con1">
    
	<h1>Transaction Details</h1>
	<table>
		<thead>
			<tr>
				<th>Transaction Date</th>
				<th>Payment Amount</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${transactions}" var="transaction">
				<tr>
					<td>${transaction.transactionDate}</td>
					<td><span class="green-text "><strong
							class=" formattedOutput">${transaction.paymentAmount}</strong></span></td>
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
