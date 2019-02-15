 package com.qa.SpringSecurityWithReact.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.SpringSecurityWithReact.entities.User;
import com.qa.SpringSecurityWithReact.services.UserService;

@RestController
public class UserController {
	
	
	@Autowired
	UserService userService;
	
	@PostMapping("/newUser")
	public User registerNewUser(String username, String password) {
		return userService.registerNewUser(username, password);
	}

}
