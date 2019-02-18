/*
 * package com.qa.SpringSecurityWithReact.services;
 * 
 * import java.util.HashSet; import java.util.Set;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.stereotype.Service;
 * 
 * import com.qa.SpringSecurityWithReact.entities.User; import
 * com.qa.SpringSecurityWithReact.repos.UserRepository;
 * 
 * @Service public class UserService {
 * 
 * @Autowired private UserRepository userRepo;
 * 
 * @Autowired private PasswordEncoder passwordEncoder;
 * 
 * public User registerNewUser(String username, String password) { if
 * (userRepo.findByUsername(username) != null) { return null; }
 * Set<GrantedAuthority> authorities = new HashSet<>(); authorities.add(new
 * SimpleGrantedAuthority("USER")); User toRegister = new User(username,
 * passwordEncoder.encode(password), authorities); return
 * userRepo.save(toRegister); }
 * 
 * }
 */