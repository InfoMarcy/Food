package com.luxmart.security.service;

import java.util.Optional;

import com.luxmart.security.model.User;

public interface UserService {
	 public Optional<User> findUserByEmail(String email);
	    public Optional<User> findUserByResetToken(String resetToken);
	    public void save(User user);

}
