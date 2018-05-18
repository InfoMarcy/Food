package com.luxmart.repository;

import java.util.List;

import com.luxmart.model.Restaurant;
import com.luxmart.model.RestaurantHeaderType;
import com.luxmart.security.model.Authorities;
import com.luxmart.store.model.RestaurantDb;
import com.luxmart.store.model.util.CreateRestaurant;

public interface RestaurantRepository {

	Restaurant getRestaurant(String restaurantName);

	List<Restaurant> getRestaurants(String lat, String lng, int km);

	List<RestaurantHeaderType> getHeaders();

	List<Restaurant> getFoodTypeRest(String lat, String lng, String foodType);

	List<Restaurant> findRestaurant(String lat, String lng, int km, String search);

	
	void addCredentials(Authorities Authority);

	List<RestaurantDb> findAllRestaurant();

	Restaurant getRestaurantByName(String name);

	void addRestaurant(CreateRestaurant restaurant);

	RestaurantDb getRestaurantById(long id);
	
	RestaurantDb findRestaurantByName(String name);

	void updateRestaurant(CreateRestaurant restaurant);

	void deleteRestaurant(long id);

	void addHeaderType(RestaurantHeaderType foodType);

	void updateHeaderType(RestaurantHeaderType foodType);

	RestaurantHeaderType getfoodTypeById(int id);

	void deleteHeaderFoodType(int id);


	

}
