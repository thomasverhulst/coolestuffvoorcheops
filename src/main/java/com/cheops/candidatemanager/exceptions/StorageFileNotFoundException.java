package com.cheops.candidatemanager.exceptions;

public class StorageFileNotFoundException extends StorageException {
	/**
	 *
	 */
	private static final long serialVersionUID = 5675703464692576653L;

	public StorageFileNotFoundException(String message) {
		super(message);
	}

	public StorageFileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
