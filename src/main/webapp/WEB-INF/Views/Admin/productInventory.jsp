<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks2.jsp" %>

<script>
    $(document).ready(function(){
      
        $('.table').DataTable({
            "lengthMenu": [[5,10,15,20,25,-1], [5,10,15,20,25, "All"]],
          
        });
    });

</script>


  <!--  used to use function such as forEach in table -->
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="container starter-template" >
	
	  <div class="page-header">
	  
	  <h3>Product Inventory page</h3>
	  <p class="lead">This is the product inventory page</p>
	  </div>
	  
	  <table class="table table-striped table-hover text-center" id="productInventoryId">
	  
				  <thead>
							  <tr class="bg-success">
										     <th>Photo Thumb</th>
										     <th>Product Name</th>
										     <th>Category</th>
										     <th>Type</th>
										     <th>Price</th>
										     <th></th>
										     <th>Add Ons</th>
							  </tr>
				  
				  </thead>
				  
				  <c:forEach items="${products}" var="getProducts">
				  <tr>
				  <td class="align-middle"><img src='<c:url value='${getProducts.imageUrl}' />' alt="${getProducts.name}" style="width: 100px;"  class="img-thumbnail" /></td>
				  <td class="align-middle">${getProducts.name}</td>
				  <td class="align-middle">${getProducts.category}</td>
				  <td class="align-middle">${getProducts.type}</td>
				  <td class="align-middle">${getProducts.price} CAD</td>
				  <td class="align-middle">
				  
				  <a href='/admin/viewProduct/${getProducts.id}' >
				  <i class="fa fa-info-circle" aria-hidden="true"></i>
				  </a>
				  
				    <a href="#" onclick="deleteProduct('${getProducts.id}')">
				 <i class="fa fa-trash" aria-hidden="true"></i>
				  </a>
				 
				   <a href='/admin/product/editProduct/${getProducts.id}' id="editProduct">
				 <i class="fa fa-pencil" aria-hidden="true"></i>
				  </a>
				  
				  
				  
				  </td>
				  
				  <td class="align-middle">
				  
				  <a href='/admin/product/productAddOn/${getProducts.id}' >
				<i class="fa fa-clone" aria-hidden="true"></i>
				 </a>
				  
				  </td>
				  
				  </tr>
				  </c:forEach>
				
	  
	  </table>
	    
		  <a href="<c:url value='/admin/product/addProduct/${restaurant}' />" class="btn btn-primary">Add Product</a>  
		 
</section>



</body>

                 <!--delete record  ajax call -->
                <script src="../../resources/JsonAndAjax/productInventory.js"></script>
 <%@ include file="../include/footerContent.jsp" %>
 <%@ include file="../include/footerLinks.jsp" %>
</html>

 
  