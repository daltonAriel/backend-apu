package com.apu_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apu_api.security.JwtRequest;
import com.apu_api.security.JwtUserDetails;
import com.apu_api.security.JwtUserPrincipal;
import com.apu_api.security.JwtUtil;


@RestController 
@RequestMapping("/apu")
@CrossOrigin(origins = "*")
public class JwtAuthenticationController {

	
	
	@Autowired
	private AuthenticationManager authenticationManager;

	
	@Autowired
	private JwtUtil jwtTokenUtil;

	
	@Autowired
	private JwtUserDetails userDetailsService;
	
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		authenticate(authenticationRequest.getCorreo(), authenticationRequest.getClave());

		final JwtUserPrincipal userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getCorreo());
				
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(token);
	}

	@PreAuthorize("hasRole('ROLE_USUARIO')")
	@RequestMapping(value = "/data", method = RequestMethod.POST)
	public ResponseEntity<?> saludar() {

		return ResponseEntity.ok("HOLA A TODOS");
	}
	
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
			
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
