<%@ include file="../include/header.jsp" %>
<header class="container-fluid">
<%@ include file="../include/storeNavbar.jsp" %>
</header>


<!-- Main Content of the page -->
<article class="starter-template">


<section class="container">
<div class="Jumbotron">
<article class="container">


<c:if test="${not empty confirmationMessage}">
 <div class="alert alert-success" role="alert">
<h5>${confirmationMessage}</h5>
</div>
</c:if>


<c:if test="${not empty invalidToken}">
  <div  class="alert alert-danger" role="alert">
<h5>${invalidToken}</h5>
</div>

</c:if>





</article>
<article class="container">
<p>
<a href="/Login" class="btn btn-default" >Login</a>
</p>
</article>
</div>
</section>


  
		
      
     


</article>
<%@ include file="../include/footer.jsp" %>