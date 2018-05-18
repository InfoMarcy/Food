package com.luxmart.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.luxmart.store.model.CartItem;

public class CartItemRowMapper implements RowMapper<CartItem>{

    @Override
	public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
    	CartItem cartItem = new CartItem();
		
    	cartItem.setCartItemId(rs.getInt("cart_item_id"));
    	
		return cartItem;
		

	}
    
}
