      var map;
      var markers = [];
      var infoWindow;     
      var geocoder;
      var coords =  { };
      // get the LatLng of the entered address
      var googleLatLong;
   	var autoComplete;    
    var getPlace;
    
    var addInstruction = false;
      
      //get the position of the browser
     function Initialize(){
    	
          	autocomplete = new google.maps.places.Autocomplete((document.getElementById('address')));
          	
            autocomplete.addListener('place_changed', onPlaceChanged);
          
         }
     
     function onPlaceChanged() {
    	 
    	 getPlace = autocomplete.getPlace();   
    	 
    	  getAddress();
    	  $( "#editAddressDialog" ).dialog("open");
    	  $( "#changeAddressDialog" ).dialog("close");
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
    	    	   $("#d_lat").attr("value",java_lat);
    	    	   
    	    	   var java_lng = coords.longitude;
    	    	   $("#googleLng").attr("value",java_lng);
    	    	   $("#d_lng").attr("value",java_lng);
    	    	   
    	    	  
    	    	   var java_address =  coords.deliveryAddress;
    		    	  $("#deliveryAddress").attr("value",java_address);
    		    	 
    		    	  
    		    	 initMap();
    		    	 
    		    	
    		    	  
    	      } else {
    	        alert('Geocode was not successful for the following reason: ' + status);
    	      }
    	    });
    	  }

  // create a map
     function initMap() {
     	 
    	 var deliverylat2 = document.getElementById("d_lat").value;
    	 var deliverylng2 = document.getElementById("d_lng").value;
    	 
         var deliveryLocation = {lat: Number(deliverylat2), lng: Number(deliverylng2)};
         var map = new google.maps.Map(document.getElementById('mapDiv'), {
           zoom: 17,
           center: deliveryLocation
         });
         var marker = new google.maps.Marker({
           position: deliveryLocation,
           map: map
         });
       }
     
 /// Jquery Pop Up Windows
$(document).ready(function(){
	
	// create the map on the site
	 initMap();
	 Initialize();

});

// link to open the change address windowp
$("#changeAddressLink").click(function(){
	
	  $( "#changeAddressDialog" ).dialog("open");
	
});


// open the change address window
$( "#changeAddressDialog" ).dialog({
	autoOpen: false,
	closeOnEscape: false,
	draggable: false,
	modal: true,
	width: 500,
	height: 300
	
});


// link to add delivery instructions
$("#addDeliveryIntructionsLink").click(function(){
	
	  $( "#editAddressDialog" ).dialog("open");
	  
	  //check which button was cliked for the save window pop up if
	   addInstruction = true;
	  
	
});


//Open Delivery instructions window
$( "#editAddressDialog" ).dialog({
	autoOpen: false,
	closeOnEscape: false,
	draggable: false,
	modal: true,
	width: 500,
	height: 400,
	buttons: {
		
		"Cancel" : function (){
				$(this).dialog("close");
			},
			
			"Save": function (){
				
				
				// get the value of the ApartmentNumber and set it in the form to submit the values to db
				var apartmentNumber = document.getElementById("editApartmentNumber").value;
				var deliveryInstructions = document.getElementById("editDeliveryInstructions").value;				
				  $("#apartmentNumber").attr("value",apartmentNumber);
				  $("#deliveryInstructions").attr("value",deliveryInstructions);
				  
				
				  if(addInstruction) {
					  
					  addInstruction = false;
					  submitform();
					
					
				  } else {
					  getAddressDetails(getPlace);
					  submitform();
					  
				  }
				
				
				
				
				
				//submitform();				
				$(this).dialog("close");
			}
		}
		
	
	
});



function submitform()
{
    document.forms["coordsForm"].submit();
}




//calling the maps when the page load
//google.maps.event.addDomListener(window, "load", Initialize);

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
function getAddressDetails(place){
	
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