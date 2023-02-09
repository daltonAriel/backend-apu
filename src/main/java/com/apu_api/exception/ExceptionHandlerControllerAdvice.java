package com.apu_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	
	
	@ExceptionHandler(ForbiddenExcep.class)
	public ResponseEntity<String> handleForbiddenException(ForbiddenExcep ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
	}
	
	
	@ExceptionHandler(InternalErrorExcep.class)
	public ResponseEntity<String> handleForbiddenException(InternalErrorExcep ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(NotAllowedExcep.class)
	public ResponseEntity<String> handleForbiddenException(NotAllowedExcep ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	
	@ExceptionHandler(NotFoundExcep.class)
	public ResponseEntity<String> handleForbiddenException(NotFoundExcep ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(UnauthorizedExcep.class)
	public ResponseEntity<String> handleForbiddenException(UnauthorizedExcep ex) {
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	
	
	
}
