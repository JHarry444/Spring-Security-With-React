package com.qa.SpringSecurityWithReact.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.qa.SpringSecurityWithReact.services.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class HIGSecurityAdapter extends WebSecurityConfigurerAdapter {

	@Autowired
	MyUserDetailsService musds;

	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/eedb");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("password");
		return driverManagerDataSource;

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		this.musds.init();
		auth.jdbcAuthentication().dataSource(dataSource())
				.usersByUsernameQuery("select username,password, enabled from user where username=?")
				.authoritiesByUsernameQuery("select username, role from user_role where username=?").and()
				.authenticationProvider(authenticationProvider()).userDetailsService(musds).passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
		http.authorizeRequests().regexMatchers("/newUser").fullyAuthenticated().and().formLogin()
				.loginProcessingUrl("/login").successHandler(successHandler()).failureHandler(failureHandler()).and()
				.userDetailsService(musds);
	}

	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new CustomFailureHandler();
	}

	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomSuccessHandler();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(musds);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

}
