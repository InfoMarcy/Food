<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks.jsp" %>

<script>
    $(document).ready(function(){
      
        $('.table').DataTable({
            "lengthMenu": [[5,10,15,20,-1], [5,10,15,20, "All"]],
          
        });
    });

</script>
  <!--  used to use function such as forEach in table -->
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<section class="container starter-template" >
	
	  <div class="page-header">
	  
	  <h1>Restaurant Management Page</h1>
	  <p class="lead">This is the restaurant management page</p>
	  </div>
	  
	  <table class="table table-striped table-hover text-center">
	  
				  <thead>
							  <tr class="bg-success">										     
										      <th>Name</th>
										       <th>Type</th>
										        <th>Phone</th>
										         <th>Email</th>
										         <th>Location</th>
										         <th></th>
										         
							  </tr>
				  
				  </thead>
				  
				  <c:forEach items="${restaurants}" var="restaurant">
				  <tr class="text-left">
					
				  <td class="align-middle">${restaurant.name}</td>
				    <td class="align-middle">${restaurant.type}</td>
				    <td class="align-middle">${restaurant.phone}</td>
				     <td class="align-middle">${restaurant.email}</td>
				    <td class="align-middle">${restaurant.streetName}</td>
				    
				    <td> 
				    
				    <a href='<c:url value='/admin/productInventory/${restaurant.name}' />' class="btn btn-primary pull-right">Product Inventory</a>
				    
				    </td>
				     
				 
				  </tr>
				  </c:forEach>
				
	  
	  </table>
	  
		  <br />
		  <a href="/admin/restaurant/addRestaurant" class="btn btn-primary btn-lg">Add Restaurant</a> 
		
</section>





</body>
 <%@ include file="../include/footerContent.jsp" %>
</html>

 