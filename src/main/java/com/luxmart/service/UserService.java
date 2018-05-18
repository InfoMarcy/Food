package com.luxmart.service;

import org.springframework.security.core.userdetails.User;

public interface UserService {

	User findByUsername(String name);

}
