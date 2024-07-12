package br.com.vote.api.controller.advice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static Logger logger = LoggerFactory.getLogger(CustomControllerAdvice.class);
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiErrorMessage> handleResourceNotFoundException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ApiErrorMessage em = new ApiErrorMessage(ex.getMessage(), request.getDescription(false), new Date(), String.valueOf(status.value()));
		logger.error(ex.getMessage());
		
		return new ResponseEntity<>(em, status);		
	}
	
	@ExceptionHandler(AssociateException.class)
	public ResponseEntity<ApiErrorMessage> handleAssociateException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ApiErrorMessage em = new ApiErrorMessage(ex.getMessage(), request.getDescription(false), new Date(), String.valueOf(status.value()));
		logger.error(ex.getMessage());
		
		return new ResponseEntity<>(em, status);		
	}
	
	@ExceptionHandler(VoteException.class)
	public ResponseEntity<ApiErrorMessage> handleVoteException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ApiErrorMessage em = new ApiErrorMessage(ex.getMessage(), request.getDescription(false), new Date(), String.valueOf(status.value()));
		logger.error(ex.getMessage());
		
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
		logger.error(message);
		
		return new ResponseEntity<>(em, status);		
	}	

}
