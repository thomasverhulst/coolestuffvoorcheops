package com.cheops.candidatemanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = -6739169475216917111L;

  public MyFileNotFoundException(String message) {
    super(message);
  }

  public MyFileNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
