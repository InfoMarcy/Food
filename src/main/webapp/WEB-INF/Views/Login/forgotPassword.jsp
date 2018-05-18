<%@ include file="../include/header.jsp" %>

<header class="container-fluid">
<%@ include file="../include/accountNavbar.jsp" %>
</header>


  <!--  used to use function such as forEach in table -->
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="container starter-template" >


	<div class="row justify-content-center">
	
	  <c:url value="/forgot" var="passwordUrl"/>
	  <form action='${passwordUrl}' method="post" class="form-horizontal" name="forgotForm">
	  
	  
	  <h3>---------------- Forgot Password ----------------</h3>
	  
	  <p class="lead">Enter your e-mail address and we'll send you a link to reset your password.</p>
	  <!-- check if there are any messages -->
	  <c:if test="${not empty msg}">
	   <div class="alert alert-success" role="alert">
	               <div>${msg}</div>	
	               </div>	  
	  </c:if>
	  
	    <!-- check if there are any errors -->
	   <c:if test="${not empty error}">
	  
	      <div  class="alert alert-danger" role="alert">
 
	         <div class="error">${error}</div>
	         
	         </div>	 	  
	  </c:if>
	 
	  <div class="form-group">
			<label for="email" class="control-label">Email</label> 
			
			<input type="email" id="email" name="email" class="form-control" required="required"/>
			
		</div>
	  
	  
	  
	 
		
		
		
	  
	   <div class="form-group">		
		 <!-- this use for safety to prevent from CRS attacks -->
	   <input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" />
	   
	  <button type="submit" class="form-control btn btn-primary col-md-6" name="btnSubmit">Submit</button>
		    	</div>
	
	 
	  </form>  
	  
	  
	  
	
	  </div>


	 
</section>


  <%@ include file="../include/footer.jsp" %>
  