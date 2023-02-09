package com.apu_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundExcep extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundExcep(String message) {
		super(message);
	}
	
	
	public NotFoundExcep() {
		super();
	}

}
