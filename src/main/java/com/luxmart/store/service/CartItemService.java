package com.luxmart.store.service;

import com.luxmart.store.model.Cart;
import com.luxmart.store.model.CartItem;

public interface CartItemService {
	
	void addCartItem(CartItem  cartItem);
	
	void removeCartItem(CartItem  cartItem);
	
	void removelAllCartItems(Cart cart);

	void removeProductFromCartItem(int productId);

}
