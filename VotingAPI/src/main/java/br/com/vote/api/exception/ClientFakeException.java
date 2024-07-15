package br.com.vote.api.exception;

public class ClientFakeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ClientFakeException(String message) {
		super(message);
	}

}
