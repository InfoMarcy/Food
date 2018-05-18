package com.luxmart.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luxmart.store.model.Cart;
import com.luxmart.store.model.Customer;
import com.luxmart.store.model.CustomerOrder;
import com.luxmart.store.service.CartItemService;
import com.luxmart.store.service.CartService;
import com.luxmart.store.service.CustomerOrderService;

@Controller
public class OrderController {
	
	@Autowired
	CartService cartService;
	@Autowired
	CustomerOrderService customerOrderService;
	
	
	@Autowired
	CartItemService CartItemService;
	
	
	
	// create an order for the customer
	@RequestMapping("/order/{cartId}")
	public String createOrder(@PathVariable(value="cartId") int cartId){
		// create a new order
		CustomerOrder customerOrder = new CustomerOrder();
		
		// get the cart by id
		Cart cart = cartService.getCartById(cartId);
		
		// set the cart for tha customer
		customerOrder.setCart(cart);
		
		// get the customer
		Customer customer = cart.getCustomer();
		
		// set the custumer for the order
		customerOrder.setCustomer(customer);
		
		// set customer billing and shipping address to the order
	//	customerOrder.setBillingAddress(customer.getBillingAddress());
	//	customerOrder.setShippingAddress(customer.getShippingAddress());
		
		customerOrderService.addCustomerOrder(customerOrder);
		return "redirect:/checkout?cartId="+cartId;
	}
	
	
	

	}

