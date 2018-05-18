package com.luxmart.store.service.Impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxmart.store.model.Cart;
import com.luxmart.store.repository.CartRepository;
import com.luxmart.store.service.CartService;

@Service("cartService")
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepository  cartRepository;

	@Override
	public Cart getCartById(int id) {
		
		return (Cart) cartRepository.getCartById(id);
	}

	@Override
	public void Update(Cart cart) {
		
		cartRepository.Update(cart);
		
	}

	@Override
	public Cart validate(int cartId) throws IOException {
		// TODO Auto-generated method stub
		return cartRepository.validate(cartId);
	}

}
