package com.apu_api.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
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
		
		response.setContentType("application/json");
	    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	    PrintWriter writer = response.getWriter();
	    
	    if (authException instanceof BadCredentialsException) {
	    	writer.write("{\"message\":\"Credenciales no validas\", \"error\":\"UNAUTHORIZED\"}");
	    } else if (authException instanceof DisabledException) {
	    	writer.write("{\"message\":\"Cuenta bloqueada\", \"error\":\"USER_BLOCKED\"}");
	    }
	    
	    writer.flush();
		
	}

}
