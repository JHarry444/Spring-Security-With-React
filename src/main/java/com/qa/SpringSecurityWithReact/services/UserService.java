package com.qa.SpringSecurityWithReact.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.qa.SpringSecurityWithReact.entities.User;
import com.qa.SpringSecurityWithReact.repos.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User registerNewUser(String username, String password) {
		if (userRepo.findByUsername(username) != null) {
			return null;
		}
		User toRegister = new User();
		toRegister.setUsername(username);
		toRegister.setPassword(passwordEncoder.encode(password));
		return userRepo.save(toRegister);
	}
	
}
