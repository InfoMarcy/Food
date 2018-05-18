package com.luxmart.store.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxmart.store.model.Cart;
import com.luxmart.store.model.CartItem;
import com.luxmart.store.repository.CartItemRepository;
import com.luxmart.store.service.CartItemService;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public void addCartItem(CartItem cartItem) {
		cartItemRepository.addCartItem(cartItem);
		
	}

	@Override
	public void removeCartItem(CartItem cartItem) {
		cartItemRepository.removeCartItem(cartItem);
		
	}

	@Override
	public void removelAllCartItems(Cart cart) {
		cartItemRepository.removelAllCartItems(cart);
		
	}


	@Override
	public void removeProductFromCartItem(int productId) {
		cartItemRepository.removeProductFromCartItem(productId);
		
	}

}
