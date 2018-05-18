      var map;
      var markers = [];
      var infoWindow;     
      var geocoder;
      var coords =  { };
      // get the LatLng of the entered address
      var googleLatLong;
   	var autoComplete;
      
      //get the position of the browser
     function Initialize(){
    	 
    	 
    	 document.getElementById("findRestaurant").disabled = true;
    	 
    	
          	autocomplete = new google.maps.places.Autocomplete((document.getElementById('address')));
          	
            autocomplete.addListener('place_changed', onPlaceChanged);

          	
          	
          
          	
         }
     
     
     
     
     function onPlaceChanged() {
    	 document.getElementById("findRestaurant").disabled = false;
    	 getAddress();
    	 var getPlace = autocomplete.getPlace(); 
    	 
    	  getAddressDetails(getPlace);
       }
      
      // get the address that the user entered
      function getAddress() { 
    	
    	  coords = [];
    	  geocoder = new google.maps.Geocoder();
    	  
    	    var address = document.getElementById('address').value;
    	    geocoder.geocode( { 'address': address}, function(results, status) {
    	      if (status == 'OK') {
    	    	
    	         
    	    	  coords.latitude = results[0].geometry.location.lat();
    	    	  coords.longitude = results[0].geometry.location.lng();
    	    	  coords.deliveryAddress = address;
    	    
    	    	
    	    	// put the lat and lng unto the index hidden fields
    	    	   var java_lat = coords.latitude;
    	    	   $("#googleLat").attr("value",java_lat);
    	    	   
    	    	   var java_lng = coords.longitude;
    	    	   $("#googleLng").attr("value",java_lng);
    	    	   
    	    	  
    	    	   var java_address =  coords.deliveryAddress;
    		    	  $("#deliveryAddress").attr("value",java_address);
    	    	
    	    	
    	      } else {
    	        alert('Geocode was not successful for the following reason: ' + status);
    	      }
    	    });
    	  }

      
   // holdes the key for the google Maps values
      var componentForm = {
    		  street_number: 'short_name',
    		  route: 'long_name',
    		  locality: 'long_name',
    		  administrative_area_level_1: 'short_name',
    		  country: 'long_name',
    		  postal_code: 'short_name'
    		  };

      // get the google maps address autofill
      function getAddressDetails(place) {
      	
      	 for (var component in componentForm) {
          document.getElementById(component).value = '';    	      
        }

        // Get each component of the address from the place details
        // and fill the corresponding field on the form.
      	
        for (var i = 0; i < place.address_components.length; i++) {
          var addressType = place.address_components[i].types[0];
          if (componentForm[addressType]) {
            var val = place.address_components[i][componentForm[addressType]];
            document.getElementById(addressType).value = val;
          
          }
        }
      }
      
    //calling the maps when the page load
     google.maps.event.addDomListener(window, "load", Initialize);
     