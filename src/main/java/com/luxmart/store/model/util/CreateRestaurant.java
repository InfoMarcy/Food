package com.luxmart.store.model.util;

import com.luxmart.store.model.Location;
import com.luxmart.store.model.RestaurantDb;

public class CreateRestaurant {
	
	private RestaurantDb restaurant;
	private Location location;
	
	
	public RestaurantDb getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantDb restaurant) {
		this.restaurant = restaurant;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

}
