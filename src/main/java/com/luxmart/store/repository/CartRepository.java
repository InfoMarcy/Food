package com.luxmart.store.repository;

import java.io.IOException;

import com.luxmart.store.model.Cart;

public interface CartRepository {

	Cart getCartById(int id);
	
	void Update(Cart cart);
	
	Cart validate(int cartId) throws IOException;

}
