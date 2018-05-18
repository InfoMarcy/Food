<%@ include file="../include/header.jsp" %>

<header class="container-fluid">
<%@ include file="../include/accountNavbar.jsp" %>
</header>


  <!--  used to use function such as forEach in table -->
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<section class="container starter-template" >


	<div class="row justify-content-center">
	
	  <c:url value="/reset" var="passwordUrl"/>
	  <form action='${passwordUrl}' method="post" class="form-horizontal" name="resetPassword">
	  
	  
	  <h3>---------------- Reset Password ----------------</h3>
	  
	 
	  <!-- check if there are any messages -->
	  <c:if test="${not empty msg}">
	   <div class="alert alert-success" role="alert">
	               <div class="msg">${msg}</div>
	               </div>		  
	  </c:if>
	  
	    <!-- check if there are any errors -->
	   <c:if test="${not empty error}">
	    <div class="alert alert-danger">
	         <div class="error" >${error}</div>	 
	         </div>	  
	  </c:if>
	 
	   <div class="form-group ">
			<label for="password" class="control-label">Password: </label> 	
	    <div class="input-group">
	
			<input type="password" id="password" name="password" placeholder="Password" class="form-control" required="required"/>
		         
		         	<span class="input-group-addon"><input id="showPassword" type="checkbox">Show</span>   
		</div>
		    </div> 
		
		
		
	  
	   <div class="form-group">		
		 <!-- this use for safety to prevent from CRS attacks -->
	   <input type="hidden" name="${_csrf.parameterName}"	value="${_csrf.token}" />
	   <input type="hidden" name="token"	value="${token}" />
	  <button type="submit" class="form-control btn btn-primary col-md-6" name="btnSubmit">Save</button>
		    	</div>
	
	 
	  </form>  
	  
	  
	  
	
	  </div>


	 
</section>

<script src="../resources/js/showPassword.js"></script>
  <%@ include file="../include/footer.jsp" %>
  