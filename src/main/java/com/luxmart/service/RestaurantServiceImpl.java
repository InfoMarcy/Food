package com.luxmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxmart.model.Restaurant;
import com.luxmart.model.RestaurantHeaderType;
import com.luxmart.repository.RestaurantRepository;
import com.luxmart.security.model.Authorities;
import com.luxmart.store.model.RestaurantDb;
import com.luxmart.store.model.util.CreateRestaurant;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Override
	public List<Restaurant> findAllRestaurants(String lat, String lng, int km) {
		return restaurantRepository.getRestaurants(lat, lng, km);
	}

	@Override
	public Restaurant getRestaurant(String restaurantName) {
		return restaurantRepository.getRestaurant(restaurantName);

	}

	@Override
	public List<RestaurantHeaderType> getHeaders() {

		return restaurantRepository.getHeaders();
	}

	@Override
	public List<Restaurant> getFoodTypeRest(String lat, String lng, String foodType) {

		return restaurantRepository.getFoodTypeRest(lat, lng, foodType);
	}

	@Override
	public List<Restaurant> findRestaurant(String lat, String lng, int km, String search) {

		return restaurantRepository.findRestaurant(lat, lng, km, search);
	}

	@Override
	public void addCredentials(Authorities Authority) {
		restaurantRepository.addCredentials(Authority);
	}

	@Override
	public List<RestaurantDb> findAllRestaurant() {
		return restaurantRepository.findAllRestaurant();
	}

	@Override
	public Restaurant getRestaurantByName(String name) {
		return restaurantRepository.getRestaurantByName(name);
	}

	// Admin Restaurant Methods
	@Override
	public void addRestaurant(CreateRestaurant restaurant) {
		restaurantRepository.addRestaurant(restaurant);

	}

	@Override
	public RestaurantDb getRestaurantById(long id) {

		return restaurantRepository.getRestaurantById(id);
	}

	@Override
	public void updateRestaurant(CreateRestaurant restaurant) {
		restaurantRepository.updateRestaurant(restaurant);

	}

	@Override
	public void deleteRestaurant(long id) {
		restaurantRepository.deleteRestaurant(id);

	}

	@Override
	public RestaurantDb findRestaurantByName(String resturantName) {
		// TODO Auto-generated method stub
		return restaurantRepository.findRestaurantByName(resturantName);
	}

	@Override
	public void addHeaderType(RestaurantHeaderType foodType) {
		restaurantRepository.addHeaderType(foodType);

	}

	@Override
	public void updateHeaderType(RestaurantHeaderType foodType) {
		restaurantRepository.updateHeaderType(foodType);

	}

	@Override
	public RestaurantHeaderType getfoodTypeById(int id) {
		// TODO Auto-generated method stub
		return restaurantRepository.getfoodTypeById(id);
	}

	@Override
	public void deleteHeaderFoodType(int id) {
		restaurantRepository.deleteHeaderFoodType(id);

	}

}
