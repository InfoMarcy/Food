package com.luxmart.store.repository.impl;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Repository;

import com.luxmart.security.model.Authorities;
import com.luxmart.security.model.User;
import com.luxmart.security.service.EmailService;
import com.luxmart.store.model.Cart;
import com.luxmart.store.model.Customer;
import com.luxmart.store.model.util.CheckoutInfo;
import com.luxmart.store.model.util.CustomerInfo;
import com.luxmart.store.repository.CustomerRepository;
import com.luxmart.stripe.PaymentService;

@Repository("customerRepository")
@Transactional
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	 @Autowired
	 private EmailService emailService;
	 
	 @Autowired
	 PaymentService paymentService;

	@Override
	public void addCustomer(Customer customer, HttpServletRequest request) {
		
		Session session = sessionFactory.getCurrentSession();
		
	CustomerInfo customerInfoForStripe = new CustomerInfo();
	customerInfoForStripe.setFirstName(customer.getFirstName());
	customerInfoForStripe.setLastName(customer.getLastName());
	customerInfoForStripe.setEmail(customer.getEmail());
	customerInfoForStripe.setPhone(customer.getPhone());
		// Create Stripe customer
		String stripeCustomerId = paymentService.createCustomer(customerInfoForStripe);
		
		// save the customer Id create in stripe
		if(stripeCustomerId != null) {
		customer.setStripeCustomerId(stripeCustomerId);
		customerInfoForStripe.setStripeCustomerId(stripeCustomerId);
		 }
		
		
		// save the data to Database
		session.saveOrUpdate(customer);
	
		
		
		
		
		// add the new user to the security table
		User newUser = new User();		
		newUser.setEmail(customer.getEmail());
		newUser.setEnabled(false);	
		newUser.setCustomerId(customer.getCustomerId());
		newUser.setFirstName(customer.getFirstName());
		newUser.setLastName(customer.getLastName());
		newUser.setPassword(customer.getPassword());
		
		// Generate random 36-character string token for confirmation link	
		newUser.setConfirmationToken(UUID.randomUUID().toString());
		
		
		// add the role for the new User
		Authorities customerAuthority = new Authorities();
		customerAuthority.setEmail(customer.getEmail());
		customerAuthority.setAuthority("ROLE_USER");
		
				
		// save the new Customer User
		session.saveOrUpdate(newUser);
    	session.saveOrUpdate(customerAuthority);
		
		// create a cart for the Customer
		Cart newCart = new Cart();
		newCart.setCustomer(customer);
		
		// set the customer with the  cart
		customer.setCart(newCart);
		
		// save the changes for the cart
		session.saveOrUpdate(customer);		
		session.saveOrUpdate(newCart);
	
		
		// save the changes to database
	     session.flush();
	     

			// to send the email
			String appUrl = request.getScheme() + "://" + request.getServerName();
			
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(customer.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
					+ appUrl + "/confirm?token=" + newUser.getConfirmationToken());
			 registrationEmail.setFrom("luxmartwebdesign@gmail.com");
			
			
			
			// send Email to the new Customer Email
			emailService.sendEmail(registrationEmail);
	}

	
	// get Customer by Id
	@Override
	public Customer getCustomerById(int id) {
		Session session = sessionFactory.getCurrentSession();		
		
		return (Customer) session.get(Customer.class, id);
	}

	
	// get a list of all customers
	@Override
	public List<Customer> getAllCustomers() {
		Session session = sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from Customer");
		
		@SuppressWarnings("unchecked")
		List<Customer> customerList = query.list();
		
		return customerList;
	}
	
	
	@Override
	public User findByConfirmationToken(String token){
		
		Session session = sessionFactory.getCurrentSession();
		
		// the query session.createQuery uses the name of the class not the name of the database table		
		Query query = session.createQuery("from User  Where confirmation_token = ?");
		query.setString(0, token);
		
		return (User) query.uniqueResult();
	}

	// update records in the Database
	@Override
	public void enableUser(User user) {
		
		
		Session session = sessionFactory.getCurrentSession();
		
		
		// the query session.createQuery uses the name of the class not the name of the database table
		Query query = session.createQuery("update User set enabled = ? Where id = ?");	
		query.setBoolean(0, true);
		query.setLong(1, user.getId());		
		query.executeUpdate();
		
	}
	

    // search customer by username that in this case is the email address
	@Override
	public Customer getCustomerByUsername(String username) {
		
		Session session = sessionFactory.getCurrentSession();	
		
		// Hibernate Query
		Query query = session.createQuery("from Customer Where email = ?");
		query.setString(0, username);
		
		return (Customer) query.uniqueResult();
	}


	@Override
	public void saveCustomer(Customer customer) {
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(customer);	
		// save the changes to database
	     session.flush();
	}
	
	
	// add new shipping address
	@Override
	public void addShipping(CheckoutInfo checkoutInfo) {		
		
		Session session = sessionFactory.getCurrentSession();
		
		//ShippingAddress shippingAddress = checkoutInfo.getShipping();
		
		
		
		// Hibernate Query Update
		Query query = session.createQuery("select shippingId from ShippingAddress Where postal_code = ? AND customer_customer_id = ?");
		query.setString(0, checkoutInfo.getShipping().getPostalCode());
	    query.setInteger(1, checkoutInfo.getShipping().getCustomer().getCustomerId());
		
	    if(query.uniqueResult() != null){	
	    	
	    //	System.out.println("if");
	    	
	    	    Long shippingId =  (Long) query.uniqueResult();
	    	    
	    	    
	    	 // the query session.createQuery uses the name of the class not the name of the database table
	  	  		Query updateShippingInfo = session.createQuery("update ShippingAddress set apartment_number = ?, delivery_instructions = ? Where shippingId = ?");	
	  	  	updateShippingInfo.setString(0, checkoutInfo.getShipping().getApartmentNumber());
	  	  updateShippingInfo.setString(1, checkoutInfo.getShipping().getDeliveryInstructions());
	  	updateShippingInfo.setLong(2, shippingId);
	  	updateShippingInfo.executeUpdate();
	  
	    	   
	    			
	    } else { // save or create a new record in the database
	 
	    	 session.saveOrUpdate(checkoutInfo.getShipping());	
				// save the changes to database
			     session.flush();	    	
	    	
	    }
		
			    
			   
	}
	

}
