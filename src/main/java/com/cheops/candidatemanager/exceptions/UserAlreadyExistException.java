package com.cheops.candidatemanager.exceptions;

public class UserAlreadyExistException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6739169475216917111L;

	public UserAlreadyExistException(String message) {
		super(message);
	}

	public UserAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
