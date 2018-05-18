<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks.jsp" %>

<section class="container starter-template" >
	
	  <div class="page-header">
	  
	  <h1>Customer Management Page</h1>
	  <p class="lead">This is the customer management page</p>
	  </div>
	  
	  <table class="table table-striped table-hover text-center">
	  
				  <thead>
							  <tr class="bg-success">
										     <th>Name</th>
										      <th>Email</th>
										       <th>Phone</th>										       
										         <th>Enabled</th>
										   
							  </tr>
				  
				  </thead>
				  
				  <c:forEach items="${customerList}" var="customer">
				  <tr class="text-left">
					
				  <td class="align-middle">${customer.firstName} ${customer.lastName}</td>
				    <td class="align-middle">${customer.email}</td>
				      <td class="align-middle">${customer.phone}</td>
				        <td class="align-middle">${customer.enabled}</td>
				  
				  </tr>
				  </c:forEach>
				
	  
	  </table>
	  
		           
	 
</section>


</body>
 <%@ include file="../include/footerContent.jsp" %>
</html>
