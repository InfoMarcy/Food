<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks2.jsp" %>

<section class="container starter-template">
	
	  <div class="page-header">
	  
	  <h3>Product Detail</h3>
	  <p class="lead">Here is the information of the product</p>
	  <hr />
	  </div>
	<article class="container">
	<div class="row">
	<div class="col-md-4">
	
	<img src='<c:url value='${product.imageUrl}' />' alt="${product.name}" style="width:100%; "  class="img-thumbnail img-responsive"/>
				
	</div>
	
	<div class="col-md-6">
	<h3>${product.name}</h3>
	<p class="lead text-capitalize">${product.description}</p>
	<p>
	<strong>Manufacturer</strong> : ${product.manufacturer}
	
	
	</p>
	<p><strong>Category</strong> : ${product.category}</p>
	<p><strong>Type</strong> : ${product.type}</p>
	<h4> ${product.price} USD</h4>
	
	<br>
	<!-- define a variable call role in which if your are an admin when you click back it will take you the the   productInventory otherwise to the product list page -->
	<c:set var="role" scope="page" value="${param.role}"></c:set>
	<c:set var="url" scope="page" value="/admin/productInventory/${product.manufacturer}"></c:set>
	<c:if test="${role='Role_Admin'}">
	<c:set var="url" value="/admin/productInventory/${product.manufacturer}" />
	</c:if>
	
	<a href="<c:url value='${url}' />" class="btn btn-default">Back</a>
	
	
	</div>
	
	<div class="col-md-2">
	
	</div>
	
	</div>
	</article>
	  
	  
		           
	 
</section>

</body>
 <%@ include file="../include/footerContent.jsp" %>
</html>
