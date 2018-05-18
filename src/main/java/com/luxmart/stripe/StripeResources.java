package com.luxmart.stripe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.luxmart.store.model.Customer;
import com.luxmart.store.service.CustomerService;

@Controller
@RequestMapping("/rest/stripe")
public class StripeResources {
	
	@Autowired
	PaymentService paymentService;
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/addPayment", method =RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addPayment(@RequestParam(value = "stripeToken", required=false) String stripeToken, @AuthenticationPrincipal User activeUser){
	
	///	System.out.println("Add payment " + stripeToken);
		
		        // get the customer info of the customer that is currently logged in
				Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
				if(stripeToken != null && !stripeToken.isEmpty()){
					paymentService.addpayment(stripeToken, customer.getStripeCustomerId());		
				}
				
	}
	

}
