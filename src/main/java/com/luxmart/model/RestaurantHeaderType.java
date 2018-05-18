package com.luxmart.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class RestaurantHeaderType implements Serializable {
	
	private static final long serialVersionUID = -2079625726900103485L;
	
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty(message ="The Restaurant food Type is required.")
	@Column(name = "foodType",unique=true)
	private String foodType;
	private String imageUrl;

	
	@Transient
	private MultipartFile image;
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public String getFoodType() {
		return foodType;
	}
	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	

}
