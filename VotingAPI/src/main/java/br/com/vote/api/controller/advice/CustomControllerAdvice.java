package br.com.vote.api.controller.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import br.com.vote.api.exception.AssociateException;
import br.com.vote.api.exception.ResourceNotFoundException;

@ControllerAdvice
@RestController
public class CustomControllerAdvice {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiErrorMessage> handleResourceNotFoundException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ApiErrorMessage em = new ApiErrorMessage(ex.getMessage(), request.getDescription(false), new Date(), String.valueOf(status.value()));
		
		return new ResponseEntity<>(em, status);		
	}
	
	@ExceptionHandler(AssociateException.class)
	public ResponseEntity<ApiErrorMessage> handleAssociateException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ApiErrorMessage em = new ApiErrorMessage(ex.getMessage(), request.getDescription(false), new Date(), String.valueOf(status.value()));
		
		return new ResponseEntity<>(em, status);		
	}

}
