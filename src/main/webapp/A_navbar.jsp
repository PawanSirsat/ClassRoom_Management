<!DOCTYPE html>
<html>

<head>
<style>
.container {
	background-color: #007bff;
	width: 100px;
	height: 100vh;
	position: fixed;
	left: 0;
}
</style>
</head>

<body>
	<div class="container">
		<ul id="list"></ul>
	</div>

	<script>
		var data = [ 'Item 1', 'Item 2', 'Item 3', 'Item 4' ];

		function populateList() {
		var listElement = document.getElementById('list');

			listElement.innerHTML = '';
			
			for (var i = 0; i < data.length; i++) {
				var listItem = document.createElement('li');
				listItem.textContent = data[i];
				listElement.appendChild(listItem);
			}
		}
		populateList();
	</script>
</body>

</html>
