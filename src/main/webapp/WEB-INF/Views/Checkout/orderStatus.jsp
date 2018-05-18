
<%@ include file="../include/header.jsp" %>
<header class="container-fluid">
<%@ include file="../include/storeNavbar.jsp" %>
</header>

<link href="../../resources/jquery/jquery-ui.css" rel="stylesheet" />


<section class="container starter-template">



<c:choose>
    <c:when test="${error}">
     <h3 style='color: red;'>${error}</h3>
    </c:when>    
    <c:otherwise>
       <h3 style='color: green;'>Success!</h3>
            <div>Id.: <span>${id}</span></div>
            <div>Status: <span>${status}</span></div>
            <div>Charge id.: <span>${chargeId}</span></div>
            <div>Balance transaction id.: <span>${balance_transaction}</span></div>
    </c:otherwise>
</c:choose>


        <a href='/checkout.html'>Checkout again</a>


















</section>
<script src="../../resources/jquery/jquery.js"></script>
  <script src="../../resources/jquery/jquery-ui.js"></script>  
<%@ include file="../include/footer.jsp" %>
