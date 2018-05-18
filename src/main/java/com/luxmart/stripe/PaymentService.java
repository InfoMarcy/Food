package com.luxmart.stripe;

import com.luxmart.store.model.CustomerOrder;
import com.luxmart.store.model.util.CustomerInfo;
import com.stripe.model.Charge;

public interface PaymentService {
	
	public String getKey();
	
	public String createCustomer(CustomerInfo customer);
	
	 public Charge chargeCreditCard(CustomerOrder order);

	  void addpayment(String token, String stripeCustomerId);

}
