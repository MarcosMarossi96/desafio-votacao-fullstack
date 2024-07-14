package br.com.vote.api.exception;

public class SessionException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SessionException(String message) {
		super(message);
	}	

}
