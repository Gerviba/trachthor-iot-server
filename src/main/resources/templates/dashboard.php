<?php
    session_start();
    if(!isset($_SESSION['username'])){
            header("Location: index.php");
            exit;                               
    }
    ?>

<!DOCTYPE html>
<html lang="en">
<head>
<title>DASHBOARD - TrackThor</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../static/style/style.css" th:href="@{/style/style.css}">
    <link rel="stylesheet" href="style.css">
    
</head>
<body>
    
     <nav class="navbar navbar-expand-lg navbar-light navbar-custom sticky-top">
      <div class="container">
        <a class="navbar-brand" href="map.html">
          TrachThor <?php echo($_SESSION['username']); ?>
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span> 
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
              <li class="nav-item active">
              <a class="nav-link" href="dashboard.php">Dashboard</a>
            </li>
              <li class="nav-item">
              <a class="nav-link" href="devices.php">Devices</a>
            </li>
              <li class="nav-item">
              <a class="nav-link" href="settings.php">Settings</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="map.php">Map</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="logout.php">Logout</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>  
    
    
	<nav class="navbar navbar-dark bg-dark navbar-expand-lg" id="dasboard-navbar">
		<a class="navbar-brand" href="#">TrackThor</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav">
				<a class="nav-item nav-link active" href="/">Dashboard</a> 
				<a class="nav-item nav-link" href="/control/map">Map</a>
				<a class="nav-item nav-link" href="/control/devices">Devices</a>
				<a class="nav-item nav-link" href="/control/users">Users</a>
				<a class="nav-item nav-link" href="/control/settings">Settings</a>
			</div>
		</div>
	</nav>

	<div class="container">
		<h1>Warnings</h1>
		<div class="row col-md-12 table-responsive">
			<table id="warningsTable" class="table table-dark">
				<thead>
					<tr>
						<th>Time</th>
						<th>Level</th>
						<th>Message</th>
					</tr>
				</thead>
				<tbody>
					<tr th:each="warning : ${warnings}" th:object="${warning}" 
							th:class="'bg-' + ${#strings.toLowerCase(warning.level)}">
						<td th:text="${#dates.format(warning.date, 'yyyy-MM-dd HH:mm:ss')}">Time</td>
						<td th:text="*{level}">WARNING</td>
						<td th:text="*{message}">Test message</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>