package com.apu_api.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class JwtUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);
	
	@Value("${jwt.ttlMillis}")
    private long ttlMillis;

	@Value("${jwt.secret}")
	private String secret;
	
	
	
	public String generateToken(JwtUserPrincipal authentication) {
		JwtUserPrincipal userPrincipal = authentication;
		
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + ttlMillis);
		Map<String, Object> claims = new HashMap<>();
		claims.put("correo", userPrincipal.getUsername());
		claims.put("nombres", userPrincipal.getNombres());
		claims.put("apellidos", userPrincipal.getApellidos());
		claims.put("roles", userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
		
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public String getUserNameFromJWT(String token) {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();

			return claims.getSubject();
		} catch (SignatureException ex) {
			LOGGER.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			LOGGER.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			LOGGER.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			LOGGER.error("JWT claims string is empty");
		}
		return null;
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			LOGGER.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			LOGGER.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			LOGGER.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			LOGGER.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			LOGGER.error("JWT claims string is empty");
		}
		return false;
	}
	
	
	

}
