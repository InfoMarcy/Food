<%@ include file="../include/adminHeader.jsp" %>
<%@ include file="../include/headerLinks2.jsp" %>
     

<section class="container starter-template" >
	
	  <div class="page-header">
	  
	  <section class="container">

	 	  <div id="headerType">
	<%@ include file="../include/headerTypeAdmin.jsp"%>

    </div>

</section>
	  
	  
	  
	  <h3>Fill the below information to add a new Type</h3>
	  </div>
	  
	  
	 <form:form action="${pageContext.request.contextPath}/admin/foodTypeImage" method="post" commandName="foodType" enctype="multipart/form-data">
			
			<div class="form-group">
			 <label for="foodType">Type</label>  

			 <form:input path="foodType" id="foodType" name ="foodType" class="form-control"/>			 
			 </div>
			 
			 
			   <div class="form-group">
			 <label for="image" class="control-label">Upload Picture</label><br />
			 <form:input path="image" id="image" class="form-group" type="file" />
			 </div>
			 
			  <div class="form-group"> 
			 <input type="submit" class="btn btn-primary" value="Add" />
			 <a href='<c:url value="/admin"/>' class="btn btn-default" >Cancel</a>
			 </div>
	 </form:form>
	 
	 

	  
</section>




</body>
<%@ include file="../include/footerLinks.jsp" %>
 <%@ include file="../include/footerContent.jsp" %>
</html>

 