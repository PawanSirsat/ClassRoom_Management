<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<title>Create Assignment</title>
<style>
.container2 {
    max-width: 800px;
    margin: 50px auto;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 5px;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}

.form-row {
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    justify-content: space-between;
}

.form-column {
    flex: 1;
    margin-right: 20px;
}

.form-group {
    margin-bottom: 20px;
}

label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

input[type="text"],
select,
textarea,
input[type="date"],
input[type="number"],
input[type="file"] {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 10px;
    box-sizing: border-box;
}

  textarea {
            resize: vertical;
            height: 140px;
        }

input[type="submit"] {
    background-color: #4CAF50;
    color: #ffffff;
    padding: 10px 16px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #45a049;
}
</style>
</head>
<body>
	<div id="facultynavbar"></div>
	<script>
	function handleFacultySelection() {
		  const facultyDropdown = document.getElementById('batchdropdown');
		  const facultyInput = document.getElementById('batchinput');
		  const submitButton = document.getElementById('submitBtn');

		  // Check if a faculty is selected
		  if (facultyDropdown.value !== '') {
		    // Disable the faculty ID input
		    facultyInput.disabled = true;
		    // Disable the submit button
		    submitButton.disabled = true;
		  } else {
		    // Enable the faculty ID input
		    facultyInput.disabled = false;
		    // Enable the submit button
		    submitButton.disabled = false;
		  }
		}
  // Fetch the navbar content
  fetch('FacultyNavbar.jsp')
    .then(response => response.text())
    .then(data => {
      // Insert the navbar content into the navbarContainer div
      document.getElementById('facultynavbar').innerHTML = data;
    })
    .catch(error => console.error('Error fetching navbar:', error));
</script>

	<div class="container2">
    <h2>Create Assignment</h2>
    <form action="addAssignment" method="POST" enctype="multipart/form-data">
        <div class="form-row">
            <div class="form-column">
                <div class="form-group">
                    <label>Title:</label>
                    <input type="text" name="title" required><br>
                </div>
                <div class="form-group">
                    <label for="batchid">Batch:</label>
                    <select name="batchid" id="batchdropdown" onchange="handleFacultySelection()" required>
                        <option value="">Select Batch</option>
                        <c:forEach var="batch" items="${batch}">
                            <option value="${batch.batchId}">${batch.batchName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Due Date:</label>
                    <input type="date" name="dueDate" required><br>
                </div>
                               
            </div>
            <div class="form-column">
                <div class="form-group">
                    <label>Description:</label>
                    <textarea name="description" rows="4" cols="50" required></textarea>
                </div>

                <div class="form-group">
                    <label>PDF File:</label>
                    <input type="file" name="pdfFile" required><br>
                </div>
            </div>
        </div>

        <input type="submit" value="Create Assignment">
    </form>
</div>



</body>
</html>
