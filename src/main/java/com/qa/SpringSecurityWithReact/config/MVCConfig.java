package com.qa.SpringSecurityWithReact.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MVCConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/user").allowedOrigins("http://35.234.140.95:3001", "http://localhost:3001")
				.allowCredentials(true).allowedMethods("PUT", "POST", "GET");
	}
}
