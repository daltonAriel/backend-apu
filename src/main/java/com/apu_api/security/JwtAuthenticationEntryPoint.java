package com.apu_api.security;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
			
		if(authException.getClass() == DisabledException.class) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Forbidden");
		}else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		}
		
		
	}

}
