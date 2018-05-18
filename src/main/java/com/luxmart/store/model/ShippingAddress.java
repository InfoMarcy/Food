package com.luxmart.store.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="shipping_address")
public class ShippingAddress implements Serializable{	

	private static final long serialVersionUID = -9007794637683659417L;
	// Shipping Address
	@Id
	@GeneratedValue
	private Long shippingId;	
	private String streetNumber;
	private String streetName;
	private String apartmentNumber;
	private String city;
	private String state;
	private String country;
	private String postalCode;
	private String deliveryInstructions;
	
	@ManyToOne	
	private Customer customer;
	
	// google address
	private String googleLat;
	private String googleLng;
	private String deliveryAddress;
	

	public ShippingAddress() {
		
	}
	
	
	
	public Long getShippingId() {
		return shippingId;
	}
	public void setShippingId(Long shippingId) {
		this.shippingId = shippingId;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getApartmentNumber() {
		return apartmentNumber;
	}
	public void setApartmentNumber(String apartmentNumber) {
		this.apartmentNumber = apartmentNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	public String getDeliveryInstructions() {
		return deliveryInstructions;
	}
	public void setDeliveryInstructions(String deliveryInstructions) {
		this.deliveryInstructions = deliveryInstructions;
	}
	
	public String getGoogleLat() {
		return googleLat;
	}
	public void setGoogleLat(String googleLat) {
		this.googleLat = googleLat;
	}
	public String getGoogleLng() {
		return googleLng;
	}
	public void setGoogleLng(String googleLng) {
		this.googleLng = googleLng;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}



	public String getStreetNumber() {
		return streetNumber;
	}



	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}



	public String getPostalCode() {
		return postalCode;
	}



	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}



	public Customer getCustomer() {
		return customer;
	}



	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


}
