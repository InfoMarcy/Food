package com.luxmart.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luxmart.service.RestaurantService;
import com.luxmart.store.model.Customer;
import com.luxmart.store.model.Product;
import com.luxmart.store.model.RestaurantDb;
import com.luxmart.store.service.CustomerService;
import com.luxmart.store.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminHome {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	RestaurantService restaurantService;
	
	@RequestMapping()
	public String AdminPage(){		
		
		// return Admin page view
		return "Admin/admin";		
	}
	
	// Get all product on the product inventory page
	@RequestMapping("/productInventory/{restaurantName}")
	public String productInventory(@PathVariable(value ="restaurantName") String restaurantName, Model model){
		
		//get a list of all the products		
		List<Product> products  = productService.findProductByRestaurant(restaurantName);
		
			
		// add the product to the model to use it in jsp page
		model.addAttribute("products", products);
		model.addAttribute("restaurant", restaurantName);
	// return the product inventory page	
		return "Admin/productInventory";
	}
	
	
	// Get a list of all the registered customer
	@RequestMapping("/customer")
	public String customer(Model model){	

		try {
			
			List<Customer> customerList = customerService.getAllCustomers();
			model.addAttribute("customerList", customerList);
			return "Admin/customerManagement";
		}catch(Exception e){
			
			
			model.addAttribute("error", "there was an error while loading the customer, please verify with the admin thanks");
			return "redirect:/admin";
			
		}
	
		
	}
	
	
	// Geta list of all the registered customer
		@RequestMapping("/restaurant")
		public String RestaurantDb(Model model){	

		List<RestaurantDb> restaurants = restaurantService.findAllRestaurant();
		model.addAttribute("restaurants", restaurants);
			return "Admin/restaurantManagement";
		}

}
