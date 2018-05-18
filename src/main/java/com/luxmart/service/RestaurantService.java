package com.luxmart.service;

import java.util.List;

import com.luxmart.model.Restaurant;
import com.luxmart.model.RestaurantHeaderType;
import com.luxmart.security.model.Authorities;
import com.luxmart.store.model.RestaurantDb;
import com.luxmart.store.model.util.CreateRestaurant;

public interface RestaurantService {

	Restaurant getRestaurant(String restaurantName);

	List<Restaurant> findAllRestaurants(String lat, String lng, int km);

	List<RestaurantHeaderType> getHeaders();

	List<Restaurant> getFoodTypeRest(String lat, String lng, String foodType);

	List<Restaurant> findRestaurant(String lat, String lng, int km, String search);

	void addCredentials(Authorities Authority);

	List<RestaurantDb> findAllRestaurant();

	Restaurant getRestaurantByName(String productType);

	// methods for Restaurants admin

	RestaurantDb getRestaurantById(long id);

	void deleteRestaurant(long id);

	void addRestaurant(CreateRestaurant restaurant);

	void updateRestaurant(CreateRestaurant restaurant);

	RestaurantDb findRestaurantByName(String resturantName);

	void addHeaderType(RestaurantHeaderType foodType);

	void updateHeaderType(RestaurantHeaderType foodType);

	RestaurantHeaderType getfoodTypeById(int id);

	void deleteHeaderFoodType(int id);
}
