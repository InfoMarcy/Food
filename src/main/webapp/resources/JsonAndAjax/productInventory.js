/**
 * 
 */


//				  function deleteProduct(id) {
//						  
//					  if (confirm("Are you sure you want to delete this product?") == true) {
//					       
//					//	  window.location.href = '/admin/product/deleteProduct/'+id;
//						  
//						  
//						
//						  
//					  }
//					
//				  }
				  
				  
				    function deleteProduct(id) {
				    	
				    	 if (confirm("Are you sure you want to delete this product?") == true) {
						
						// XMLHttpRequest will stablish a connection with the url we specify and then it let us send or receive data
							var ourRequest = new XMLHttpRequest();

							// get the data from url
							ourRequest.open('GET', '/admin/product/deleteProduct/'+id);
							

							// define what we want to do once the data is loaded
							ourRequest.onload = function (){
								
								
								if(ourRequest.status >= 200 && ourRequest.status < 400){									
								
								// reload
							  window.location.reload(true);
							
								
								} else {
									
									console.log("We connected to the server, but it returned an error");
								}
							
							};
							
						//  handler ajax error
							ourRequest.onerror = function (){
								console.log("connection error");
							}
							
							// send the request
								ourRequest.send();
							
							
				    	 }
					}


