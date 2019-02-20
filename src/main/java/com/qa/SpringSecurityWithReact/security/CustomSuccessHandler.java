package com.qa.SpringSecurityWithReact.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	public CustomSuccessHandler() {
	}

	public CustomSuccessHandler(String defaultTargetUrl) {
		super(defaultTargetUrl);
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		handle(request, response, authentication);
		clearAuthenticationAttributes(request);
	}
}
