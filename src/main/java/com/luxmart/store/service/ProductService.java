package com.luxmart.store.service;

import java.util.List;

import com.luxmart.store.model.Product;
import com.luxmart.store.model.ProductAddOn;

public interface ProductService {
	
	List<Product> findAllProducts();


	Product getProductById(int productId);


	void addProduct(Product product);


	void deleteProduct(int id);


	void updateProduct(Product product);


	List<Product> findProductByRestaurant(String searchCondition);


	void addProductAddOn(ProductAddOn productAddOn);

}
