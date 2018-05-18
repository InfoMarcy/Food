
<%@ include file="../include/header.jsp" %>
<header class="container-fluid">
<%@ include file="../include/storeNavbar.jsp" %>
</header>

<link href="../../resources/jquery/jquery-ui.css" rel="stylesheet" />

<!-- Bean for time -->
<jsp:useBean id="now" class="java.util.Date" />
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<section class="starter-template">


          <div class="alert alert-info text-center">
	         <p class="lead">Thank you for buying our Products!</p>

	       </div>	 


<section class="container">

            <div class="row" >

                    <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3" id="receiptBox">

           <div class="row" >
                <div class="col-xs-6 col-sm-6 col-md-6">
                    <address>
                        <strong>Luxmart Food Delivery</strong>
                        <br>
                        <i class="fa fa-home" aria-hidden="true"></i> 1706 Victoria Dr
                        <br>
                        Vancouver BC, V5N 4J8.
                        <br>
                        <abbr title="Phone"><i class="fa fa-phone" aria-hidden="true"></i></abbr> (778) 952-6067
                    </address>
                </div>
                
                
                    <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                    <p>
                        <strong>Date: </strong><fmt:formatDate type="date" value="${now}"/>
                    </p>
                    <p>
                        <strong>Receipt #:  </strong> ${orderReport.id}
                    </p>
                </div>
                
                </div>
               

                        <div class="text-center">
                            <h1>Receipt</h1>
                        </div>
                       
                      
                    
                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <address>
                                    <strong>Shipping Address</strong><br>                                    
                                        ${orderReport.deliveryAddress}
                                  </address>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                                <p></p>
                            </div>
                        </div>

                
                        
                        <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th>Product</th>
                                    <th>Qty.</th>
                                    <th class="text-center">Price</th>
                                    <th class="text-center">Total</th>
                                </tr>
                                </thead>
                                
                                   <c:forEach var="product" items="${orderReport.orderProducts}" >
                                    <tr>
                                        <td class="col-md-9"><em>${product.name}</em></td>
                                        <td class="col-md-1" style="text-align: center">${product.quantity}</td>
                                        <td class="col-md-1" style="text-align: center">${product.price}</td>
                                        <td class="col-md-1" style="text-align: center">${product.price * product.quantity}</td>
                                    </tr>
                                </c:forEach>
                                
                                
                                <tbody>
                               

                             <tr>
                            <td>   </td>
                            <td>   </td>
                            <td class="text-right">
                            <p>
                                <strong>Subtotal: </strong>
                            </p>
                            <p>
                                <strong>Fees + Tips: </strong>
                            </p></td>
                            <td class="text-center">
                            <p>
                                <strong>$</strong><strong>${orderReport.cartSubtotal}</strong>
                            </p>
                            <p>
                           <strong>$</strong><strong>${orderReport.totalPrice - orderReport.cartSubtotal}</strong>
                           
                             </p></td>
                        </tr>
                                
                                
                                 <tr>
                            <td>   </td>
                            <td>   </td>
                            <td class="text-right"><h4><strong>Total: </strong></h4></td>
                            <td class="text-center text-danger"><h4>$<strong>${orderReport.totalPrice}</strong></h4></td>
                        </tr>
                                </tbody>
                            </table>
                            
                       
                        </div>
                        </div>
                      

</section>
</section>

<%@ include file="../include/footer.jsp" %>