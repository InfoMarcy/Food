package com.luxmart.security.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.luxmart.security.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	 Optional<User> findByEmail(String email);
	 Optional<User> findByResetToken(String resetToken);
	

}
