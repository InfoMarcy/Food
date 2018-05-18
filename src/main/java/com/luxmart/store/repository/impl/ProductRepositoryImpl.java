package com.luxmart.store.repository.impl;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luxmart.store.model.Product;
import com.luxmart.store.model.ProductAddOn;
import com.luxmart.store.repository.ProductRepository;

@Repository("productRepository")
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	
	@Autowired
	  public ProductRepositoryImpl(EntityManagerFactory factory) {
	    if(factory.unwrap(SessionFactory.class) == null){
	      throw new NullPointerException("factory is not a hibernate factory");
	    }
	    this.sessionFactory = factory.unwrap(SessionFactory.class);
	  }
	

	// find a list of all products
	@Override
	public List<Product> findAllProducts() {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product");

		@SuppressWarnings("unchecked")
		List<Product> product = query.list();

		session.flush();
		return product;
	}

	// get a product by id
	@Override
	public Product getProductById(int id) {

		Session session = sessionFactory.getCurrentSession();

		Product product = (Product) session.get(Product.class, id);
		session.flush();

		return product;

	}

	// add a Products into the Database
	@Override
	public void addProduct(Product product) {

		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		session.flush();
		
		
	}

	// update a product in the Database
	@Override
	public void updateProduct(Product product) {
		
		Session session = sessionFactory.getCurrentSession();
		
		// if there is no image dont update the link
		if (product.getImageUrl() == null || product.getImageUrl().isEmpty()) {
			// the query session.createQuery uses the name of the class not the name of the database table
			Query query = session.createQuery("update Product set name = ?, description = ?, category = ?, type = ?, price = ?, category_description = ? Where id = ?");	
					
			query.setString(0, product.getName());
			query.setString(1, product.getDescription());
			query.setString(2, product.getCategory());
			query.setString(3, product.getType());
			query.setDouble(4, product.getPrice());
			query.setString(5, product.getCategory_description());
			query.setInteger(6, product.getId());
			query.executeUpdate();
			
		} else { //update everything
			session.saveOrUpdate(product);
			session.flush();
		}
		
				
				
		
		
		

	}

	// delete a product from database
	@Override
	public void deleteProduct(int id) {
		
		// delete the a product
		Session session = sessionFactory.getCurrentSession();
		session.delete(getProductById(id));
		session.flush();

	}


	@Override
	public List<Product> findProductByRestaurant(String searchCondition) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Product Where manufacturer = ? Order By category");
        query.setString(0, searchCondition);
		@SuppressWarnings("unchecked")
		List<Product> product = query.list();

		session.flush();
		return product;
		
	}

// add optionals or AddOns to the product
//	@Override
//	public void addProductAddOn(ProductAddOn productAddOn) {
//		Session session = sessionFactory.getCurrentSession();
//		
//		 int existId = 0;
//		
//		try{
//		Query query = session.createQuery("Select id from ProductAddOn Where name = ? And Product_id = ? Order By category");
//		 query.setString(0, productAddOn.getName());
//		 query.setInteger(1, productAddOn.getProduct().getId());
//		  existId  =  (int) query.uniqueResult();
//		} catch (Exception e){
//			
//			
//		}
//		 // ckeck if product exist in Db
//		 if(existId > 0){			 
//			 productAddOn.setId(existId);			 
//			
//		 }
//		 
//		 session.saveOrUpdate(productAddOn);
//		session.flush();
//
//		
//	}


	@Override
	public void addProductAddOn(ProductAddOn productAddOn) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
