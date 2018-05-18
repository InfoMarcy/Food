package com.luxmart.security.model;


import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name= "Users")
public class User {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", nullable = false, updatable = false)
	    private int id;
	    
	    @Column(name = "email", nullable = false, unique = true)
		@Email(message = "Please provide a valid e-mail")
		@NotEmpty(message = "Please provide an e-mail")
	    private String email;
	    
	    @Column(name = "first_name")
		@NotEmpty(message = "Please provide your first name")
	    private String firstName;
	    private String lastName;
	    @Column(name = "password", nullable = false)
	    private String password;
	    private boolean enabled;
	    private int customerId;	   
		private String confirmationToken;
		
		
		private LocalDateTime created_on;
		
		@Column(name = "last_login")
		private Date lastLogin;

		@Column(name = "reset_token")
		private String resetToken;
		
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public boolean isEnabled() {
			return enabled;
		}
		public void setEnabled(boolean enabled) {
			this.enabled = enabled;
		}
		public int getCustomerId() {
			return customerId;
		}
		public void setCustomerId(int customerId) {
			this.customerId = customerId;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getConfirmationToken() {
			return confirmationToken;
		}
		public void setConfirmationToken(String confirmationToken) {
			this.confirmationToken = confirmationToken;
		}
		public LocalDateTime getCreated_on() {
			return created_on;
		}
		public void setCreated_on(LocalDateTime created_on) {
			this.created_on = created_on;
		}
		public Date getLastLogin() {
			return lastLogin;
		}
		public void setLastLogin(Date lastLogin) {
			this.lastLogin = lastLogin;
		}
		public String getResetToken() {
			return resetToken;
		}
		public void setResetToken(String resetToken) {
			this.resetToken = resetToken;
		}

}
