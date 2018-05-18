<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks3.jsp" %>


<section class="container starter-template" >
	
	  <div class="page-header">
	  
	  <h3>Add product</h3>
	  <p class="lead">Fill the below information to add a product</p>
	  </div>
	  
	  
	  <form:form action="${pageContext.request.contextPath}/admin/product/addProduct" method="post" commandName="product" enctype="multipart/form-data">
			
			
			
			
			 <div class="form-group">
			 <label for="name">Name<form:errors path="name" cssStyle="color:#ff0000;" /></label>
			 <form:input path="name" id="name" class="form-control" />
			 </div>
			 
			 
	
		 
			 
			  <div class="form-group">
			 <label for="description">Description</label>
			 <form:input path="description" id="description" class="form-control" />
			 </div>
			

			 
			 <div class="form-group">
			 <label for="price">Price<form:errors path="price" cssStyle="color:#ff0000;" /></label>
			 <form:input path="price" id="price" class="form-control" />
			 </div>
			 
			 
			 <div class="form-group">
			 <label for="price">Category</label>
			 <form:input path="category" id="category" name ="category" class="form-control"/>
			 
			 </div>
			 
			 
			 <div class="form-group">
			 <label for="category_description">Category Description</label>
			 <form:input path="category_description" id="category_description" name ="category_description" class="form-control"/>
			 
			 </div>
			 
			
			<div class="form-group">
			 <label for="manufacturer">Manufacturer</label>
			 <input  id="manufacturer" name ="manufacturer" value="${restaurant.name}" class="form-control" readOnly/>
			 
			 </div>
			 
		
			 <form:hidden path="status" value="Active" />
			
			 
			   <div class="form-group">
			 <label for="productImage" class="control-label">Upload Picture</label><br />
			 <form:input path="productImage" id="productImage" class="form-control" type="file" />
			 </div>
			
			 		
			 <br><br>		 
			 <input type="submit" class="btn btn-default" value="Submit" />
			 <a href='<c:url value="/admin/productInventory/${restaurant.name}"/>' class="btn btn-default" >Cancel</a>
	
	 </form:form>
	  
</section>
</body>
 <%@ include file="../include/footerContent.jsp" %>
</html>
