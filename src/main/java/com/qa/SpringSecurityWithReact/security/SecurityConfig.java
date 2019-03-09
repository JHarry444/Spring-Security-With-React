package com.qa.SpringSecurityWithReact.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationProvider;
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
import org.springframework.security.web.csrf.CsrfFilter;

import com.qa.SpringSecurityWithReact.services.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private MyUserDetailsService muds;
	private AuthenticationFailureHandler failureHandler;
	private AuthenticationSuccessHandler successHandler;
	private AuthenticationProvider authenticationProvider;
	private DataSource dataSource;
	private PasswordEncoder encoder;

	public SecurityConfig(MyUserDetailsService muds, AuthenticationFailureHandler failureHandler,
			AuthenticationSuccessHandler successHandler, AuthenticationProvider authenticationProvider,
			DataSource dataSource, PasswordEncoder encoder) {
		this.muds = muds;
		this.failureHandler = failureHandler;
		this.successHandler = successHandler;
		this.authenticationProvider = authenticationProvider;
		this.dataSource = dataSource;
		this.encoder = encoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		this.muds.init();
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password, enabled from user where username=?")
				.authoritiesByUsernameQuery("select userid, role from user_role where userid=?").and()
				.authenticationProvider(authenticationProvider).userDetailsService(muds).passwordEncoder(encoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		http.authorizeRequests().regexMatchers("/newUser").fullyAuthenticated().and().formLogin()
				.loginProcessingUrl("/login").successHandler(successHandler).failureHandler(failureHandler).and()
				.userDetailsService(muds);
	}

}
