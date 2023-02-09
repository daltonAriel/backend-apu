package com.apu_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class ForbiddenExcep extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ForbiddenExcep(String message) {
		super(message);
	}


	public ForbiddenExcep() {
		super();
	}
	
	
}
