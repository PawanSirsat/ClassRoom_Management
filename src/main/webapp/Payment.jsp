<!DOCTYPE html>
<html>
<head>
<title>Payment Page</title>
<style>
body {
	display: flex;
	align-items: center;
	justify-content: center;
	height: 100vh;
	margin: 0;
	background-color: #f1f1f1;
}

.form-group1 {
	margin-bottom: 10px;
}

label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
}

input[type="text"], input[type="number"] {
	width: 100%;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.error-message {
	color: red;
	font-size: 12px;
}

.submit-button {
	display: block;
	width: 100%;
	padding: 10px;
	margin: 10px;
	margin-top: 50px;
	background-color: #4CAF50;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	background-color: #4CAF50;
}

.green-text {
	color: green;
}

.red-text {
	color: red;
}

.profile-info {
	width: 900px;
	height: 380px;
	padding: 40px;
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 6px 10px 3px rgba(0, 0, 0, 0.3);
	padding: 40px;
}

.additional-info {
	position: absolute;
	top: 330px;
	right: 500px;
	padding: 20px;
}

.additional-info p {
	margin-bottom: 10px;
}

.additional-info .info-icon {
	margin-right: 5px;
}

.info-container {
	display: inline-grid; /* the default for span */
	align-items: center;
	margin-top: 5px;
}

.info-container input {
	width: 230px;
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
	<div class="profile-info">
		<h2>Payment Details</h2>

		<div class="info-container">
			<label for="card-number">Card Number</label> <input type="number"
				id="card-number" name="card-number" placeholder="Card Number"
				pattern="\d{1,8}" inputmode="numeric" maxlength = "8"> <span
				id="card-number-error" class="error-message"></span>
		</div>
		<div class="info-container">
			<label for="expiration-date">Expiration Date</label> <input
				type="text" id="expiration-date" name="expiration-date"
				placeholder="MM/YY" pattern="\d{1,2}\/\d{1,2}" required> <span
				id="expiration-date-error" class="error-message"></span>
		</div>
		<div class="info-container">
			<label for="cvv">CVV</label> <input type="number" id="cvv" name="cvv"
				placeholder="CVV" pattern="\d{1,3}" inputmode="numeric" required>
			<span id="cvv-error" class="error-message"></span>
		</div>
		<form class="signup-form" action="payfees" method="post">
			<div class="info-container">
				<label for="amount">Amount</label> <input type="number" id="amount"
					name="amount" placeholder="Enter Amount"> <span
					id="amount-error" class="error-message"></span>
			</div>
			<button type="submit" class="submit-button">Pay Now</button>
		</form>

		<div class="additional-info">
			<p>
				<i class="fa fa-credit-card-alt" aria-hidden="true"></i> Paid Fees:
				<span class="green-text"><strong> &#8377; <%=session.getAttribute("paidfees")%></strong></span>
			</p>
			<p>
				<i class="fa fa-credit-card-alt" aria-hidden="true"></i> Unpaid
				Fees: <span class="red-text"><strong> &#8377; <%=session.getAttribute("unpaidfees")%></strong></span>
			</p>
			<p>
				Course Fees: <span class="green-text"><strong>&#8377;<%=session.getAttribute("coursefees")%></strong></span>
			</p>
		</div>
	</div>

	<script>
        // Amount input live validation
        var amountInput = document.getElementById("amount");
        var amountError = document.getElementById("amount-error");

        amountInput.addEventListener("input", function() {
            var amount = parseInt(amountInput.value);
            if (isNaN(amount) || amount <= 0 || amount > <%=session.getAttribute("unpaidfees")%>) {
                amountError.innerText = "Please enter a valid amount (1 - <%=session.getAttribute("unpaidfees")%>)";
            } else {
                amountError.innerText = "";
            }
        });

        // Form validation
        document.querySelector("form").addEventListener("submit", function(event) {
            var cardNumberInput = document.getElementById("card-number");
            var expirationDateInput = document.getElementById("expiration-date");
            var cvvInput = document.getElementById("cvv");

            var cardNumberError = document.getElementById("card-number-error");
            var expirationDateError = document.getElementById("expiration-date-error");
            var cvvError = document.getElementById("cvv-error");

            var isValid = true;

            // Card number validation
            if (!isValidCardNumber(cardNumberInput.value)) {
                cardNumberError.innerText = "Invalid card number";
                isValid = false;
            } else {
                cardNumberError.innerText = "";
            }

            // Expiration date validation
            if (!isValidExpirationDate(expirationDateInput.value)) {
                expirationDateError.innerText = "Invalid expiration date";
                isValid = false;
            } else {
                expirationDateError.innerText = "";
            }

            // CVV validation
            if (!isValidCVV(cvvInput.value)) {
                cvvError.innerText = "Invalid CVV";
                isValid = false;
            } else {
                cvvError.innerText = "";
            }

            if (!isValid) {
                event.preventDefault(); // Prevent form submission if there are errors
            }
        });

        // Card number validation
        function isValidCardNumber(cardNumber) {
            // Implement your card number validation logic here
            return /^\d{8}$/.test(cardNumber);
        }

        // Expiration date validation
        function isValidExpirationDate(expirationDate) {
            // Implement your expiration date validation logic here
            return /^(0[1-9]|1[0-2])\/\d{2}$/.test(expirationDate);
        }

        // CVV validation
        function isValidCVV(cvv) {
            // Implement your CVV validation logic here
            return /^\d{3}$/.test(cvv);
        }
    </script>
</body>
</html>
