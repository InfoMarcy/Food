package com.luxmart.store.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

@Entity
public class ProductOptions implements Serializable {
	
	private static final long serialVersionUID = 7500426574103356417L;

	@Id
	@GeneratedValue
	private int id;

@ManyToOne	
private Product product;

private String choice;

@Min(value = 0 , message =" The product price must not be less than 0")
private double price;

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Product getProduct() {
	return product;
}

public void setProduct(Product product) {
	this.product = product;
}

public String getChoice() {
	return choice;
}

public void setChoice(String choice) {
	this.choice = choice;
}

public double getPrice() {
	return price;
}

public void setPrice(double price) {
	this.price = price;
}


}
