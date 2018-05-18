      <!--  add this tab to specify the library -->
    <%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>
    
      <!--  used to use function such as forEach in table -->
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
    <!-- Spring security tag -->
      <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   
   <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-inverse fixed-top" id="RestNav">
      
    
        <a class="navbar-brand js-scroll-trigger" href="/admin">  &nbsp; Dashboard</a>
        
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse " id="navbarResponsive">
        
       <ul class="nav navbar-nav ml-auto">   
       
        <li class="nav-item">            
			 <a class="nav-link js-scroll-trigger" href="/">Home</a>
	     </li>   
          
        
          
         <li class="nav-item">            
			 <a class="nav-link js-scroll-trigger" href="/admin/restaurant">Restaurants</a>
	     </li>
          
          <sec:authorize access="hasRole('ADMIN')" >
           <li class="nav-item">            
			 <a class="nav-link js-scroll-trigger" href="/admin/customer">Customers</a>
	     </li>
          </sec:authorize>
            
          </ul>
 
        </div>
        
        
    </nav>
    
 
