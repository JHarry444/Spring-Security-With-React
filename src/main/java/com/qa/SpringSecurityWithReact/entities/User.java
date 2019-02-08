package com.qa.SpringSecurityWithReact.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

	public User(String username, String password) {

		this.username = username;
		this.password = password;
	}

	public User() {
		// Default Constructor
	}
 
	@Id
	private String username;

	private String password;
	
	private Boolean accountNonExpired = true;
	
	private Boolean accountNonLocked = true;
	
	private Boolean credentialsNonExpired = true;
	
	private Boolean enabled = true;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

}