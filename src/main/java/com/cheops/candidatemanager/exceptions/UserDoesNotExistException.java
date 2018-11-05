package com.cheops.candidatemanager.exceptions;

public class UserDoesNotExistException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6739169475216917111L;

	public UserDoesNotExistException(String message) {
		super(message);
	}

	public UserDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
