package com.luxmart.store.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "orders")
public class OrderReport implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	private Long id;

	// customer
	private int customerId;
	private String customerName;
	private String customerEmail;

	private BigDecimal totalPrice;
	private String deliveryAddress;
	private String paymentMethod;
	private String date;
	private BigDecimal cartSubtotal;

	@OneToMany(mappedBy = "orderReport", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	private List<OrderProduct> orderProducts;

	// private String manufacturerAddress;
	// private BigDecimal deliveryFee;
	// private BigDecimal tips;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public BigDecimal getCartSubtotal() {
		return cartSubtotal;
	}

	public void setCartSubtotal(BigDecimal cartSubtotal) {
		this.cartSubtotal = cartSubtotal;
	}

}
