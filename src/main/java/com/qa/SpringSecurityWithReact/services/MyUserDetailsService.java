package com.qa.SpringSecurityWithReact.services;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.qa.SpringSecurityWithReact.entities.User;
import com.qa.SpringSecurityWithReact.entities.UserRole;
import com.qa.SpringSecurityWithReact.repos.UserRepository;
import com.qa.SpringSecurityWithReact.repos.UserRolesRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	PasswordEncoder passwordEncoder;

	public void init() {
		User user = userRepository.findByUsername("admin");
		if (user == null) {
			user = this.registerNewUser("admin", "password", "ADMIN");
		}
	}

	public User registerNewUser(String username, String password) {
		return registerNewUser(username, password, "USER");
	}

	public User registerNewUser(String username, String password, String... roles) {
		User newUser = this.userRepository.save(new User(username, passwordEncoder.encode(password), 1));
		for (String role : roles) {
			this.userRolesRepository.save(new UserRole(1L, role));
		}
		return newUser;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("No user present with username: " + username, null);
		} else {
			List<String> userRoles = userRolesRepository.findRoleByUserName(username);
			return new MyUserDetails(user, userRoles);
		}
	}

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRolesRepository userRolesRepository;
}