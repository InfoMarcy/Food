<%@page import="com.luxmart.model.GoogleLatLng"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
       <!--  add this tab to specify the library -->
    <%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>
    
     <!-- Spring security tag -->
      <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
      
   
      
        <!--  used to use function such as forEach in table -->
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="en">

  <head>
  

  
    <meta http-equiv="Content-Type" content="text/html">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Luxmart Food Delivery</title>

    <!-- Bootstrap core CSS -->
    <link href="../resources/vendor/bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Custom fonts for this template -->
    <link href="../resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>

    <!-- Custom styles for this template -->
    <link href="../resources/css/agency.css" rel="stylesheet">
     <link href="../resources/css/style.css" rel="stylesheet">
      <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDEjrJMVu39h6zTU6K_SapWFc3YXwTWu3M&libraries=places"></script>
      
      
     
  </head>

  <body id="page-top" style="padding-top: 0">

    <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
      <div class="container">
        <a class="navbar-brand js-scroll-trigger" href="#page-top">Luxmart Food Delivery</a>
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
          <ul class="navbar-nav ml-auto">
          
           <li class="nav-item">            
			 <a class="nav-link js-scroll-trigger" href="/about-us">About Us</a>
	    </li>
          
          <c:choose>
			               <c:when test="${pageContext.request.userPrincipal.name != null}">
							      <li class="nav-item">
                                   <a class="nav-link js-scroll-trigger" href="#"><sec:authentication property="name"/></a>
                                 </li>
                                 <!-- https://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html -->
                                <!-- If user has role User display list for customer cart
                                   <sec:authorize access="hasRole('USER')" >
                                    <li class="nav-item">
                                    <a class="nav-link js-scroll-trigger" href="/customer/cart"><i class="fa fa-shopping-cart" aria-hidden="true"></i>  Cart</a> 
                                 </li>	
                                  </sec:authorize>  
                                  --> 
                                  
                                  
                                         <li class="nav-item">
                                   <a class="nav-link js-scroll-trigger" href="/admin">Admin Dashboard</a> 
                                 </li>   
                                 
                                  <li class="nav-item">
                                   <a class="nav-link js-scroll-trigger" href="/logout">LOG OUT</a> 
                                 </li>	   
                                 
                                 <!-- If user has role Admin show link for admin page-->
                                 <sec:authorize access="hasRole('ADMIN')" >
		                                 <li class="nav-item" style="float:right;">
		                                         <a class="nav-link js-scroll-trigger" href="/admin" class="btn bnt-primary">ADMIN</a>
		                                   </li> 
                                  </sec:authorize>    
			                </c:when>    
			        <c:otherwise>
			         <li class="nav-item">            
				                       <a class="nav-link js-scroll-trigger" href="/login">LOG IN</a>
				                   </li>
				                  <li class="nav-item">
				                     <a class="nav-link js-scroll-trigger" href="/register">SIGN UP</a>
				                  </li>	
				                  
			      </c:otherwise>
      </c:choose>
           
            
          </ul>
        </div>
      </div>
    </nav>



  
    <!-- Header -->
    <header class="masthead">
      <div class="container">
        <div class="intro-text">
          <div class="intro-lead-in">A Better Way To Get The Food you Love</div>
          <div class="intro-heading">Delivered</div>
          
           <div class="container">
          
          <div class="row">
          
          <div class="col-md-1">
          
          </div>
           <div class="col-md-6" >
            <input type="text" id="address" name="address" class="form-control input-lg" placeholder="Get Started by Entering Your Address"  style="display: block;  margin:0; width: 100%;" />
            
          
           </div>
           <div class="col-md-4">
           
            <form:form commandName="coords"> 
           <form:input path="googleLat" type="hidden" name="latitude" id="googleLat" value="${googleLat}" />
            <form:input path="googleLng" type="hidden" name="longitude" id="googleLng" value="${googleLng}" />
            <form:input path="deliveryAddress" type="hidden" name="deliveryAddress" id="deliveryAddress" value="${deliveryAddress}" />
             <form:input path="postalCode" type="hidden" name="postalCode" id="postal_code" value="${postalCode}" />
             
              <form:input path="streetNumber" type="hidden" name="street_number" id="street_number" />
            <form:input path="streetName" type="hidden" name="route" id="route" />
            <form:input path="city" type="hidden" name="locality" id="locality" />
            <form:input path="state" type="hidden" name="administrative_area_level_1" id="administrative_area_level_1"  />
             <form:input path="country" type="hidden" name="country" id="country" />
             
            
            
            <input type="submit" id="findRestaurant" value="Find Restaurants" 
            class="btn btn-lg btn-primary img-thumbnail" style="display: block;     margin:0;  width: 100%;  "/>
        
           </form:form>
          
           </div>
            
          <div class="col-md-1">
          
          </div>
          </div>
        
          
          
          </div>
        
         </div>
         
    
         </div>
    </header>
<!-- Footer -->
    <footer>
      <div class="container">
        <div class="row">
          <div class="col-md-4">
            <span class="copyright">Copyright &copy; Luxmart Web Design 2018</span>
          </div>
          <div class="col-md-4">
     
          </div>
          <div class="col-md-4">
            <ul class="list-inline quicklinks">
              <li class="list-inline-item">
                <a href="#">Privacy Policy</a>
              </li>
              <li class="list-inline-item">
                <a href="#">Terms of Use</a>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </footer>

   
    
    

    <!-- Bootstrap core JavaScript -->   
    <script src="../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../resources/vendor/popper/popper.min.js"></script>
    <script src="../resources/vendor/bootstrap/js/bootstrap.min.js"></script>


    <!-- Plugin JavaScript -->
    <script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    

    <!-- Custom scripts for this template -->
    <script src="../resources/js/agency.min.js"></script>
    
    
   <!--    <script src="../resources/js/bootstrap.js"></script>   -->
    
     <script src="../resources/js/getDeliveryCoords.js"></script>

  </body>

</html>

   

   

  
