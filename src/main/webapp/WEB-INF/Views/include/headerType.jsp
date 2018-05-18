
<style type="text/css">

figure {

display: inline-block;
cursor: pointer;
}


figure:hover {
transform: scale(1.2);
transition: all 1s ease-in;

}
.image_horizonral_slider{


float: left;
margin: 5px;
padding: 5px;
width: 100px;
height:100px;
border-radius: 50px;
transition: all 1s ease-in;
}

.horizontalCarousel {
   
   width: 100;
    white-space:nowrap;
    overflow-x: scroll;
}


::-webkit-scrollbar {
    display: none;
}





</style>

<section class="horizontalCarousel">
<div class="row">
<div class="col-md-12" class="inner_Container_Scroll">

	<!-- iterate through the list of headers -->
					  <c:forEach items="${foodHeaders}" var="getHeaders">						
<a href="/restaurants/${getHeaders.foodType}">
				<figure>
						<div class="row">
						<div class="col-md-12">
										<img alt="" class="image_horizonral_slider" src="${getHeaders.imageUrl}"  >
						</div>
						</div>
						
						
						<div class="row">
						<div class="col-md-12">
									<p class="text-center text-capitalize">${getHeaders.foodType}</p>
						</div>
						</div>
				
				</figure>
				
				</a>

</c:forEach>



</div>
</div>
</section>
