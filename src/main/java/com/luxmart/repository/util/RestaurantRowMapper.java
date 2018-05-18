package com.luxmart.repository.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.luxmart.model.Restaurant;

public class RestaurantRowMapper implements RowMapper<Restaurant>{

    @Override
	public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
    	Restaurant restaurant = new Restaurant();
		
    	restaurant.setId(rs.getLong("id"));
    	restaurant.setName(rs.getString("name"));    	
    	restaurant.setType(rs.getString("type"));
    	restaurant.setStreetName(rs.getString("street_name"));    	
    	restaurant.setImageUrl(rs.getString("image_url"));
      	restaurant.setDistance(Math.round(rs.getDouble("distance") * 100.0)/100.0);
	
    	
    	
		return restaurant;
		

	}
    
}
