<%@ include file="../include/header.jsp" %>

<header class="container-fluid">
<%@ include file="../include/accountNavbar.jsp" %>
</header>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<article class="container starter-template" >

<div class="row justify-content-center">
        
        <form:form  action="${pageContext.request.contextPath}/register" method="post" class="m-t" role="form"   name="registrationForm" commandName="customer">
      
      
     
                    <h1>Create account</h1>
 
 					<p class="lead">Already have an account?  <a href="/login"> Sign in</a></p>
 			
 			<div class="row">
 			<div class="col-md-6">
 					
		<div class="form-group">	
					     <label for="name">First Name</label>     <form:errors path="firstName" cssStyle="color: #ff0000" />
					  <form:input type="text"	path ="firstName" id="firstname" name="firstName" placeholder="First Name" class="form-control"  />
					     
		   </div>
		   
		   </div>
		   <div class="col-md-6">
		   <div class="form-group">	
					     <label for="name">Last Name</label>   <form:errors path="lastName" cssStyle="color: #ff0000" />  
					  <form:input type="text"	path ="lastName" id="lastName" name="lastName" placeholder="Last Name" class="form-control"  />
					     
		   </div>	
		   </div>	   
		   
		   </div>
		   
		   	<div class="form-group">	
		   	         <label for="customerEmail">Email</label> <span style="color:#ff0000">${emailsMsg}</span><form:errors path="email" cssStyle="color: #ff0000" />  
					  <form:input type="text"	path ="email" id="customerEmail" name="customerEmail" placeholder="Email Address" class="form-control" data-error="This email address is invalid"  />
					      
		   </div>
		   
		    <div class="form-group">	
					     <label for="name">Phone Number</label>     
					  <form:input type="text"	path ="phone" id="customerPhone" name="customerPhone" placeholder="Phone Number" class="form-control"  />
					     
		   </div>
		   
		
	 	<div class="form-group">	
		              <label for="password">Password</label><span style="color:#ff0000"> ${passwordMsg}</span> <form:errors path="password" cssStyle="color: #ff0000" />  
					 <div class="input-group">
					  <form:password path ="password" id="password" name="password" placeholder="Password" class="form-control" />
					
					<span class="input-group-addon"><input id="showPassword" type="checkbox">Show</span>
		 
		     </div>         
					          
		   </div>
		   
		  
     
      <!-- Submit button -->
		  <div class="form-group">
	            
			    <button type="submit" class="btn btn-primary" name="btnRegister">Register</button>          
	          
	           
           
               <a href='<c:url value="/"/>' class="btn btn-default" >Cancel</a>

          </div>
         
      </form:form>
     </div>
</article>
<script src="../resources/js/showPassword.js"></script>
  <%@ include file="../include/footer.jsp" %>
  
  
  