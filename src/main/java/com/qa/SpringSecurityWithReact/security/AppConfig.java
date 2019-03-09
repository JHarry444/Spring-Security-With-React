package com.qa.SpringSecurityWithReact.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.qa.SpringSecurityWithReact.services.MyUserDetailsService;
@Configuration
public class AppConfig {
	
	@Bean(name="failureHandler")
	public AuthenticationFailureHandler failureHandler() {
		return new CustomFailureHandler();
	}

	@Bean(name="successHandler")
	public AuthenticationSuccessHandler successHandler() {
		return new CustomSuccessHandler();
	}


	@Bean(name = "encoder")
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}
	
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/eedb");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("password");
		return driverManagerDataSource;

	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(MyUserDetailsService muds) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(muds);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}


}
