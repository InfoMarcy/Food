<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks2.jsp" %>

<!-- google maps key and api -->
 <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDAY1k9MQx9eRui6lRpiXpacNW4HWOi2aQ&libraries=places"></script>
      

<section class="container starter-template" >
	
	  <div class="page-header">
	  
	  <h3>Add Restaurant</h3>
	  <p class="lead">Fill the below information to add a Restaurant</p>
	  </div>
	  
	  
	  
			 
	  
	 <form:form action="${pageContext.request.contextPath}/admin/restaurant/addRestaurant" method="post" commandName="restaurant" enctype="multipart/form-data">
			
			<div class="form-group">
			 <label for="restName">Name</label>
			 <form:input path="restaurant.name" id="restName" name ="restName" class="form-control"/>			 
			 </div>
			 
			 
			  <div class="form-group">
			 <label for="restType">Type</label>
			 <form:input path="restaurant.type" id="restType" class="form-control" />
			 </div>
			 
			 
			 <div class="form-group">
			 <label for="RestDescription">Description</label>
			 <form:input path="restaurant.description" id="RestDescription" name ="RestDescription" class="form-control"/>	 
			 </div>
			 
			 
			 <div class="form-group">
			 <label for="restEmail">Email</label>
			 <form:input path="restaurant.email" id="restEmail" name ="restEmail" class="form-control"/>	 
			 </div>
			
			 <div class="form-group">
			 <label for="restphone">Phone</label>
			 <form:input path="restaurant.phone" id="restphone" name ="restphone" class="form-control"/>	 
			 </div>		
			 
			  <div class="form-group">
			 <label for="address">Address</label>			
			  <input type="text" id="address" name="address" class="form-control input-lg" autocomplete="false" />
   
			 </div>	 
			 
			 
			 <!-- location properties -->
			  <div class="form-group">
			  <label for="restSuiteNumber">Suite Number</label><br />
			 <form:select path="location.apartmentNumber">			
			 <form:option value="main" selected="selected">Main Floor</form:option>			 
			 <form:option value="2">2 Floor</form:option>
			 <form:option value="3">3 Floor</form:option>          
	         </form:select>
			   </div>	

            <form:input path="location.streetNumber" type="hidden" name="street_number" id="street_number" />
            <form:input path="location.streetName" type="hidden" name="route" id="route" />
            <form:input path="location.city" type="hidden" name="locality" id="locality" />
            <form:input path="location.state" type="hidden" name="administrative_area_level_1" id="administrative_area_level_1"  />
            <form:input path="location.postalCode" type="hidden" name="postal_code" id="postal_code" />
             <form:input path="location.country" type="hidden" name="country" id="country" />
             <form:input path="location.latitude" type="hidden" name="latitude" id="googleLat"  />
            <form:input path="location.longitude" type="hidden" name="longitude" id="googleLng"  />
			 
			 
			 <div class="form-group">
			 <label for="restnotes">Notes about how to find the Restaurant</label>
			 <form:input path="restaurant.notes" id="restnotes" name ="restnotes" class="form-control"/>	 
			 </div>	
			 
			 
			   <div class="form-group">
			 <label for="restLogo" class="control-label">Upload Picture</label><br />
			 <form:input path="restaurant.logo" id="restLogo" class="form-group" type="file" />
			 </div>
			 
			 <br><br>		 
			 <input type="submit" class="btn btn-default" value="Submit" />
			 <a href='<c:url value="/admin/restaurant"/>' class="btn btn-default" >Cancel</a>
	 </form:form>
	  
</section>

 <!-- work with google maps to get delivery address and pop up windows-->
    <script src="../../resources/js/getRestaurantAddress.js"></script>


</body>
<%@ include file="../include/footerLinks.jsp" %>
 <%@ include file="../include/footerContent.jsp" %>
</html>

 