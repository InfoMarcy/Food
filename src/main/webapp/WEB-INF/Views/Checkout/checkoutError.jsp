
<%@ include file="../include/header.jsp" %>
<header class="container-fluid">
<%@ include file="../include/storeNavbar.jsp" %>
</header>

<link href="../../resources/jquery/jquery-ui.css" rel="stylesheet" />



<section class="container starter-template">



<div class="Jumbotron">
<article class="container">

<h1>Payment Failed</h1>

<p class="lead">Sorry! We could not process your payment with the details you provided. <br />Please ensure your
card details are correct and that you have enough funds in your account.</p>

<a href="<c:url value='/order/1' />" class="btn btn-success" id="btnCheckout" >
<i class="fa fa-shopping-cart" aria-hidden="true"></i> Go Back to Checkout</a>   



</article>

<section class="container">



</section>
</div>


</section>

<%@ include file="../include/footer.jsp" %>
