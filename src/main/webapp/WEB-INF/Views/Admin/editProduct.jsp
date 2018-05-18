<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks3.jsp" %>


<section class="container starter-template" >
	
	  <div class="page-header">
	  
	  <h3>Edit product</h3>
	 
	  
	  	  <p class="lead">Please update the product information here from   restaurant</p>
	  </div>
	  
	 <form:form action="${pageContext.request.contextPath}/admin/product/editProduct" method="post" commandName="product" enctype="multipart/form-data">
			 <div class="form-group">
			 <label for="name">Name</label>
			 <form:input path="name" id="name" class="form-control" />
			 </div>
			 
			 <form:input path="id" id="id" type="hidden" />
			 
			 <form:hidden path="status" value="Active" />
			
			 
			 
			 
			   <div class="form-group">
			 <label for="category">Category </label>
			 <form:input path="category" id="category" name ="category" class="form-control"/>
			 
			 </div>
			 
			 
			  <div class="form-group">
			 <label for="category_description">Category Description</label>
			 <form:input path="category_description" id="category_description" name ="category_description" class="form-control"/>
			 
			 </div>
			 
			
			 
			 
			  <div class="form-group">
			 <label for="category">Type</label>
			 <form:input path="type" id="category" class="form-control" />
			 </div>
			 
			  <div class="form-group">
			 <label for="description">Description</label>
			 <form:input path="description" id="description" class="form-control" />
			 </div>
			 
			 <div class="form-group">
			 <label for="price">Price</label>
			 <form:input path="price" id="price" class="form-control" />
			 </div>
			 
			  <div class="form-group">
			 <label >Manufacturer</label> <br />
			  <form:input path="manufacturer" id="manufacturer" class="form-control" readOnly="true" />
			 </div>
			 
			 
			   <div class="form-group">
			 <label for="productImage" class="control-label">Change Picture</label><br />
			 <img src='<c:url value='${product.imageUrl}' />' alt="${product.name}" style="width:250px; "  class="img-thumbnail img-responsive"/>
			 <br /><form:input path="productImage" id="productImage" class="form-control" type="file" name="uploadImageName" />
			 </div>
			 
			 
			 
			 <br><br>		 
			 <input type="submit" class="btn btn-default" value="Update Product" />
			 <a href='<c:url value="/admin/productInventory/${product.manufacturer}"/>' class="btn btn-default" >Cancel</a>
	 </form:form>
	  
</section>


</body>
 <%@ include file="../include/footerContent.jsp" %>
</html>
