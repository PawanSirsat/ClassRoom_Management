<!DOCTYPE html>
<html>

<head>
  <title>About Our Company</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 20px;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }
    
    .about-container {
      max-width: 1200px;
	  box-shadow: 0 5px 30px 20px rgba(0, 0, 0, 0.3);
      padding: 80px;
      border-radius: 30px;
      margin:50px;
    }
    
    h1 {
      color: #333;
      font-size: 28px;
      margin-bottom: 10px;
    }
    
    p {
      color: #666;
      font-size: 18px;
    }
    
    .contact-info {
      margin-top: 20px;
      display: flex;
    }
    
    .contact-info i {
      margin-right: 10px;
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
  <div class="about-container">
    <h1>About Our Company</h1>
    <p>Codenera is a specialized coding school, train
     resources rigorously on competitive technologies. We at
      Codenera make sure that the quality coders we are making are
       matching to the industry standards and placing into the right
        position.</p>

    <div class="contact-info">
      <p>      <i class="fas fa-envelope"></i>
      Email: p1.sirsat1998.com</p>
    </div>

    <div class="contact-info">
      <p>      <i class="fas fa-phone"></i>
      Phone: +91 951 899 4028</p>
    </div>

    <div class="contact-info">
      <p>      <i class="fas fa-map-marker-alt"></i>
      Address: 123 Main Baner, Pune, India</p>
    </div>
  </div>
</body>

</html>
