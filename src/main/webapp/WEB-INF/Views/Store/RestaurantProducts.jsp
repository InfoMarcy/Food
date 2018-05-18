<%@ include file="../include/header.jsp" %>



<link href="../../resources/jquery/jquery-ui.css" rel="stylesheet" />


<header class="container-fluid">
<%@ include file="../include/storeNavbar.jsp" %>
</header>

  <!--  used to use function such as forEach in table -->
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     
     
     
  
 
 <article class="starter-template">
<article class="row">

<article class="col-md-8">

<section class="container"  id="page_product"">
	
	  <div class="page-header">
	 
	  <img src='<c:url value='${restaurant.imageUrl}' />'  alt="image" style="width:180px" />	
	 	 
	  <p class="lead">${restaurant.streetName} <br/>
	 <!--   Delivery Hours: 11:30AM - 4:30PM -->
	  <input type="hidden" value="${cartId}"  id="cartId"/>
	<span id='getGrandTotalPrice' style="display:none;"></span> 
	
	  </p>
	  
	
	  </div>
	
	 
	  <header class="container-fluid" style="padding:10px 10px 10px 0px; margin:0px; display:none;">
	  
					      <nav class="navbar navbar-default">
								  <div class="container-fluid">
								    <div class="navbar-header">
								    <c:forEach items="${restProductCategory}" var="getProductCategory">
								    
								     <a class="navbar-brand" href="#">${getProductCategory}</a>
								    
								    </c:forEach>
								     
								      
								    </div>
								 
							
								</div>
					</nav>     
     
	  
	  
	  </header>
	  
	
			
							<!-- display the List of restaurants retrieved by 3 columns -->
							<c:set var="i" value ="0" />
							<c:set var="count" value ="0" />
							
						<c:forEach items="${restProductCategory}" var="getProductCategory">
								   
								    <div class="row">
								    <div class="col-md-12">
								     <h5 style="padding-top:10px;">${getProductCategory}</h5>
								    </div>								    
								    </div>
								  
								    
								    
							<!-- iterate through the list of restaurants to display the data -->
							<c:forEach items="${products}" var="getProducts">
						
						
					<c:choose>
					<c:when test="${count == 0}">
					<div class="row" id="${getProductCategory}">
					</c:when>
					<c:when test="${count mod 2 == 0}">
					
					<div class="row" id="${getProductCategory}">
					</c:when>
					
					</c:choose>
						
						
					
				<c:choose>
						  <c:when test="${ getProductCategory == getProducts.category}">
						  
						
						 
						
						  
						  <c:set var="count" value="${count + 1}" scope="page"/>
						  
						<!-- divide the row in 3 columns -->
							
							
							<div class="col-md-12 gridRestaurantsChildren">
							
							
				         
							<div class="grid gridRestaurants gridRestaurantsChildren2 ">
							  <div class="row">
							 
							  <div class="col-md-2">
							  
							  
							  <figure class="figure">
										  <img src='<c:url value='${getProducts.imageUrl}' />'										  
										  class="figure-img img-fluid rounded" alt="${getProducts.name}">
										
										</figure>
							  
							 
	
							  </div>
							 
							 <div class="col-md-10">
							  
							  
							
						     <div class="row">
							 
							<div class="col-md-12">  
							     <span class="text-primary">${getProducts.name.toUpperCase()}</span> 
							</div>	
							
							
							
							
							
							</div>
							
							  <div class="row">
							  
							 
							  <div class="col-md-6">
							  
							  <p class="text-muted">${getProducts.description}
							 
							 
							  
							  
							 </p>
							  </div>
							  
							  <div class="col-md-3 align-middle text-muted">
							  
							  <span>$${getProducts.price}</span>
							  </div>
							   <div class="col-md-3 align-middle">
							   
							   <button type="button" class="btn btn-outline-primary btn-md pull-right"							  
							    onclick="addProduct('${getProducts.id}', '${restaurant.name}')">
							    <i class="fa fa-plus" aria-hidden="true"></i>
							    </button>
							 
							   </div>
							  
							  </div>
							 
							  
							  
							  </div>
							
							 </div>
							
							</div>
							
								</div>
									
								</c:when>
								
												<c:otherwise>
				                                               <c:if test="${count mod 2 != 0 }">
				                                               
				                                              <div style="display:none;">
				                                             <c:out value="${count = 0}"></c:out>
				                                              </div>
				                                               </c:if>
				                                </c:otherwise>
                                
                                
									</c:choose>	
											
									<c:choose>
					<c:when test="${count == 0}">
					</div>
					</c:when>
					<c:when test="${count mod 2 == 0}">
					
					</div>
					</c:when>
					
					</c:choose>
					
					
					
	
					
							
							</c:forEach >
							
							</c:forEach>
							
	
	
		  
	 
</section>
</article>
<article class="col-md-4" id = "page_cart">
<section class="container" id="cart">









 <!-- working with handleBar templates and json data -->
<div id="cartItemsContainer">



</div>




</section>
</article>

 

</article>

<br />
<a href="/restaurants" class="btn btn-primary">Back to Restaurants</a>
</article> 

 <!-- HandleBar script for working with json data -->
		       <script id="cart-template" type="text/x-handlebars-template">


<div class="row">
<div class="col-md-12">
<h5>Your Order</h5>
<p class="lead-text" style="color:red">${restaurant.name.toUpperCase()}</p>

 <a href="<c:url value='/order/${cartId}' />" class="btn btn-success" id="btnCheckout" ><i class="fa fa-shopping-cart" aria-hidden="true"></i> Proceed to Checkout $<span id='getGrandTotal'></span></a>   
<hr />
</div>

</div>
                 

                 {{#each cartItems}} 
		

    <div class="row" style="text-align: left">

		<div class="col-md-1">
		<h5> {{quantity}} </h5>                   
		</div>

		<div class="col-md-6">
		{{product.category}} <br/>                             
		<h5> {{product.name}} </h5>                               
		</div>



		<div class="col-md-2">
		<h5> {{totalPrice}} </h5>         
		</div>

		<div class="col-md-3">
		
		<a href="#" class="label label-danger" onclick="removeProduct('{{product.id}}')">Remove</a>
		
		</div>

		</div>

                 {{/each}}


                 </script>
    



<script src="../../resources/jquery/jquery.js"></script>
  <script src="../../resources/jquery/jquery-ui.js"></script>


                  <!-- HandleBar template for working with json data -->
		       <script src="../../resources/JsonAndAjax/handlebars-v4.0.11.js"></script>
		       <!-- javaScript file for working with json data and HanderBar -->
		       <!-- Ajax api file -->
                <script src="../../resources/JsonAndAjax/AjaxRestApi.js"></script>
               
                


<%@ include file="../include/footer.jsp" %>
