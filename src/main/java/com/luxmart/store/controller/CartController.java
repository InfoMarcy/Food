//package com.luxmart.store.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import com.luxmart.store.model.Customer;
//import com.luxmart.store.service.CustomerService;
//
//
//@Controller
//@RequestMapping("/customer/cart")
//public class CartController {
//	
//	@Autowired
//	CustomerService customerService;
//	
//	@RequestMapping
//	public String getCart(@AuthenticationPrincipal User activeUser){
//		
//		
//		// retrieve customer info form the session and customer and cart is binding as one to one
//		// when a customer is create it is also created a cart for that customer
//		// get the customer info of the customer that is currently logged in
//		Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
//		
//		// get the cart info of the customer by retrieving the cart Id
//		int cartId = customer.getCart().getCartId();
//		
//		//redirect to new path
//		return "redirect:/customer/cart/"+cartId;
//	}
//	
//	@RequestMapping("/{cartId}")
//	public String getCartRedirect(@PathVariable(value="cartId") int cartId, Model model){
//		
//		// add the Cart Id to the model to be use later
//		model.addAttribute("cartId", cartId);
//		
//		return "Cart/cart";
//	}
//
//}
