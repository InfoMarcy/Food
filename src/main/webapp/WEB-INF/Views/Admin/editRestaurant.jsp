<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks3.jsp" %>



<section class="container starter-template" >
	
	  <div class="page-header">
	  
	  <h3>Edit product</h3>
	  <p class="lead">Please Update the product information here</p>
	  </div>
	  
	 <form:form action="${pageContext.request.contextPath}/admin/product/editProduct" method="post" commandName="product" enctype="multipart/form-data">
			 <div class="form-group">
			 <label for="name">Name</label>
			 <form:input path="name" id="name" class="form-control" />c
			 </div>
			 
			 <form:input path="id" id="id" type="hidden" />
			 
			 <form:hidden path="status" value="Active" />
			
			 
			 <div class="form-group">
			 <label for="category">Category</label>
              <label class="checkbox-inline"><form:radiobutton path="category" id="category" value="Brunch"/>Brunch</label>
              <label class="checkbox-inline"><form:radiobutton path="category" id="category" value="Lunch"/>Lunch</label>
              <label class="checkbox-inline"><form:radiobutton path="category" id="category" value="Dinner"/>Dinner</label>
			 <label class="checkbox-inline"><form:radiobutton path="category" id="category" value="Drinks"/>Drinks</label>
			 </div>
			 
			 
			  <div class="form-group">
			 <label for="category">Type</label>
              <label class="checkbox-inline"><form:radiobutton path="type" id="type" value="American"/>American</label>
              <label class="checkbox-inline"><form:radiobutton path="type" id="type" value="Mexican"/>Mexican</label>
              <label class="checkbox-inline"><form:radiobutton path="type" id="type" value="Pizza"/>Pizza</label>
			 </div>
			 
			 
			 
			  <div class="form-group">
			 <label for="description">Description</label>
			 <form:input path="description" id="description" class="form-control" />
			 </div>
			 
			 <div class="form-group">
			 <label for="price">Price</label>
			 <form:input path="price" id="price" class="form-control" />
			 </div>
			 
			
             <!-- 
              <div class="form-group">
			 <label for="status">Status</label>
              <label class="checkbox-inline"><form:radiobutton path="status" id="status" value="active"/>Active</label>
              <label class="checkbox-inline"><form:radiobutton path="status" id="status" value="inactive"/>Inactive</label>
             </div>
             
              -->
			 
			 
			  <div class="form-group">
			 <label for="manufacturer">Manufacturer</label>
			 <form:input path="manufacturer" id="manufacturer" class="form-control" />
			 </div>
			 
			   <div class="form-group">
			 <label for="productImage" class="control-label">Upload Picture</label><br />
			 <form:input path="productImage" id="productImage" class="form:input-large" type="file" />
			 </div>
			 
			 
			 
			 <br><br>		 
			 <input type="submit" class="btn btn-default" value="Update Product" />
			 <a href='<c:url value="/admin/productInventory"/>' class="btn btn-default" >Cancel</a>
	 </form:form>
	  
</section>



</body>
 <%@ include file="../include/footerContent.jsp" %>
</html>
