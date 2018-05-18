
				    function deleteHeaderType(id) {
				    	
				    	 if (confirm("Are you sure you want to delete this this category?") == true) {
						
						// XMLHttpRequest will stablish a connection with the url we specify and then it let us send or receive data
							var ourRequest = new XMLHttpRequest();

							// get the data from url
							ourRequest.open('DELETE', '/admin/deleteFoodType/'+id);
							

							// define what we want to do once the data is loaded
							ourRequest.onload = function (){
								
								
								if(ourRequest.status >= 200 && ourRequest.status < 400){									
								
								// redirect
									window.location.href = '/admin/foodTypeImage';
							
								
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


