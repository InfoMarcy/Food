package com.luxmart.store.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Customer implements Serializable {

	private static final long serialVersionUID = 6887783198180431340L;
	
	@Id
	@GeneratedValue
	private int customerId;
	
	@NotEmpty(message ="The customer fist name must not be null")
	private String firstName;
	
   @NotEmpty(message ="The customer last name must not be null")
	private String lastName;
	
	
	@NotEmpty(message ="The customer email must not be null")
	@Email
	private String email;
	
	private String phone;
	
	private String stripeCustomerId;
	

	@NotEmpty(message ="The customer password must not be null")
	private String password;
	
    // to indicate if the customer is active or not
	private boolean enabled;

	
    @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<ShippingAddress> ShippingAddress;
    
    
    @OneToMany(mappedBy = "customer", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
   	private List<BillingAddress> billingAddress;
	
	
	@OneToOne
	@JoinColumn(name ="cartId")
	@JsonIgnore
	private Cart  cart;


	public int getCustomerId() {
		return customerId;
	}


	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getStripeCustomerId() {
		return stripeCustomerId;
	}


	public void setStripeCustomerId(String stripeCustomerId) {
		this.stripeCustomerId = stripeCustomerId;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public List<ShippingAddress> getShippingAddress() {
		return ShippingAddress;
	}


	public void setShippingAddress(List<ShippingAddress> shippingAddress) {
		ShippingAddress = shippingAddress;
	}


	public List<BillingAddress> getBillingAddress() {
		return billingAddress;
	}


	public void setBillingAddress(List<BillingAddress> billingAddress) {
		this.billingAddress = billingAddress;
	}


	public Cart getCart() {
		return cart;
	}


	public void setCart(Cart cart) {
		this.cart = cart;
	}

	

	
}
