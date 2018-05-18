package com.luxmart.security.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxmart.security.model.User;
import com.luxmart.security.repository.UserRepository;
import com.luxmart.security.service.UserService;

@Service("userService")
public class UserServiceRepositoryImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findUserByEmail(String email) {		
		return userRepository.findByEmail(email);
	}

	@Override
	public Optional<User> findUserByResetToken(String resetToken) {
		
		return userRepository.findByResetToken(resetToken);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);

	}

}
