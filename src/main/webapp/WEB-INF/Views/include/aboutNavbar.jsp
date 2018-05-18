      <!--  add this tab to specify the library -->
    <%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>
    
      <!--  used to use function such as forEach in table -->
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <!-- Spring security tag -->
      <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
   <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-inverse fixed-top" id="RestNav">
      
    
        <a class="navbar-brand js-scroll-trigger" href="/">  &nbsp; Luxmart Web Design</a>
        
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse " id="navbarResponsive">
        
       <ul class="nav navbar-nav ml-auto">
       
       
          
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
        
        
    </nav>
    
 
