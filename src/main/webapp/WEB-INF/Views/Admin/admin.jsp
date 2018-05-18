
<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks.jsp" %>

<section class="container starter-template" >
<div class="page-header">
<h3>Administrator Page</h3>
</div>

<p class="lead">This is the administrator page</p>

<p>
 Welcome: ${pageContext.request.userPrincipal.name} 
</p>
<hr />

<!--  
<h5>
	<a href='<c:url value='/admin/productInventory' />'>Product Inventory</a>
</h5>



<p>Here you can view, check and modify the product inventory</p>


<br>

<hr />
-->
<h5>
	<a href='<c:url value='/admin/restaurant' />'>Restaurant Management</a>
</h5>

<p>Here you can view the Restaurant information</p>


<br>

<hr />

<h5>
	<a href='<c:url value='/admin/foodTypeImage' />'>Restaurant Type Images</a>
</h5>

<p>Here you can add the Images</p>


<br>

<hr />
<sec:authorize access="hasRole('ADMIN')" >
<h5>
	<a href='<c:url value='/admin/customer' />'>Customer Management</a>
</h5>

<p>Here you can view the customer information</p>

</sec:authorize>
</section>



</body>
 <%@ include file="../include/footerContent.jsp" %>
</html>

 