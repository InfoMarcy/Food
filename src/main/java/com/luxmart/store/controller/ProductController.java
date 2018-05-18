package com.luxmart.store.controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luxmart.model.Restaurant;
import com.luxmart.service.RestaurantService;
import com.luxmart.store.model.Customer;
import com.luxmart.store.model.Product;
import com.luxmart.store.service.CustomerService;
import com.luxmart.store.service.ProductService;

@Controller
@RequestMapping("/store")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	RestaurantService restaurantService;
	
	@Autowired
	CustomerService customerService;
	
	String restName;
	
	@RequestMapping()
	public String getRestaurantProducts(@RequestParam(value= "name", required=false) String name, Model model, @AuthenticationPrincipal User activeUser){
		
		if(restName == null && (name == null || name.isEmpty() )){
			
			return "redirect:/restaurants";
		}
		
		
		if(name != null && !name.isEmpty()){
			
			restName = name;
		} else {
			
			name = restName;
		}

		// set the view as the Jsp page name index
		//ModelAndView mv = new ModelAndView("Store/RestaurantProducts");
		
		 // Horizontal Scroll Bar Restaurant Type
		List<Product> product = productService.findProductByRestaurant(name);
		
		
		// get the values of the product category in a list
		List<String> productCategory = new ArrayList<>();
		
		// iterate through the list to find the categories
		for (int i = 0; i < product.size(); i++){	
			productCategory.add(product.get(i).getCategory().toString());
			
		}
		
		// get the distinct categories from the product List category
		LinkedHashSet<String> restProductCategory = new LinkedHashSet<String>(productCategory);
				
	
		
		//get the restaurant by name
        Restaurant restaurant = restaurantService.getRestaurantByName(name);		
        
        model.addAttribute("restProductCategory", restProductCategory);
        model.addAttribute("products", product);
        model.addAttribute("restaurant", restaurant);
        
        
        
     // retrieve customer info form the session and customer and cart is binding as one to one
     		// when a customer is create it is also created a cart for that customer
     		// get the customer info of the customer that is currently logged in
     		Customer customer = customerService.getCustomerByUsername(activeUser.getUsername());
     		
     		// get the cart info of the customer by retrieving the cart Id
     		int cartId = customer.getCart().getCartId();
        
		model.addAttribute("cartId", cartId );
     

		// return the ModelView
		 return "Store/RestaurantProducts";
	
	
	}
	

}
