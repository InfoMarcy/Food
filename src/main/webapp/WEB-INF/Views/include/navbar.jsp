      <!--  add this tab to specify the library -->
    <%@taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>
    
        <!-- Spring security tag -->
      <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
      
     
     
   <!-- Navigation -->
    <nav class="navbar navbar-expand-lg navbar-inverse fixed-top" id="RestNav">
      <div class="container">
    
        <a class="navbar-brand js-scroll-trigger" href="#page-top">Luxmart Food Delivery</a>
        
        <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          Menu
          <i class="fa fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
        
      
        
        
          <form action="/restaurants" method ="post" class="navbar-form navbar-right"> 
            <div class="form-group">
           <input   class="form-control"  type="text" name="search" id="searchRest_tb" placeholder="Search...."/>
           
           </div>
           </form>
        
          <ul class="nav navbar-nav navbar-left">
          
          
        
          
                  
           
            
          </ul>
        </div>
        
      </div>      
    </nav>
    <p class="text-muted text-left" style="margin: 0px; padding: 0px;"><strong>DELIVERING TO: </strong>${deliveryAddress.toUpperCase()}.</p>
  