package com.apu_api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	
	
	
	/*
	 * 4.0 JwtRequestFilter amplía la clase OncePerRequestFilter de Spring Web Filter. Para cualquier solicitud entrante,
	 * esta clase de filtro se ejecuta. Comprueba si la solicitud tiene un token JWT válido. Si tiene un token JWT válido,
	 * establece la autenticación en el contexto para especificar que el usuario actual está autenticado.
	 */

	
	@Autowired
	private JwtUserDetails jwtUserDetailsService;

	
	@Autowired
	private JwtUtil jwtTokenUtil;

	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		
		if (requestTokenHeader != null ) {
			jwtToken = requestTokenHeader;
			username = jwtTokenUtil.getUserNameFromJWT(jwtToken);
		}
		
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			JwtUserPrincipal userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
				
			if (jwtTokenUtil.validateToken(jwtToken) && userDetails.isEnabled()) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		chain.doFilter(request, response);
	}
	
	
	
	
	
	
	
	
	
}
