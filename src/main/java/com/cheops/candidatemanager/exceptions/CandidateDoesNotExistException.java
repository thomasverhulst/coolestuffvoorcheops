package com.cheops.candidatemanager.exceptions;

public class CandidateDoesNotExistException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6739169475216917111L;

	public CandidateDoesNotExistException(String message) {
		super(message);
	}

	public CandidateDoesNotExistException(String message, Throwable cause) {
		super(message, cause);
	}
}
