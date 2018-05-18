<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks3.jsp" %>


<section class="container starter-template" >
	
	  <div class="page-header">
	  
	  <h3>Product Extra</h3>
	  <p class="lead">${product.name}</p>
	  </div>
	  
	 <form:form action="${pageContext.request.contextPath}/admin/product/productAddOn" method="post" commandName="productAddOn" enctype="multipart/form-data">
			
			 <div class="form-group">
			 <label for="category">Category</label>
			 <form:input path="category" id="name" class="form-control" />
			 </div>
			
			 <div class="form-group">
			 <label for="name">Name</label>
			 <form:input path="name" id="name" class="form-control" />
			 </div>
			 
			 
			  <div class="form-group">
			 <label for="name">Description</label>
			 <form:input path="description" id="description" class="form-control" />
			 </div>
			 
			 <form:input path="id" id="id" type="hidden" />
			 
			 
			 <form:input path="product.id" id="product" type="hidden" value="${product.id}" />
			
			
			 
			 <div class="form-group">
			 <label for="price">Price</label>
			 <form:input path="price" id="price" class="form-control" />
			 </div>
			 
			
			 
			 
			 <br><br>		 
			 <input type="submit" class="btn btn-default" value="Add To Product" />
			 <a href='<c:url value="/admin/productInventory"/>' class="btn btn-default" >Cancel</a>
	 </form:form>
	  
</section>


</body>
 <%@ include file="../include/footerContent.jsp" %>
</html>
