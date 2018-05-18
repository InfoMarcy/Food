package com.luxmart.store.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.luxmart.model.Restaurant;
import com.luxmart.service.RestaurantService;
import com.luxmart.store.model.Product;
import com.luxmart.store.model.ProductAddOn;
import com.luxmart.store.model.RestaurantDb;
import com.luxmart.store.service.AmazonS3Services;
import com.luxmart.store.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminProduct {
	
	
	// Amazon service storage 
	@Autowired
	AmazonS3Services amazonS3Services;

	
	@Autowired
	ProductService productService;
	@Autowired
	RestaurantService restaurantService;
	

	
	// save product to database
	
	@RequestMapping(value= "/product/addProduct", method= RequestMethod.POST)
	public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request){
		
		// check if the data submitted from jsp has errors
		if(result.hasErrors()){
			
			return "forward:/admin/product/addProduct/"+product.getManufacturer();
		}
		
		// quick fix for now
		String productType = product.getManufacturer().toString();
		
		Restaurant restaurant = restaurantService.getRestaurantByName(productType);	
		
		
	 	product.setType(restaurant.getType().toString());
		
	 // add new Product to Database
		productService.addProduct(product);
		
		
		// save image 
		MultipartFile productImage = product.getProductImage();
		
		String imageUrl = null;
		
		if(productImage != null && !productImage.isEmpty()){
			
			 try {
				//	productImage.transferTo(new File(path.toString()));
					
				// convert image to pass it to amazon s3
					InputStream s3Image = productImage.getInputStream();
					
					String productImageName = product.getId() + ".png";
					imageUrl =	amazonS3Services.uploadFile(productImageName, s3Image);
				} 
				 catch (Exception e) {
					 e.printStackTrace();
					throw new RuntimeException("Product Image saving Failed ", e);
				}
		}
		
	           if(imageUrl != null) {
	        	   product.setImageUrl(imageUrl);
	           }
		     
	           //add product image
	           productService.updateProduct(product);
	          
	        
	           return "forward:/admin/productInventory/"+product.getManufacturer();
	}
	
	
	
	// add Product function
	@RequestMapping(value ="/product/addProduct/{restaurantName}", method= RequestMethod.GET)
	public String addProduct(@PathVariable(value ="restaurantName") String restaurantName, Model model){
		
		
		// set default values to set the values on the jsp page
		Product product = new Product();
		//product.setCategory("Lunch");

		
		
		RestaurantDb restaurant = restaurantService.findRestaurantByName(restaurantName);
		model.addAttribute("restaurant", restaurant);
		
		
		
		
		model.addAttribute("product", product);
		return "Admin/addProduct";
	}
	
	

	// edit Product function
	@RequestMapping("/product/editProduct/{id}")
	public String editProduct(@PathVariable("id") int  id, Model model){
		
		Product product = productService.getProductById(id);
		
		
		
		
		model.addAttribute("product", product);
		return "Admin/editProduct";
	}
	
	

	// save product to database
	@RequestMapping( value= "/product/editProduct", method= RequestMethod.POST)
	public String editProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, HttpServletRequest request){
		
		// check if the data submitted from jsp has errors
		if(result.hasErrors()){			
			
			 return "forward:/admin/product/editProduct/"+product.getManufacturer();
		}
		
		// save image 
		MultipartFile productImage = product.getProductImage();
		
		
		String imageUrl = null;
		if(productImage != null && !productImage.isEmpty()){
			
			
			 try {	
				// convert image to pass it to amazon s3
					InputStream s3Image = productImage.getInputStream();
					
					String productImageName = product.getId() + ".png";
					imageUrl =	amazonS3Services.uploadFile(productImageName, s3Image);
				} 
				 catch (Exception e) {
					 e.printStackTrace();
					throw new RuntimeException("Product Image saving Failed ", e);
				}
			
		}
		
		if(imageUrl != null) {
     	   product.setImageUrl(imageUrl);
        }
		      // Update the current Product in the Database
				productService.updateProduct(product);
		
		
		 return "forward:/admin/productInventory/"+product.getManufacturer();
	}

	
//	@RequestMapping(value = {"/product/deleteProduct/{id}"}, method = RequestMethod.GET)
//	public String deleteProduct(@PathVariable("id") int id, HttpServletRequest request){
//		
//		      
//			
//		
//		
//		// delete the product
//		productService.deleteProduct(id);
//		amazonS3Services.deleteImage(id+".png");
//		// return the ModelView
//		return "redirect:/admin/productInventory/{restaurantName}";
//		
//	}
	
	
	
	//remove an item from the  cart
	    @RequestMapping(value = {"/product/deleteProduct/{id}"}, method = RequestMethod.GET)
		@ResponseStatus(value = HttpStatus.NO_CONTENT)
		public void deleteProduct(@PathVariable("id") int id){


			// delete the product
			productService.deleteProduct(id);
			amazonS3Services.deleteImage(id+".png");
			
		}
	
	
	// Addon Product function
		@RequestMapping("/product/productAddOn/{id}")
		public String productAddOn(@PathVariable("id") int  id, Model model){
			
			Product product = productService.getProductById(id);
			
			ProductAddOn productAddOn = new ProductAddOn(); 
			
			model.addAttribute("productAddOn", productAddOn);
			model.addAttribute("product", product);
			return "Admin/productAddOn";
		}
	
		
		// save product to database
		@RequestMapping( value= "/product/productAddOn", method= RequestMethod.POST)
		public String productAddOnPost(@Valid @ModelAttribute("productAddOn") ProductAddOn productAddOn, BindingResult result){
			
			// check if the data submitted from jsp has errors
			if(result.hasErrors()){
				
				return "Admin/productAddOn";
			}

			productService.addProductAddOn(productAddOn);
			
			// return the ModelView
			return "redirect:/admin/productInventory";
}
		
		
		// show a Product Details
		@RequestMapping("/viewProduct/{id}")
		public String viewProduct(@PathVariable("id") int id, Model model) throws IOException {
			Product product = productService.getProductById(id);
			model.addAttribute("product", product);
			
			return "Admin/viewProduct";
		}
		
}




