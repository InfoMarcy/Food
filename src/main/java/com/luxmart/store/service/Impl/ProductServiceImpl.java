package com.luxmart.store.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxmart.store.model.Product;
import com.luxmart.store.model.ProductAddOn;
import com.luxmart.store.repository.ProductRepository;
import com.luxmart.store.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository restaurantRepository;
	
	   @Override
	     public List<Product> findAllProducts(){
	    	 return restaurantRepository.findAllProducts();
	     }
	     
	     @Override
	     public Product getProductById(int productId){
	    	 return restaurantRepository.getProductById(productId);
	     }
	     
	     @Override
	     public void addProduct(Product product){
	    	  restaurantRepository.addProduct(product);
	     }
	     @Override
	     public void deleteProduct(int id) {
	    	  restaurantRepository.deleteProduct(id);
	     }
	    	 
	     @Override	 
	     public void updateProduct(Product product) {
	    	  restaurantRepository.updateProduct(product);
	     }

		@Override
		public List<Product> findProductByRestaurant(String searchCondition) {
			// TODO Auto-generated method stub
			return restaurantRepository.findProductByRestaurant(searchCondition);
		}

		@Override
		public void addProductAddOn(ProductAddOn productAddOn) {
			restaurantRepository.addProductAddOn(productAddOn);
			
		}

}
