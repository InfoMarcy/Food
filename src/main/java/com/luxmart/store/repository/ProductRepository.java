package com.luxmart.store.repository;

import java.util.List;

import com.luxmart.store.model.Product;
import com.luxmart.store.model.ProductAddOn;


public interface ProductRepository {
	
	List<Product> findAllProducts();

	Product getProductById(int productId);

	void addProduct(Product product);

	void deleteProduct(int id);

	void updateProduct(Product product);

	List<Product> findProductByRestaurant(String searchCondition);

	void addProductAddOn(ProductAddOn productAddOn);


}
