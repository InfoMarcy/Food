package com.luxmart.stripe;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.luxmart.store.model.CustomerOrder;
import com.luxmart.store.model.util.CustomerInfo;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.RateLimitException;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;


@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {
	
	
	// stripe secret key
	private static final String TEST_STRIPE_SECRET_KEY = "sk_test_T3b8cyknjJPEbQMrvRUTPnWa";

	
	// set the stripe secret key
	  public PaymentServiceImpl() {
			Stripe.apiKey = TEST_STRIPE_SECRET_KEY;
	  }
	
	  
	
	  
	  // create a customer in stripe API
	  @Override
		public String createCustomer(CustomerInfo customer) {
		 // Attaching the Source to a Customer is required for you to reuse it for future payments
		  
		  Map<String, Object> customerParams = new HashMap<String, Object>();
		    customerParams.put("description", 
		    customer.getFirstName() + " " + customer.getLastName());
			customerParams.put("email", customer.getEmail());			
			// initialize the id of the stripe customer
			String id = null;
				
			try { 
		      // Create customer
			  Customer stripeCustomer = Customer.create(customerParams);
			  id = stripeCustomer.getId();
			//  System.out.println(stripeCustomer);
			} catch (CardException e) {
			  // Doesn't make much sense here
			} catch (RateLimitException e) {
		  	  // Too many requests made to the API too quickly
			} catch (InvalidRequestException e) {
			  // Invalid parameters were supplied to Stripe's API
			} catch (AuthenticationException e) {
			  // Authentication with Stripe's API failed (wrong API key?)
			} catch (APIConnectionException e) {
			  // Network communication with Stripe failed
			} catch (StripeException e) {
			  // Display a very generic error to the user, and maybe send
			  // yourself an email
			} catch (Exception e) {
			  // Something else happened unrelated to Stripe
			}
				
			
			// return the id of the newly create customer
			return id;	
		}

	  String stripeDefaultCard;
       @Override
	  public void addpayment(String token, String stripeCustomerId){
		// update Customer stripe # Retrieve the customer we're adding this token to
					try {
						Customer stripeCustomer  = Customer.retrieve(stripeCustomerId);
						
						
						if(token !=null && !token.isEmpty()){
							
							Map<String, Object> updateParams = new HashMap<String, Object>();
							updateParams.put("card", token);
						    stripeCustomer.update(updateParams);
						
						}
						
						
					} catch (AuthenticationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvalidRequestException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (APIConnectionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (CardException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (APIException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
		  
	  }
	  
	  // charge
		@Override
		public Charge chargeCreditCard(CustomerOrder order)   {	
			
					 
			Map<String, Object> chargeParams = new HashMap<String, Object>();
		    // Stripe requires the charge amount to be in cents		
			chargeParams.put("amount", order.getChargeAmount());
			chargeParams.put("currency", "CAD");
			chargeParams.put("description", "Food Order");		
			chargeParams.put("customer", order.getCustomer().getStripeCustomerId());			
			
			try {
			  // Submit charge to credit card 
			  Charge charge = Charge.create(chargeParams);
			  
			  if(charge  != null){
				  System.out.println(charge);
				  return charge;				  
			  }
		     
		    } catch (CardException e) {
			  // Transaction was declined
			  System.out.println("Status is: " + e.getCode());
			  System.out.println("Message is: " + e.getMessage());
			} catch (RateLimitException e) {
			  // Too many requests made to the API too quickly
			} catch (InvalidRequestException e) {
			  // Invalid parameters were supplied to Stripe's API
		    } catch (AuthenticationException e) {
			  // Authentication with Stripe's API failed (wrong API key?)
			} catch (APIConnectionException e) {
			  // Network communication with Stripe failed
			} catch (StripeException e) {
			  // Display a very generic error to the user, and maybe send
			  // yourself an email
			} catch (Exception e) {
			  // Something else happened unrelated to Stripe
			}
			
			
			return null;
				
		}
	
	// get the public API key to send it to the customer to submit the payment
	@Override
	public String getKey() {
		
		return	 "pk_test_otQnjjPy6y3fnMDITtKlPXJn";
		
	}
	
}
