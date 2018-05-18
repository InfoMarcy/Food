/**
 * 
 */

     
      var totalDelivery;

    paypal.Button.render({
      env: 'production', // Or 'sandbox', 'production'

      commit: true, // Show a 'Pay Now' button

      client: {
          sandbox:    'AUx8yRfZwBx3iGwA35G5J8gQCaFwNk65wN-xxJm8jsVE-mRp0Kp4IVjRJHFWgGptWXlopc_JdHNZXEZ8',
          production: 'ATmdcqmvvsmHF7apRs0JVQlsVYnX3hQDMN4YJYwdlJG43xGz3roImVADltTDi4cKqeiTbVyRiXQGg2uf'
      },
      
      // Specify the style of the button

      style: {
          label: 'buynow',
          fundingicons: true, // optional
          branding: true, // optional
          size:  'large', // small | medium | large | responsive
          shape: 'rect',   // pill | rect
          color: 'blue'   // gold | blue | silver | black
      },

      
      // payment() is called when the button is clicked
      payment: function(data, actions) {
    	 var total = document.getElementById("getTotalOrderPrice").textContent;
    	 var delivery =document.getElementById("paypalDelivery").textContent;
    	  totalDelivery = total + "-" + delivery;
    	  
          // Make a call to the REST api to create the payment
          return actions.payment.create({
              payment: {
                  transactions: [
                      {
                          amount: { total: total, currency: 'MXN' }
                      }
                  ]
              }
          });
      },


      onAuthorize: function(data, actions) {
        /* 
         * Execute the payment here 
         
         */
    	  
    	  // Make a call to the REST api to execute the payment
          return actions.payment.execute().then(function() {
              //window.alert('Payment Complete!');
        	  
        	  window.location = "/paypalPayment/"+totalDelivery;
          });
              // The payment is complete!
              // You can now show a confirmation message to the customer
         
      },

      onCancel: function(data, actions) {
        /* 
         * Buyer cancelled the payment 
         */
      },

      onError: function(err) {
        /* 
         * An error occurred during the transaction 
         */
      }
    }, '#paypal-button');
