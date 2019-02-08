package com.qa.SpringSecurityWithReact.services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.qa.SpringSecurityWithReact.entities.User;

public class MyUserDetails implements UserDetails {
	
	private static final long serialVersionUID = -3484755980839679471L;
	
	private User user;
	
	public MyUserDetails(User user) {
		this.user = user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getPassword() {
		return this.user.getPassword();
	}

	public String getUsername() {
		return this.user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return this.user.getAccountNonExpired();
	}

	public boolean isAccountNonLocked() {
		return this.user.getAccountNonLocked();
	}

	public boolean isCredentialsNonExpired() {
		return this.user.getCredentialsNonExpired();
	}

	public boolean isEnabled() {
		return this.user.getEnabled();
	}

}
