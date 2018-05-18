package com.luxmart.model;

public class GoogleLatLng {

	
	private String googleLat;
	private String googleLng;
	private String deliveryAddress;
	private String postalCode;
	
	private String streetNumber;
	private String streetName;
	private String apartmentNumber;
	private String city;
	private String state;
	private String country;

	
	public GoogleLatLng(){
		
	}
	
	

	public GoogleLatLng(String googleLat, String googleLng, String deliveryAddress, String postalCode,
			String streetNumber, String streetName, String apartmentNumber, String city, String state, String country) {
		super();
		this.googleLat = googleLat;
		this.googleLng = googleLng;
		this.deliveryAddress = deliveryAddress;
		this.postalCode = postalCode;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.apartmentNumber = apartmentNumber;
		this.city = city;
		this.state = state;
		this.country = country;
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


	public String getPostalCode() {
		return postalCode;
	}


	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public String getStreetNumber() {
		return streetNumber;
	}


	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
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
}
