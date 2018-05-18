package com.luxmart.store.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CustomerOrder implements Serializable {

	private static final long serialVersionUID = 3695056149469448954L;
	
	@Id
	@GeneratedValue
	private int CustomerOrderId;
	
	private int chargeAmount;
	
	@OneToOne
	@JoinColumn(name ="cartId")
	private Cart cart;
	
	@OneToOne
	@JoinColumn(name ="customerId")
	private Customer customer;
	
	@OneToOne
	@JoinColumn(name ="billingAddressId")
	private BillingAddress  billingAddress;
	
	@OneToOne
	@JoinColumn(name ="shippingId")
	private ShippingAddress  ShippingAddress;

	public int getCustomerOrderId() {
		return CustomerOrderId;
	}

	public void setCustomerOrderId(int customerOrderId) {
		CustomerOrderId = customerOrderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public BillingAddress getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(BillingAddress billingAddress) {
		this.billingAddress = billingAddress;
	}

	public ShippingAddress getShippingAddress() {
		return ShippingAddress;
	}

	public void setShippingAddress(ShippingAddress shippingAddress) {
		ShippingAddress = shippingAddress;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public int getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(int chargeAmount) {
		this.chargeAmount = chargeAmount;
	}


}
