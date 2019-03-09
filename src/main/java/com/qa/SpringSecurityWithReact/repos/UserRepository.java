package com.qa.SpringSecurityWithReact.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.SpringSecurityWithReact.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}