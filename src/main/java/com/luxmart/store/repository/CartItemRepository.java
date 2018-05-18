package com.luxmart.store.repository;

import com.luxmart.store.model.Cart;
import com.luxmart.store.model.CartItem;

public interface CartItemRepository {
	
   void addCartItem(CartItem  cartItem);
	
	void removeCartItem(CartItem  cartItem);
	
	void removelAllCartItems(Cart cart);

//	CartItem getCartItemByProductId(int productId);

	void removeProductFromCartItem(int productId);

}
