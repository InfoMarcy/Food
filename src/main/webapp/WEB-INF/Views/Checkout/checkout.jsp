<%@ include file="../include/header.jsp"%>
<header class="container-fluid">
	<%@ include file="../include/storeNavbar.jsp"%>
</header>

<link href="../../resources/jquery/jquery-ui.css" rel="stylesheet" />
<!-- google maps key and api -->
<script type="text/javascript"
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEjrJMVu39h6zTU6K_SapWFc3YXwTWu3M&libraries=places"></script>
<!-- paypal payment script -->
<script src="https://www.paypalobjects.com/api/checkout.js"></script>

<section class="container starter-template">
	<input type="hidden" value="${cartId}" id="cartId" />
	<article class="container">
		<article class="row">

			<section class="col-xs-12 col-sm-12 col-md-8" id="customerInfo">

				<div class="row" id="customerAddress">
					<div class="col-md-4 col-xs-4 col-md-4">
						<p class="lead">
							Address <br /> <a href="#" id="changeAddressLink">Change</a>
						</p>

						<div id="changeAddressDialog" title="Saved Addresses">
							<hr />
							<label>${checkoutInfo.shipping.deliveryAddress}</label> <br />
							<hr />

							<p class="lead">Add a new Address</p>
							<input type="text" id="address" name="address"
								class="form-control input-lg"
								style="display: block; margin: 0; width: 100%;" />

						</div>


						<div id="editAddressDialog" title="Edit Address">

							<div class="row">
								<div class="col-md-12">
									<input type="text" id="deliveryAddress"
										value="${checkoutInfo.shipping.deliveryAddress}"
										class="form-control input-lg" readonly /><br /> <input
										type="text" id="editApartmentNumber"
										placeholder="Apartment Number or suite"
										class="form-control input-lg" /><br />
									<textarea rows="4" id="editDeliveryInstructions" cols="30"
										class="form-control input-lg"
										placeholder="Delivery Instructions. Example: Call me when you're outside">
</textarea>

								</div>

							</div>
						</div>







					</div>

					<div class="col-md-8">


						<div class="row">
							<div class="col-md-12">
								<input type="hidden" id="d_lat"
									value="${checkoutInfo.shipping.googleLat}" /> <input
									type="hidden" id="d_lng"
									value="${checkoutInfo.shipping.googleLng}" />

								<div id="mapDiv"
									style="border: 1px solid red; width: 350px; height: 150px;">


								</div>
								<label><i class="fa fa-home" aria-hidden="true"></i>
									<span id="paypalDelivery">${checkoutInfo.shipping.deliveryAddress}</span></label>
								<p>
									<a href="#" id="addDeliveryIntructionsLink">Add delivery
										Instructions</a>
								</p>

							</div>
						</div>


					</div>

				</div>
				<hr>

				<div class="row" id="deliveryTime">
					<div class="col-md-12">



						<!--   delivery time-->
					</div>
				</div>



				<div class="row" id="paymentMehtod">

					<div class="col-xs-4 col-sm-4 col-md-4 ">


						<p class="lead">
							Payment <br /> <a href="">Change / Add</a>
						</p>

					</div>

					<div class="col-xs-8 col-sm-8 col-md-8 ">


						<form action="/addPayment" method="POST">
							<script src="https://checkout.stripe.com/checkout.js"
								class="stripe-button" data-email="${customer.email}"
								data-label="+ Add a payment card"
								data-key="pk_test_otQnjjPy6y3fnMDITtKlPXJn"
								data-name="Luxmart Food Delivery"
								data-panel-label="Save new card"
								data-label="Update Card Details" data-allow-remember-me=false
								data-locale="auto">
  </script>

							<script>
								// Hide default stripe button, be careful there if you
								// have more than 1 button of that class
								document.getElementsByClassName("stripe-button-el")[0].style.display = 'none';
							</script>
							<br />
							<button type="submit" class="btn btn-outline-primary btn-lg">
								+ Add a payment card</button>

						</form>
						<br />


						<!-- paypal Payment -->
						<div id="paypal-button"></div>


					</div>
				</div>








				<hr>

				<div class="row" id="cartSummary">
					<div class="col-md-12">


						<section class="" id="cart">

							<!-- working with handleBar templates and json data -->
							<div id="cartItemsContainer"></div>


						</section>

					</div>
				</div>

				<hr>
			</section>


			<section class="col-xs-12 col-sm-12  col-md-4" id="cartInfo">

				<div class="row ">
					<div class="clearfix hidden-xs hidden-sm-up col-md-4 text-right">


						<figure class="figure  ">
							<img
								src='${checkoutInfo.restaurant.imageUrl}'
								class="figure-img img-fluid rounded" width="80" alt="logo">

						</figure>
					</div>
					<div class="col-xs-12 col-sm-12 col-md-8 text-left">
						<br> <span class="lead">Order From</span><br /> <span
							class="text-primary">${checkoutInfo.restaurant.name}</span>
					</div>
				</div>


				<div class="row">
					<div class="col-md-12">

						<form:form action="customer/placeOrder" commandName="amountStripe"
							method="post">

							<form:input path="amount" type="hidden" name="amount"
								id="getStripeAmount" />
								
								
			<form:input path="deliveryAddress" type="hidden" name="deliveryAddress" value="${checkoutInfo.shipping.deliveryAddress}" />
								
							<button type="submit" class="btn btn-success btn-block">Place
								Order</button>
								
								
								
						</form:form>



					</div>
				</div>


				<div class="row">
					<div class="clearfix hidden-xs  hidden-sm col-md-9">
						<span class="text-muted">Food & Beverage Subtotal</span> <span
							class="text-muted">Delivery Fee</span> <br /> <span
							class="text-muted">Courier Tip </span><br />



					</div>


					<div
						class="clearfix hidden-xs  hidden-sm col-md-3 text-right text-muted">
						$<span id='getGrandTotal'>0.0</span><br /> $<span
							id='getDeliveryFee'>$0.0</span><br /> $<span id='getCourierTip'>0.0</span><br />

					</div>
				</div>

				<div class="row">
					<div class="col-md-12 text-right"
						aria-label="Button group with nested dropdown">




						<div class="btn-group" role="group">
							<button type="button" class="btn btn-info" value="10">10%</button>
							<button type="button" class="btn btn-info" value="15">15%</button>
							<button type="button" class="btn btn-info" value="20">20%</button>


						</div>

					</div>


				</div>




				<hr />
				<div class="row">
					<div class="col-md-10 text-left">
						<p class="lead">Total</p>
					</div>

					<div class="col-md-2 text-right">

						$<span id='getTotalOrderPrice'>0.00</span>


					</div>


				</div>


			</section>




		</article>
	</article>





</section>



<section id="formSubmit">

	<form:form commandName="checkoutInfo" method="post" id="coordsForm">
		<!-- google maps properties -->
		<form:input path="shipping.googleLat" type="hidden" name="latitude"
			id="googleLat" />
		<form:input path="shipping.googleLng" type="hidden" name="longitude"
			id="googleLng" />
		<form:input path="shipping.deliveryAddress" type="hidden"
			name="deliveryAddress" id="deliveryAddress" />

		<!-- shipping properties properties -->

		<form:input path="shipping.streetNumber" type="hidden"
			name="street_number" id="street_number" />
		<form:input path="shipping.streetName" type="hidden" name="route"
			id="route" />
		<form:input path="shipping.city" type="hidden" name="locality"
			id="locality" />
		<form:input path="shipping.state" type="hidden"
			name="administrative_area_level_1" id="administrative_area_level_1" />
		<form:input path="shipping.postalCode" type="hidden"
			name="postal_code" id="postal_code" />
		<form:input path="shipping.country" type="hidden" name="country"
			id="country" />

		<!-- Shipping Delivery Details -->
		<form:input path="shipping.apartmentNumber" type="hidden"
			name="apartmentNumber" id="apartmentNumber" />
		<form:input path="shipping.deliveryInstructions" type="hidden"
			name="deliveryInstructions" id="deliveryInstructions" />
	</form:form>





</section>

<!-- HandleBar script for working with json data -->
<script id="cart-template" type="text/x-handlebars-template">


<div class="row">
<div class=" col-xs-12 col-sm-12 col-md-4 ">


<p class="lead">Summary <br />

 <a href="/store">Return to Menu</a>
</p>

</div>



<div class=" col-xs-12 col-sm-12 col-md-8">



 

                 

                 {{#each cartItems}} 



<div class="row">
<div class="col-md-1"></div>
<div class="col-xs-2 col-sm-2 col-md-9">
{{product.category}}
</div>
<div class="hidden-xs hidden-sm col-md-2 "></div>
</div>
		

    <div class="row" style="text-align: left">

		<div class="col-xs-2 col-sm-2 col-md-1">
<p  class="text-primary">  {{quantity}} </p> 
		                 
		</div>

		<div class=" col-xs-8 col-sm-8 col-md-9">
		                           
		<p  class="text-primary"> {{product.name}} </p>                               
		</div>



		<div class=" col-xs-2 col-sm-2 col-md-2">
<p  class="text-primary"><span class="text-muted">X</span> <span> $</span>{{product.price}} </p>      
	    
		</div>

		

		</div>

                 {{/each}}




</div>

</div>






                 </script>


<!-- button to avoid an error in AjaxRestAPI file -->
<input type="hidden" id="btnCheckout" />

<script src="../../resources/jquery/jquery.js"></script>
<script src="../../resources/jquery/jquery-ui.js"></script>

<!-- work with google maps to get delivery address and pop up windows-->
<script src="../../resources/js/getDeliveryCoordsCheckout.js"></script>

<!-- HandleBar template for working with json data -->
<script src="../../resources/JsonAndAjax/handlebars-v4.0.11.js"></script>
<!-- javaScript file for working with json data and HanderBar -->
<!-- Ajax api file -->
<script src="../../resources/JsonAndAjax/AjaxRestApiCheckout.js"></script>

<!-- Paypal script for payment button -->
<script src="../../resources/JsonAndAjax/paypal.js"></script>


<%@ include file="../include/footer.jsp"%></html>
