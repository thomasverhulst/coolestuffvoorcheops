package com.cheops.candidatemanager.exceptions;

public class CountryNotFoundException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6739169475216917111L;

	public CountryNotFoundException(String message) {
		super(message);
	}

	public CountryNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
