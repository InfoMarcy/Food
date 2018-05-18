<%@ include file="../include/header.jsp" %>

<header class="container-fluid">
<%@ include file="../include/accountNavbar.jsp" %>
</header>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<article class="container starter-template" >

<div class="row main">
 <div class="col-md-3 text-center">
 
             <figure class="figure">
					<img class="figure-img img-fluid rounded" src="http://oldhamgoodwin.com/wp-content/uploads/2011/09/Marci-Garcia.jpg" alt="${customer.firstName} ${customer.lastName}" width="120px">
						<figcaption class="figure-caption"><strong>${customer.firstName} ${customer.lastName}</strong></figcaption>
						
						
				
						
				
				</figure>
				
				<ul class="text-left">				
				<li><a href="/user/profile">Profile</a></li>
				<li><a href="/user/photo">Photo</a></li>
				<li><a href="/user/addresses">Addresses</a></li>				
				<li><a href="/user/payments">Payment Options</a></li>
				<li><a href="/user/orders">Your Orders</a></li>
				<li><a href="/user/notifications">Notifications</a></li>
				<li><a href="/user/deleteAccount">Danger Zone</a></li>
				</ul>
 
 
 </div>


         <div class="col-md-8">
            <h2 class="title">Profile</h2>
            <p class="lead">Add information about yourself to share on your profile.</p>
            <hr/>
            
           
        
        <form:form  action="${pageContext.request.contextPath}/user/profile" method="post" class="m-t" role="form" commandName="customer">
      
       
        <form:input type="hidden" name="id" path ="cutomerId"/>
        
        
         <div class="form-group">
                    <label for="firstName">First Name</label> <form:errors path="firstName" cssStyle="color: #ff0000" />
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                                              
                                   <form:input type="text"	path ="firstName" id="firstname" name="firstName" 
                                   placeholder="Enter your first name" class="form-control"  required="required" />
                        </div>
                    </div>
                </div>
     
      <div class="form-group">
                    <label for="lastName" class="cols-sm-2 control-label">Last Name</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
                                  
                             <form:input type="text"	path ="lastName" id="lastName" name="lastName" placeholder="Last Name" class="form-control" required="required"/>
					
                        </div>
                    </div>
                </div>
                   
 				
            <div class="form-group">
                    <label for="phone" class="cols-sm-2 control-label">Phone</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-phone fa" aria-hidden="true"></i></span>
                            
                                    <form:input type="text"	path ="phone" id="phone" name="customerPhone" placeholder="xxx-xxx-xxxx" class="form-control" required="required" />
					 
                        </div>
                    </div>
                </div>
 			   
		   
		    <div class="form-group">
                    <label for="customerEmail" class="cols-sm-2 control-label">Your Email</label> <span style="color:#ff0000">${emailsMsg}</span><form:errors path="customerEmail" cssStyle="color: #ff0000" />  
                    <div class="cols-sm-10">
                        <div class="input-group">
                                <span class="input-group-addon"><i class="fa fa-envelope fa"
                                                                   aria-hidden="true"></i></span>
                                   
                                     <form:input type="text"	path ="email" id="customerEmail" name="customerEmail" placeholder="Enter your Email" class="form-control" data-error="This email address is invalid"  />
					
                        </div>
                    </div>
                </div>
		   
		  
		
	 	<div class="form-group ">
                    <button type="submit" class="btn btn-primary btn-block" name="btnProfile">Change Settings</button>
                </div>
		   
      </form:form>
     </div>
     </div>
   
</article>
  <%@ include file="../include/footer.jsp" %>
  
  
  