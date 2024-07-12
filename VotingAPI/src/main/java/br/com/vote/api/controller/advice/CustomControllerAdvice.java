package br.com.vote.api.controller.advice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import br.com.vote.api.exception.AssociateException;
import br.com.vote.api.exception.ResourceNotFoundException;
import br.com.vote.api.exception.VoteException;

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
	
	@ExceptionHandler(VoteException.class)
	public ResponseEntity<ApiErrorMessage> handleVoteException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ApiErrorMessage em = new ApiErrorMessage(ex.getMessage(), request.getDescription(false), new Date(), String.valueOf(status.value()));
		
		return new ResponseEntity<>(em, status);		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		List<FieldError> allErrors = ex.getFieldErrors();
		List<String> errors = new ArrayList<>();
		
		for (FieldError fieldError : allErrors) {
			errors.add(fieldError.getField() + " " + fieldError.getDefaultMessage());
		}
		
		String message = "Field validation error";
		
		ApiErrorMessage em = new ApiErrorMessage(message, request.getDescription(false), new Date(),
				String.valueOf(status.value()), errors);
		
		return new ResponseEntity<>(em, status);		
	}	

}
