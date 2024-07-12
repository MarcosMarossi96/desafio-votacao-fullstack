package br.com.vote.api.exception;

public class VoteException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public VoteException(String message) {
		super(message);
	}

}
