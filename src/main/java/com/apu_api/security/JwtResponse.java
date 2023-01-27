package com.apu_api.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
	
	
	private String refreshToken;
	
	
	private Object data;
	
	
	
}
