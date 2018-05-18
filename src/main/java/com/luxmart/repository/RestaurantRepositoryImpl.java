package com.luxmart.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.luxmart.model.Restaurant;
import com.luxmart.model.RestaurantHeaderType;
import com.luxmart.repository.util.RestaurantRowMapper;
import com.luxmart.repository.util.RestaurantRowMapper2;
import com.luxmart.security.model.Authorities;
import com.luxmart.store.model.RestaurantDb;
import com.luxmart.store.model.util.CreateRestaurant;

@Repository("restaurantRepository")
@Transactional
public class RestaurantRepositoryImpl implements RestaurantRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	public RestaurantRepositoryImpl(EntityManagerFactory factory) {
		if (factory.unwrap(SessionFactory.class) == null) {
			throw new NullPointerException("factory is not a hibernate factory");
		}
		this.sessionFactory = factory.unwrap(SessionFactory.class);
	}

	@Autowired
	DataSource dataSourse;

	// query the database and return the object that was created
	@Override
	public Restaurant getRestaurant(String restaurantName) {

		 Restaurant restaurant = jdbcTemplate.queryForObject("Select * From restaurants  Where name = ?",
	                new RestaurantRowMapper(), restaurantName);
	 
	        return restaurant;
	}

	// query the database and return the restaurant by type or by name
	@SuppressWarnings("unchecked")
	@Override
	public List<Restaurant> findRestaurant(String lat, String lng, int km, String search) {
		// System.out.println("Testing values" + lat + " "+ lng+ " " + km + " "
		// + search);
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourse).withProcedureName("spFindRestaurantByType")
				.returningResultSet("Restaurants", new RestaurantRowMapper());
		;

		// get the values from the store procedure
		Map<String, Object> restaurant = jdbcCall.execute(lat, lng, km, search);
		return (List<Restaurant>) restaurant.get("Restaurants");

	}

	// get a list of all the restaurant for the admin page
	@Override
	public List<RestaurantDb> findAllRestaurant() {// get a restaurants of rides from the
													// Database
//		List<Restaurant> restaurants = jdbcTemplate.query("Select * From restaurants", new RestaurantRowMapper2());
//
//		return restaurants;
		
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from RestaurantDb");

		@SuppressWarnings("unchecked")
		List<RestaurantDb> restaurants = query.list();

		session.flush();
		return restaurants;

	}

	// getting a List of rides from Database
	@SuppressWarnings("unchecked")
	@Override
	public List<Restaurant> getRestaurants(String lat, String lng, int km) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourse).withProcedureName("SpFindRestaurants")
				.returningResultSet("Restaurants", new RestaurantRowMapper());
		;

		// get the values from the store procedure
		Map<String, Object> restaurant = jdbcCall.execute(lat, lng, km);
		return (List<Restaurant>) restaurant.get("Restaurants");

	}

	// get the headers of the image ribbon to find restaurants by type
	@Override
	public List<RestaurantHeaderType> getHeaders() {
		// // get a list of rides from the Database
		// List<RestaurantHeaderType> foodHeaders = jdbcTemplate.query("Select *
		// From rest_scroll_food_type_header", new foodHeaderRowMapper());
		//
		// // return the scroll header data
		// return foodHeaders;
		//

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from RestaurantHeaderType");

		@SuppressWarnings("unchecked")
		List<RestaurantHeaderType> foodHeaders = query.list();

		// session.flush();
		return foodHeaders;

	}

	// query the database and return the object that was created
	@SuppressWarnings("unchecked")
	@Override
	public List<Restaurant> getFoodTypeRest(String lat, String lng, String foodType) {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSourse).withProcedureName("spFindRestaurantByType")
				.returningResultSet("Restaurants", new RestaurantRowMapper());
		;

		// get the values from the store procedure
		Map<String, Object> restaurant = jdbcCall.execute(lat, lng, 10, foodType);
		return (List<Restaurant>) restaurant.get("Restaurants");

	}

	// add Credentials when creating new User or customer
	@Override
	public void addCredentials(Authorities Authority) {
		// System.out.println("Repository");
		// Recommended way for using jdbcTemplate with queries
		// this uses prepared Statements Standard of substituting a value into
		// the ? fields
		// this method just insert the data on the database and don't return the
		// created object
		jdbcTemplate.update("Insert Into Authorities (email, Authority) values (?, ?)", Authority.getEmail(),
				Authority.getAuthority());

	}

	// get resturant by name
	@Override
	public Restaurant getRestaurantByName(String name) {
		Restaurant restaurant = jdbcTemplate.queryForObject("Select * From restaurants  Where name = ?",
				new RestaurantRowMapper2(), name);

		return restaurant;
		
//		
//		Session session = sessionFactory.getCurrentSession();
//		Query query = session.createQuery("Select * From restaurants  Where name = ?");
//        query.setString(0, name);
//	
//	   Restaurant restaurant = (Restaurant) query.uniqueResult();
//
//		session.flush();
//		return restaurant;
	}

	// add new Restaurant
	@Override
	public void addRestaurant(CreateRestaurant restaurant) {

		Session session = sessionFactory.getCurrentSession();

		restaurant.getRestaurant().setStreetName(restaurant.getLocation().getStreetName());
		session.saveOrUpdate(restaurant.getRestaurant());

		RestaurantDb restaurant2 = getRestaurantById(restaurant.getRestaurant().getId());

		restaurant.getLocation().setRestaurant(restaurant2);

		session.saveOrUpdate(restaurant.getLocation());
		session.flush();
	}

	// get Restaurant By id
	@Override
	public RestaurantDb getRestaurantById(long id) {

		Session session = sessionFactory.getCurrentSession();

		RestaurantDb restaurant = (RestaurantDb) session.get(RestaurantDb.class, id);
		session.flush();

		return restaurant;

	}

	// update Restaurant
	@Override
	public void updateRestaurant(CreateRestaurant restaurant) {

		Session session = sessionFactory.getCurrentSession();
		// the query session.createQuery uses the name of the class not the name
		// of the database table
		Query query = session.createQuery("update RestaurantDb set imageUrl = ? Where id = ?");

		query.setString(0, restaurant.getRestaurant().getImageUrl());
		query.setLong(1, restaurant.getRestaurant().getId());
		query.executeUpdate();

	}

	@Override
	public void deleteRestaurant(long id) {
		// TODO Auto-generated method stub

	}

	// find Restaurant by name
	@Override
	public RestaurantDb findRestaurantByName(String name) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from RestaurantDb Where name = ?");
		query.setString(0, name);

		RestaurantDb restaurant = (RestaurantDb) query.uniqueResult();

		return restaurant;
	}

	// add new Restaurant header type
	@Override
	public void addHeaderType(RestaurantHeaderType foodType) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(foodType);

		session.flush();

	}

	// update Restaurant Header Type
	@Override
	public void updateHeaderType(RestaurantHeaderType foodType) {

		Session session = sessionFactory.getCurrentSession();
		// the query session.createQuery uses the name of the class not the name
		// of the database table
		// Query query = session.createQuery("update RestaurantHeaderType set
		// imageUrl = ? set foodType = ? Where id = ?");
		//
		// query.setString(0, foodType.getImageUrl());
		// query.setString(1, foodType.getFoodType());
		// query.setLong(2, foodType.getId());
		//
		// query.executeUpdate();

		session.update(foodType);
		session.flush();

	}

	// get Restaurant header Type By id
	@Override
	public RestaurantHeaderType getfoodTypeById(int id) {

		Session session = sessionFactory.getCurrentSession();

		RestaurantHeaderType foodType = (RestaurantHeaderType) session.get(RestaurantHeaderType.class, id);
		session.flush();

		return foodType;

	}

	// delete Restaurant header food type by Id and by class
	@Override
	public void deleteHeaderFoodType(int id) {
		// delete the a product
		Session session = sessionFactory.getCurrentSession();
		session.delete(getfoodTypeById(id));
		session.flush();
	}

}
