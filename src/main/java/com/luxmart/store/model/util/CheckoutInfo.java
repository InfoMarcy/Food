package com.luxmart.store.model.util;

import com.luxmart.model.Restaurant;
import com.luxmart.store.model.CustomerOrder;
import com.luxmart.store.model.ShippingAddress;
import com.luxmart.stripe.PaymentModel;

public class CheckoutInfo {
	
	private ShippingAddress shipping;
	private PaymentModel payment;
	private CustomerOrder order;
	private Restaurant restaurant;

	
	public ShippingAddress getShipping() {
		return shipping;
	}
	public void setShipping(ShippingAddress shipping) {
		this.shipping = shipping;
	}
	public PaymentModel getPayment() {
		return payment;
	}
	public void setPayment(PaymentModel payment) {
		this.payment = payment;
	}
	public CustomerOrder getOrder() {
		return order;
	}
	public void setOrder(CustomerOrder order) {
		this.order = order;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	

}
