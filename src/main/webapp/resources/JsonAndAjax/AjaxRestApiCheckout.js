
 var restaurantData;
 var cartId;
 var foodSubTotal
 
 // get the tip percentage from the page
 var courierTip;
 
 // calculated tip amount
 var getTip;
 // delivery fee
 var deliveryFee = 1;
 
$(document).ready(function () {	
	
	cartId = document.getElementById('cartId').value;
	refreshCart();
	
});


//load the Ajax cart Data
function refreshCart(){
	

	// XMLHttpRequest will stablish a connection with the url we specify and then it let us send or receive data
	var ourRequest = new XMLHttpRequest();

	// get the data from url
	ourRequest.open('GET', '/rest/cart/'+cartId);
	

	// define what we want to do once the data is loaded
	ourRequest.onload = function (){
		
		
		if(ourRequest.status >= 200 && ourRequest.status < 400){
			
			//save the data retrieve form the url on a variable
			var ourData = JSON.parse(ourRequest.responseText);
			createHtml(ourData);
			
			
			 foodSubTotal = calculateGrandTotal(ourData);
			
			document.getElementById("getGrandTotal").innerHTML = foodSubTotal;
			
			document.getElementById("getDeliveryFee").innerHTML = deliveryFee;
			
			calfinalTotal();
			
			if(ourData['cartItems'].length > 0) {
				
				document.getElementById("btnCheckout").style.visibility = "visible";
			}else {
				document.getElementById("btnCheckout").style.visibility = "hidden";
			}
			
			
			 getManufacturer(ourData);
			 restaurantData = ourData;
			
		} else {
			
			console.log("We connected to the server, but it returned an error");
		}
	
		
		var grandtotal = document.getElementById('getGrandTotal').value;
		
	};
	
//  handler ajax error
	ourRequest.onerror = function (){
		console.log("connection error");
	}
	
	// send the request
		ourRequest.send();
	
	
	
}

function addProduct(productId, restaurantname){	
	
if(getManufacturer(restaurantData)){ // the are items on the cart
	
	if(restaurantname == getManufacturer(restaurantData)) {// check if the new product is from the same restaurant
		 $.ajax({ // add the new item to the cart
		        type: "POST",
		        contentType: "application/json",
		        url: "/rest/cart/add/"+productId,	       
		        dataType: 'json',
		        cache: false,
		        timeout: 600000,
		        success: function () {
		        	
		        	refreshCart(); // refresh the cart
		        
		        }
		 });
		
	} else { // the item is not from the same restaurant
		
		var clear_cart = confirm("You are adding an item from another menu. Do you want to start a new order and clear your existing order cart?");
		
		 
		 if (clear_cart == true) {
			 
			 $.ajax({ //  delete the items from the cart
			        type: "DELETE",
			        contentType: "application/json",
			        url: "/rest/cart/"+cartId,	       
			        dataType: 'json',
			        cache: false,
			        timeout: 600000,
			        success: function () {
			        	
			        	$.ajax({
			                type: "POST",
			                contentType: "application/json",
			                url: "/rest/cart/add/"+productId,	       
			                dataType: 'json',
			                cache: false,
			                timeout: 600000,
			                success: function () {
			                	
			                	refreshCart();
			                
			                }
			         });
			        
			        }
			 });
			 
		        
		    } 
	}
}
else { // first item on the cart
	
	$.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/rest/cart/add/"+productId,	       
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function () {
        	
        	refreshCart();
        
        }
 });
}
	
	
	
	
	
	
	
}


function removeProduct(productId){	
	
	// XMLHttpRequest will stablish a connection with the url we specify and then it let us send or receive data
		var ourRequest = new XMLHttpRequest();

		// get the data from url
		ourRequest.open('PUT', 'rest/cart/remove/'+productId);
		

		// define what we want to do once the data is loaded
		ourRequest.onload = function (){
			
			
			if(ourRequest.status >= 200 && ourRequest.status < 400){
				
				refreshCart();
				
			} else {
				
				console.log("We connected to the server, but it returned an error");
			}
		
		};
		
	//  handler ajax error
		ourRequest.onerror = function (){
			console.log("connection error");
		}
		
		// send the request
			ourRequest.send();
		
		
	
}




function calculateGrandTotal(data){
	
	var grandTotal = 0;
	
	for (var i=0; i< data['cartItems'].length; i++) {
	    grandTotal+= data['cartItems'][i].totalPrice;
	}
	
	return grandTotal;
}


function getManufacturer(data) {
	
	var manufacturer = "";
	
	if(data['cartItems'].length > 0){
		manufacturer = data['cartItems'][0].product.manufacturer;
	}
	
	
	return manufacturer;
}


//handles the html data
function createHtml(data){
	
	// get the id of the script tag for the handleBar Template
	var rawTemplate = document.getElementById('cart-template').innerHTML;
	

	
	// compile the template to be use with HandleBars
	var compiledTemplate = Handlebars.compile(rawTemplate);

	
	// get the data to be used on the template
	var ourGeneratedHtml = compiledTemplate(data);	
	
	
	//get the div element by id in which we want to display the data
	var handlerbarContainer = document.getElementById('cartItemsContainer');
	handlerbarContainer.innerHTML = ourGeneratedHtml;
}



$(".btn-group > button.btn").on("click", function(){
	courierTip = +this.value;
    $(this).addClass('btn-primary').siblings().removeClass('btn-primary').addClass('btn-default');
  
    if(courierTip != null){
	   calTotal(courierTip);
   }
    
});


function calTotal(tip){
	var total = 0;
	total =  (tip / 100) * foodSubTotal;
	
	var roundParameter = Math.pow(10, 2);  
	getTip = Math.round(total * roundParameter) / roundParameter;
	document.getElementById("getCourierTip").innerHTML = getTip;
	calfinalTotal();
}

function calfinalTotal(){
	var total = 0;
//	alert("foodSubTotal " + foodSubTotal + " Tip " + getTip + "fee " + deliveryFee); 
	if(foodSubTotal != null){		
						
						if(getTip != null && deliveryFee != null){
							
							 total = foodSubTotal + getTip + deliveryFee;
							 
						} else if(getTip == null && deliveryFee != null) {
							
							total = foodSubTotal + deliveryFee;
							
						} else if(getTip != null && deliveryFee == null){
							
							total = foodSubTotal + getTip;
						} else {
							
							total = foodSubTotal;
						}
		
	}
	
	document.getElementById("getTotalOrderPrice").innerHTML = total;
	
	var cents = total * 100;
	document.getElementById("getStripeAmount").value = cents;
	
	

}




