package com.luxmart.store.model;



import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product  implements Serializable {
	
	
	private static final long serialVersionUID = 4762384726040369298L;
	
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique=true)
	private String productCode;
	@NotEmpty (message= " The Product Name is required")
	private String name;
	private String category;
	@Column(columnDefinition ="text")
	private String category_description;
	@Column(columnDefinition ="text")
	private String description;
	@Min(value = 0 , message =" The product price must not be less than 0")
	private double price;
	private String status;
	@Min(value = 0 , message =" The unitInStock must not be less than 0")
	private int unitInStock;
	private String manufacturer;
	private String type;
	
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ProductOptions> productOptions;
	
   @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
   @JsonIgnore
   private List<CartItem> cartItemList;
   
    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
  	private List<ProductAddOn> productAddOn;
    
  
   // @Transient will exclude the  productImage property from the @ Entity class
    @Transient
	private MultipartFile productImage;
	
	
    private String imageUrl; 
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public int getUnitInStock() {
		return unitInStock;
	}
	public void setUnitInStock(int unitInStock) {
		this.unitInStock = unitInStock;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public List<CartItem> getCartItemList() {
		return cartItemList;
	}
	public void setCartItemList(List<CartItem> cartItemList) {
		this.cartItemList = cartItemList;
	}
	public MultipartFile getProductImage() {
		return productImage;
	}
	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}
	public String getCategory_description() {
		return category_description;
	}
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public List<ProductAddOn> getProductAddOn() {
		return productAddOn;
	}
	public void setProductAddOn(List<ProductAddOn> productAddOn) {
		this.productAddOn = productAddOn;
	}
	public List<ProductOptions> getProductOptions() {
		return productOptions;
	}
	public void setProductOptions(List<ProductOptions> productOptions) {
		this.productOptions = productOptions;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	
	

}
