<?php
    session_start();
    if(!isset($_SESSION['username'])){
            header("Location: index.php");
            exit;                               
    }
    ?>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
      
      <!-- local CSS -->
      <link rel="stylesheet" href="style.css">

    <title>MAP - TrachThor</title>
    <style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 100%;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
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
              <li class="nav-item">
              <a class="nav-link" href="dashboard.php">Dashboard</a>
            </li>
              <li class="nav-item">
              <a class="nav-link" href="devices.php">Devices</a>
            </li>
              <li class="nav-item">
              <a class="nav-link" href="settings.php">Settings</a>
            </li>
            <li class="nav-item active">
              <a class="nav-link" href="map.php">Map</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" href="logout.php">Logout</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>  
      
      
    <div id="map"></div>
    <script>

      // This example creates a simple polygon representing the Bermuda Triangle.

      function initMap() {
   	  	var device = new google.maps.LatLng(47.473398, 19.061120);	
    	  
    	  
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 19,
          center: device,
          mapTypeId: 'terrain'
        });

        // Define the LatLng coordinates for the polygon's path.
        var nogozoneCoords = [
          {lat: 47.473696, lng: 19.060734},
          {lat: 47.473144, lng: 19.060669},
          {lat: 47.472764, lng: 19.061764},
          {lat: 47.473818, lng: 19.061407},
          {lat: 47.473696, lng: 19.060734}
        ];

        var nogozone = new google.maps.Polygon({
          paths: nogozoneCoords,
          strokeColor: '#FF0000',
          strokeOpacity: 0.2,
          strokeWeight: 10,
          fillColor: '#FF0000',
          fillOpacity: 0.5
        });
        nogozone.setMap(map);
        
        var gozoneCoords = [
          {lat: 47.473688, lng: 19.060691},
          {lat: 47.473796, lng: 19.059166},
          {lat: 47.472884, lng: 19.059150},
          {lat: 47.473117, lng: 19.060622},
          {lat: 47.473688, lng: 19.060691}
        ];
        
        var gozone = new google.maps.Polygon({
            paths: gozoneCoords,
            strokeColor: '#00FF00',
            strokeOpacity: 0.2,
            strokeWeight: 10,
            fillColor: '#00FF00',
            fillOpacity: 0.5
          });
        gozone.setMap(map);
        
        var marker1 = new google.maps.Marker({
            position: device,
            title: "Device1",
            label: "",
            icon: {
                url: '/images/device-icon-3.png',
                size: new google.maps.Size(84, 69),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(32, 37)
              }
        });

        marker1.setMap(map);
        
        var marker2 = new google.maps.Marker({
            position: {lat: 47.473229, lng: 19.060178},
            title: "Device2",
            label: "",
            icon: {
                url: '/images/device-icon-4.png',
                size: new google.maps.Size(143, 127),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(58, 69)
              }
        });

        marker2.setMap(map);
      }
    </script>
    <script async defer 
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBXZKy111kwc7sbEPIs91zXI99ak2ldLkQ&callback=initMap">
    </script>
              <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>