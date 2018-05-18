package com.luxmart.store.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

@Entity
public class ProductAddOn implements Serializable {
	
	private static final long serialVersionUID = 3495178451541866286L;

	@Id
	@GeneratedValue
	private int id;

@ManyToOne	
private Product product;

private String  category;
@Column(unique=true)
private String  name;

private String  description;
@Min(value = 0 , message =" The product price must not be less than 0")
private Double price;


public Product getProduct() {
	return product;
}
public void setProduct(Product product) {
	this.product = product;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}

}
