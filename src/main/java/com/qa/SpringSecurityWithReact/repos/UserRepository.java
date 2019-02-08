package com.qa.SpringSecurityWithReact.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.SpringSecurityWithReact.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}