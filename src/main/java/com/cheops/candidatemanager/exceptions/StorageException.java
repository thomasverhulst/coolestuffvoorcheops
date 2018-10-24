package com.cheops.candidatemanager.exceptions;

public class StorageException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6739169475216917111L;

	public StorageException(String message) {
		super(message);
	}

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
