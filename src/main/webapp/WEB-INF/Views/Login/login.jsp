<%@ include file="../include/header.jsp" %>

<header class="container-fluid">
<%@ include file="../include/accountNavbar.jsp" %>
</header>


  <!--  used to use function such as forEach in table -->
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="container starter-template" >


	<div class="row justify-content-center">
	
	  <c:url value="/login" var="loginUrl"/>
	  <form action='${loginUrl}' method="post" class="form-horizontal" name="loginForm">
	  
	  
	  <p class="lead">Sign in</p>
	  <!-- check if there are any messages -->
	  <c:if test="${not empty msg}">
	             <div class="alert alert-success" role="alert">
	               <div>${msg}</div>	
	               </div>		  
	  </c:if>
	  
	    <!-- check if there are any errors -->
	   <c:if test="${not empty error}">
	    <div class="alert alert-danger">
	         <div class="error">${error}</div>
	         </div>	 	  
	  </c:if>
	 
	  <div class="form-group">
			<label for="username" class="control-label">Email</label> 
			
			<input type="email" id="email" name="email" class="form-control" required="required"/>
			
		</div>
	  
	  
	  
	  <div class="form-group ">
			<label for="password" class="control-label">Password: </label> 
		
	
			<input type="password" id="password" name="password" class="form-control" required="required"/>
		
		
		            
		</div>
		
		
		<div class="form-check">
                    <label class="form-check-label" for="rememberMe">
                        <input class="form-check-input" type="checkbox" name="rememberMe" id="rememberMe"  checked>
                        <span>Remember me</span>
                    </label>
                </div>
		
	  
	   <div class="form-group">
		
		 <!-- this use for safety to prevent from crs attacks -->
	   <input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" />
	   
	  <button type="submit" class="form-control btn btn-primary col-md-6" name="btnLogin">Log in</button>
		    	</div>
	
	  <div class="alert alert-warning">                
                 <span>Did you forget your password?<br /></span>
                <a class="alert-link" href="<c:url value='/forgot' />" >Reset Password</a>
            </div>
           
            <div class="alert alert-warning">
                <span>New to Luxmart Food Delivery?<br /></span>
                <a class="alert-link" href="<c:url value='/register' />" >Create an Account</a>
            </div>
	  </form>  
	  
	  
	  
	
	  </div>


	 
</section>


  <%@ include file="../include/footer.jsp" %>
  