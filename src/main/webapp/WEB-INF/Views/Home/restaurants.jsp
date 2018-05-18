<%@ include file="../include/header.jsp"%>

<!--  used to use function such as forEach in table -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header class="container-fluid">
	<%@ include file="../include/navbar.jsp"%>
</header>

<div  id="carousel" class="container-fluid">

 
<%@ include file="../include/carousel.jsp" %>

</div>

<div id="headerType">

	<%@ include file="../include/headerType.jsp"%>

</div>

<section class="container-fluid">



	<article>
		<!-- if food type is not equal to view all show label -->
		<c:if test="${(foodType ne 'View All') && (not empty foodType)}">
			<h4 class="text-left">Results for "${foodType}"</h4>
			<!-- if the count is not equal to 0 show the number otherwise says No -->
			<c:choose>
				<c:when test="${restCount ne 0}">

					<p class="text-left">${restCount}stores nearby</p>
				</c:when>
				<c:otherwise>
					<p class="text-left">No stores nearby</p>
				</c:otherwise>

			</c:choose>


		</c:if>
		<div class="row">

			<!-- display the List of restaurants retrieved by 3 columns -->
			<c:set var="i" value="0" />


			<!-- iterate through the list of restaurants to display the data -->
			<c:forEach items="${restaurants}" var="getRestaurant">



				<!-- divide the row in 3 columns -->
				<div class="col-md-4 gridRestaurantsChildren">


					<a href="/store?name=${getRestaurant.name}"
						style="text-decoration: none;">


						<div class="grid gridRestaurants gridRestaurantsChildren">

							<div class="row">

								<div class="col-md-4">

									<figure class="figure">
										<img src='<c:url value='${getRestaurant.imageUrl}' />'
											class="figure-img img-fluid rounded" alt="image">

									</figure>



								</div>

								<div class="col-md-8">

									<ul class="restItems">

										<li class="text-primary" style="padding-top: 10px;">${getRestaurant.name.toUpperCase()}</li>
										<li>${getRestaurant.type}</li>
										<li>${getRestaurant.streetName}</li>
										<li>Distance: ${getRestaurant.distance} km</li>

									</ul>



								</div>

							</div>


						</div>

					</a>


				</div>


			</c:forEach>

		</div>
	</article>

</section>


</body>
<%@ include file="../include/footerContent.jsp"%>
<%@ include file="../include/footerLinks.jsp"%>
</html>
