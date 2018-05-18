$(function () {
	$(window).on("load resize", function (){
		
		
		
	});
	
	
	//smooth scrolling
	$('nav a, down-button a').bind('click', function(){
		$('html', 'body').stop().animate({
			scrollTop:$($(this).attr('href')).offset().top
		}, 1500, 'easeInOutExpo');
		event.preventDefault();
		
		// initialize WOW for Element animation
		new WOW().init();

		});
	
	
	
});



