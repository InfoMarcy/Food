package com.luxmart.store.service.Impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxmart.security.model.User;
import com.luxmart.store.model.Customer;
import com.luxmart.store.model.util.CheckoutInfo;
import com.luxmart.store.repository.CustomerRepository;
import com.luxmart.store.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public void addCustomer(Customer customer, HttpServletRequest request) {
		customerRepository.addCustomer(customer, request);
	}

	@Override
	public Customer getCustomerById(int id) {
		// TODO Auto-generated method stub
		return customerRepository.getCustomerById(id);
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.getAllCustomers();
	}

	@Override
	public User findByConfirmationToken(String token) {
		// TODO Auto-generated method stub
		return customerRepository.findByConfirmationToken(token);
	}

	@Override
	public void enableUser(User user) {
		customerRepository.enableUser(user);
	}

	@Override
	public Customer getCustomerByUsername(String username) {
		return	customerRepository.getCustomerByUsername(username);
		
	}

	@Override
	public void saveCustomer(Customer customer) {
		customerRepository.saveCustomer(customer);
		
	}

	@Override
	public void addShipping(CheckoutInfo checkoutInfo) {
		customerRepository.addShipping(checkoutInfo);
		
	}
}
