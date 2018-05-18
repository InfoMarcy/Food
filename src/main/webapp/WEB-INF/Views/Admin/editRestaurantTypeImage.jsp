<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks2.jsp" %>
     

<section class="container starter-template col-md-4" >

	  <div class="page-header">
	 
	  <section class="container text-center">

	          <div class="form-group">
	          <figure>
			 <img alt="" class="img img-circle" src="${foodType.imageUrl}"  width="100px" style="border-radius: 50px;
transition: all 1s ease-in;"/>
<figcaption>Current Image</figcaption>
   </figure>
			</div>	
            

   </section>
	  
	  
	  
	 
	  </div>
	  
	  
	 <form:form action="${pageContext.request.contextPath}/admin/editFoodTypeImage" method="post" commandName="foodType" enctype="multipart/form-data">
			
			<form:hidden path="id" name="id" id="id"/>
			<form:hidden path="imageUrl" name="imageUrl" id="imageUrl"/>
			
			<div class="form-group">
			
			 <label for="foodType" class="control-label"><strong>Type</strong></label>
			 <form:input path="foodType" id="foodType" name ="foodType" class="form-control"/>			 
			 </div>
			 
			
				
			   <div class="form-group">
			 <label for="image" class="control-label"><strong>Change Picture</strong></label><br />
			 <form:input path="image" id="image" class="form-group" type="file" />
			 </div>
			 
			  <div class="form-group"> 
			 <input type="submit" class="btn btn-primary" value="Save" />
			  <a href="#" onclick="deleteHeaderType('${foodType.id}')" class="btn btn-danger" >Delete</a>
			 <a href='<c:url value="/admin/foodTypeImage"/>' class="btn btn-default" >Cancel</a>
			 
			 </div>
	 </form:form>
	 
	 

	
</section>



</body>
<script src="../../resources/JsonAndAjax/RestaurantTypeImage.js"></script>
<%@ include file="../include/footerLinks.jsp" %>
 <%@ include file="../include/footerContent.jsp" %>
</html>

 