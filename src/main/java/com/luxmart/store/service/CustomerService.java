package com.luxmart.store.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.luxmart.security.model.User;
import com.luxmart.store.model.Customer;
import com.luxmart.store.model.util.CheckoutInfo;

public interface CustomerService {
	
 void addCustomer(Customer customer, HttpServletRequest request);
 
 Customer getCustomerById(int id);
 
 List<Customer> getAllCustomers();

User findByConfirmationToken(String token);

void enableUser(User user);

Customer getCustomerByUsername(String username);

void saveCustomer(Customer customer);

void addShipping(CheckoutInfo checkoutInfo);

//void updateShipping(ShippingCheckout shippingCheckout);


 
}
