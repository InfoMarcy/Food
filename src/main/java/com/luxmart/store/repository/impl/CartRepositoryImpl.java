package com.luxmart.store.repository.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luxmart.store.model.Cart;
import com.luxmart.store.repository.CartRepository;
import com.luxmart.store.service.CustomerOrderService;

@Repository("cartRepository")
@Transactional
public class CartRepositoryImpl implements CartRepository {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	CustomerOrderService customerOrderService;
	
	// get cart by Id Function
	@Override
	public Cart getCartById(int cartId) {
		Session session= sessionFactory.getCurrentSession();
		
		// get the cart a by Id
		 return (Cart) session.get(Cart.class, cartId);
	}

	
	// get the grandTotal of the cart
	@Override
	public void Update(Cart cart) {
		
		
		int cartId = cart.getCartId();
		double grandTotal = customerOrderService.getCustomerOrderGrandTotal(cartId);
		cart.setGrandTotal(grandTotal);
		
		
		//System.out.println("Updating  cart" + cart.getGrandTotal());
		Session session= sessionFactory.getCurrentSession();
		
		// save or update the cart item
		session.saveOrUpdate(cart);
		session.flush();

		
	}

	@Override
	public Cart validate(int cartId) throws IOException {
		
		
	
		// get the cart
		Cart cart = getCartById(cartId);
		
		
		if(cart == null || cart.getCartItems().size() ==0){
			
			throw new IOException();
		}
		
		
		// update the grand total  price on the cart
		Update(cart);
		
		// return the cart
		return cart;
	}

}
