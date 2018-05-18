package com.luxmart.store.service;

import java.io.IOException;

import com.luxmart.store.model.Cart;

public interface CartService {
	
	Cart getCartById(int id);
	
	void Update(Cart cart);
	
	Cart validate(int cartId) throws IOException;

}
