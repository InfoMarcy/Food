package com.luxmart.store.repository.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.luxmart.store.model.Cart;
import com.luxmart.store.model.CartItem;
import com.luxmart.store.repository.CartItemRepository;




@Repository("cartItemRepository")
@Transactional
public class CartItemRepositoryImpl implements CartItemRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	// add new Cart item
	@Override
	public void addCartItem(CartItem cartItem) {	
	Session session = sessionFactory.getCurrentSession();

	// save or update the cart item
	session.saveOrUpdate(cartItem);
	session.flush();

	}
	
	// remove an item form the cart
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void removeCartItem(CartItem cartItem) {
//		//delete using jdbcTemplate
	jdbcTemplate.update("Delete From cart_item  Where cart_item_id = ?", cartItem.getCartItemId());

	}
	
	
	@Override
	public void removeProductFromCartItem(int productId) {
//		//delete using jdbcTemplate
	jdbcTemplate.update("Delete From cart_item  Where product_id = ?", productId);

	}

	// remove all items from the cart
	@Override
	public void removelAllCartItems(Cart cart) {
		List<CartItem> cartIems = cart.getCartItems();
		
		//iterate though all the items in the cart and call the remove item function to remove the item
		for(CartItem item : cartIems){
			removeCartItem(item);			
		}
		
	}

}
