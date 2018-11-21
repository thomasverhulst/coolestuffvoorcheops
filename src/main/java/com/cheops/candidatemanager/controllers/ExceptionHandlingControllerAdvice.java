package com.cheops.candidatemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@ControllerAdvice
public class ExceptionHandlingControllerAdvice {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(value = MaxUploadSizeExceededException.class)
  public View maxUploadSizeExceededExceptionHandler(Locale locale, HttpServletRequest request, MaxUploadSizeExceededException exception) {
    RedirectView rv = new RedirectView(request.getRequestURL().toString());
    FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);

    if (outputFlashMap != null)
      outputFlashMap.put("errorMessage", messageSource.getMessage("form.error.fileSize", null, locale));

    return rv;
  }

}
