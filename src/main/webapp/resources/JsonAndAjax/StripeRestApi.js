
$(document).ready(function () {	
	

	
});



function addPayment(token){	
	
		 $.ajax({ // add the new item to the cart
		        type: "POST",
		        contentType: "application/json",
		        url: "/rest/stripe/addPayment/"+stripeToken,	       
		        dataType: 'json',
		        cache: false,
		        timeout: 600000,
		        success: function () {
		        	
		   
		        }
		 });
		
}

