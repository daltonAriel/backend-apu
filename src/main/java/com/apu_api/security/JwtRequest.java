package com.apu_api.security;

import lombok.Data;

@Data
public class JwtRequest {

	private String correo;
	
	private String clave;


}
