package com.cheops.candidatemanager.exceptions;

public class FileStorageException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6739169475216917111L;

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
