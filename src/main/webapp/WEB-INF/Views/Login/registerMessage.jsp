<%@ include file="../include/header.jsp" %>
<header class="container-fluid">
<%@ include file="../include/accountNavbar.jsp" %>
</header>


<!-- Main Content of the page -->
<article  class="container starter-template">



<div class="Jumbotron">
<article class="container">


<c:if test="${not empty confirmationMessage}">
   <div class="alert alert-success" role="alert">
<h5>${confirmationMessage}</h5>
</div>
</c:if>

</article>
</div>


</article>

<%@ include file="../include/footer.jsp" %>