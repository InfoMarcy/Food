package com.luxmart.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.luxmart.store.model.Customer;
import com.luxmart.store.service.CustomerService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	CustomerService customerService;
	
	
	// get the customer info for the profile page
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	public String profile(Principal principal, Model model){
		Customer  customer = customerService.getCustomerByUsername(principal.getName());
			
		model.addAttribute("customer", customer);
		return "Login/profile";
	}
	
	
	// save the customer info
	@RequestMapping(value="/profile", method = RequestMethod.POST)
	public String profilePost(@ModelAttribute("customer") Customer editCustomer, Model model){
		Customer customer = customerService.getCustomerByUsername(editCustomer.getEmail());
		customer.setFirstName(editCustomer.getFirstName());
		customer.setLastName(editCustomer.getLastName());
		customer.setEmail(editCustomer.getEmail());		
		customer.setPhone(editCustomer.getPhone());
		
		customerService.saveCustomer(customer);
		
		model.addAttribute("customer", customer);
		return "Login/profile";
	}
	
	
	@RequestMapping(value="/addresses", method = RequestMethod.GET)
	public String Customeraddresses(Principal principal, Model model){
		Customer  customer = customerService.getCustomerByUsername(principal.getName());
			
		model.addAttribute("customer", customer);
		return "Login/profileAddresses";
	}
	
	
	// save the customer address info
	@RequestMapping(value="/addresses", method = RequestMethod.POST)
	public String CustomeraddressesPost(@ModelAttribute("customer") Customer editCustomer, Model model){
		
		
		model.addAttribute("customer", editCustomer);
		return "Login/profileAddresses";
	}

}
