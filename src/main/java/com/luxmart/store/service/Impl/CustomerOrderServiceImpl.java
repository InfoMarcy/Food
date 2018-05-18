package com.luxmart.store.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxmart.store.model.Cart;
import com.luxmart.store.model.CartItem;
import com.luxmart.store.model.CustomerOrder;
import com.luxmart.store.repository.CustomerOrderRepository;
import com.luxmart.store.service.CartService;
import com.luxmart.store.service.CustomerOrderService;


@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {
	
	@Autowired
	CartService cartService;
	
	
	@Autowired
	CustomerOrderRepository CustomerOrderRepository;
	
	@Override
	public void addCustomerOrder(CustomerOrder customerOrder) {
		
		CustomerOrderRepository.addCustomerOrder(customerOrder);

	}

	
	// calculate the total price of the items
	@Override
	public double getCustomerOrderGrandTotal(int cartId) {
	
		double grandTotal = 0;
		
		Cart cart = cartService.getCartById(cartId);
		List<CartItem> cartItems = cart.getCartItems();
		
		// iterate through the items on the cart
		for(CartItem item: cartItems){
			// get the price of the items in the cart
			grandTotal += item.getTotalPrice();
			
		}
		
		return grandTotal;
	}

}
