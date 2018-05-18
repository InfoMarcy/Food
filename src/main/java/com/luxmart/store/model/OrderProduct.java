package com.luxmart.store.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "order_products")
public class OrderProduct implements Serializable {

	private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue
		private Long id;

	// products
		@Column(unique=true)
		private String code;
		@NotEmpty
		private String name;
		private String category;	
		@Column(columnDefinition ="text")
		private String description;
		private String type;
		private int quantity;	
		private BigDecimal price;	
		private String manufacturer;
		
		@ManyToOne
		@JoinColumn(name ="orderReportId")
		private OrderReport orderReport;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

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

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public String getManufacturer() {
			return manufacturer;
		}

		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}

		public OrderReport getOrderReport() {
			return orderReport;
		}

		public void setOrderReport(OrderReport orderReport) {
			this.orderReport = orderReport;
		}

		
	
}
