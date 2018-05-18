      var map;
      var markers = [];
      var infoWindow;     
      var geocoder;
      var coords =  { };
      // get the LatLng of the entered address
      var googleLatLong;
      
      //get the position of the browser
     function Initialize(){
         	// Auto Complete
      		// Get the html input element for the autocomplete search box
          	var input = document.getElementById("address");
          	// create the autoComplete Object
          	var autoComplete = new google.maps.places.Autocomplete(input); 
          	
         }
      
      // get the address that the user entered
      function getAddress() { 
    	  // remove the markers from the map
    	 // clearMarkers();
    	  
    	  coords = [];
    	  geocoder = new google.maps.Geocoder();
    	  
    	    var address = document.getElementById('address').value;
    	    geocoder.geocode( { 'address': address}, function(results, status) {
    	      if (status == 'OK') {
    	    	
    	         
    	    	  coords.latitude = results[0].geometry.location.lat();
    	    	  coords.longitude = results[0].geometry.location.lng();

    	    	//  makePlacesRequest(coords.latitude, coords.longitude);
    	    	//  console.log("Clicked the Search button");
    	    	
    	    	   showMap(coords);
    	    	   
    	    	   var java_lat = coords.latitude;
    	    	   $("#googleLat").attr("value",java_lat);
    	    	   
    	    	   var java_lng = coords.longitude;
    	    	   $("#googleLng").attr("value",java_lng);
    	    	   
    	    	//  window.location = "/restaurants";
    	    	   
    	    	   var mapContainer = document.getElementById("mapContainer");
    	    	    mapContainer.style.display = 'block';
    	    	    
    	    	// window.location = "#findRestaurant";
    	     
    	      } else {
    	        alert('Geocode was not successful for the following reason: ' + status);
    	      }
    	    });
    	  }

      
      function showMap(coords){
    	  googleLatLong = null;
      // create a map and center it
      var googleLatLong = new google.maps.LatLng( coords.latitude,  coords.longitude);
      
      
      var mapOptions = {
      	zoom : 14,
      	center : googleLatLong,
      	mapTypeId: google.maps.MapTypeId.ROADMAP
      	
      };
      
      // CREATE THE MAP
      var mapDiv =document.getElementById("map");
      map = new google.maps.Map(mapDiv, mapOptions);
      
      var homeIcon = "http://localhost:8080/resources/img/maps/home_Icon.png.png";
      var Restaurant_Icon = "http://localhost:8080/resources/img/maps/rest_Icon.png.png";
      var address = document.getElementById('address').value;
    var marker = new google.maps.Marker({
        icon:  homeIcon, 
    	map: map,
       position: googleLatLong,
       title: "Delivery Address: " + address
       });
    
      }
      
    //calling the maps when the page load
     google.maps.event.addDomListener(window, "load", Initialize);
     