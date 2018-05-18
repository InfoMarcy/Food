<%@ include file="../include/header.jsp" %>

<header class="container-fluid">
<%@ include file="../include/accountNavbar.jsp" %>
</header>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<article class="container starter-template" >

<div class="row main">
 <div class="col-md-3 text-center">
 
             <figure class="figure">
					<img class="figure-img img-fluid rounded" src="http://oldhamgoodwin.com/wp-content/uploads/2011/09/Marci-Garcia.jpg" alt="${customer.firstName} ${customer.lastName}" width="120px">
						<figcaption class="figure-caption"><strong>${customer.firstName} ${customer.lastName}</strong></figcaption>
						
						
				
						
				
				</figure>
				
				<ul class="text-left">				
				<li><a href="/user/profile">Profile</a></li>
				<li><a href="/user/photo">Photo</a></li>
				<li><a href="/user/addresses">Addresses</a></li>				
				<li><a href="/user/payments">Payment Options</a></li>
				<li><a href="/user/orders">Your Orders</a></li>
				<li><a href="/user/notifications">Notifications</a></li>
				<li><a href="/user/deleteAccount">Danger Zone</a></li>
				</ul>
 
 
 </div>


         <div class="col-md-8">
            <h2 class="title">Your Addresses</h2>
            <p class="lead">Update your Billing and Shipping Information</p>
            <hr/>
            
           
        
        <form:form  action="${pageContext.request.contextPath}/user/addresses" method="post" class="m-t" role="form" commandName="customer">
      
       
        <form:input type="hidden" name="id" path ="customerId"/>
        
        
         <h3>Billing Address</h3>
		
		 <div class="form-group">	
				        
					   <label for="billingStreet">Street Name</label>
					  <form:input type="text"	path ="billingAddress.streetName" id="billingStreet" name="billingStreet" placeholder="Street Name" class="form-control" />
					
					          
		   </div>
		   
		   
		    <div class="form-group">	
					          <label for="billingApartmentNumber">Apartment Number</label>
					  <form:input type="text"	path ="billingAddress.apartmentNumber" id="billingApartmentNumber" name="billingApartmentNumber" placeholder="Apartment Number" class="form-control" />
					
					          
		   </div>
		   
		   
		     <div class="form-group">	
					          <label for="billingCity">City</label>
					  <form:input type="text"	path ="billingAddress.city" id="billingCity" name="billingCity" placeholder="City" class="form-control" />
					
					          
		   </div>
		   
		    <div class="form-group">	
					          <label for="billingState">State</label>
					  <form:input type="text"	path ="billingAddress.state" id="billingState" name="billingState" placeholder="State" class="form-control" />
					
					          
		   </div>
		   
		   
		     <div class="form-group">	
					          <label for="billingCountry">Country</label>
					  <form:input type="text"	path ="billingAddress.country" id="billingCountry" name="billingCountry" placeholder="Country" class="form-control" />
					
					          
		   </div>
		   
		   
		      <div class="form-group">	
					          <label for="billingZipCode">ZipCode</label>
					  <form:input type="text"	path ="billingAddress.zipCode" id="billingZipCode" name="billingZipCode" placeholder="Zip Code" class="form-control" />
					
					          
		   </div>
		   
		   <h3>Shipping Address</h3>
		   
		    <div class="form-group">	
					          <label for="shippingStreet">Street Name</label>
					  <form:input type="text"	path ="shippingAddress.streetName" id="shippingStreet" name="shippingStreet" placeholder="Street Name" class="form-control" />
					
					          
		   </div>
		   
		   
		    <div class="form-group">
					          <label for="shippingApartmentNumber">Apartment Number</label>
					  <form:input type="text"	path ="shippingAddress.apartmentNumber" id="shippingApartmentNumber" name="shippingApartmentNumber" placeholder="Apartment Number" class="form-control" />
					
					          
		   </div>
		   
		   
		     <div class="form-group">	
					          <label for="shippingCity">City</label>
					  <form:input type="text"	path ="shippingAddress.city" id="shippingCity" name="shippingCity" placeholder="City" class="form-control" />
					
					          
		   </div>
		   
		    <div class="form-group">
					          <label for="shippingState">State</label>
					  <form:input type="text"	path ="shippingAddress.state" id="shippingState" name="shippingState" placeholder="State" class="form-control" />
					
					          
		   </div>
		   
		   
		     <div class="form-group">	
					          <label for="shippingCountry">Country</label>
					  <form:input type="text"	path ="ShippingAddress.country" id="shippingCountry" name="shippingCountry" placeholder="Country" class="form-control" />
					
					          
		   </div>
		   
		   
		      <div class="form-group">	
					          <label for="shippingZipCode">ZipCode</label>
					  <form:input type="text"	path ="ShippingAddress.zipCode" id="shippingZipCode" name="shippingZipCode" placeholder="zipcode" class="form-control" />
					
					          
		   </div>
		   
		  
		
	 	<div class="form-group ">
                    <button type="submit" class="btn btn-primary btn-block" name="btnAddress">Save</button>
                </div>
		   
      </form:form>
     </div>
     </div>
   
</article>
  <%@ include file="../include/footer.jsp" %>
  
  
  