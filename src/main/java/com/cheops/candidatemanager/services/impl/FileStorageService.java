package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.configuration.FileStorageProperties;
import com.cheops.candidatemanager.exceptions.FileStorageException;
import com.cheops.candidatemanager.exceptions.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class FileStorageService {

  private final Path fileStorageLocationCv;
  private final Path fileStorageLocationFeedback;
  private final String fileExtensions;
  private Locale locale = LocaleContextHolder.getLocale();

  @Autowired
  private MessageSource messageSource;

  @Autowired
  public FileStorageService(FileStorageProperties fileStorageProperties) {
    this.fileStorageLocationCv = Paths.get(fileStorageProperties.getUploadDirCv()).toAbsolutePath().normalize();
    this.fileStorageLocationFeedback = Paths.get(fileStorageProperties.getUploadDirFeedback()).toAbsolutePath().normalize();
    this.fileExtensions = fileStorageProperties.getExtensions();

    try {
      Files.createDirectories(this.fileStorageLocationCv);
      Files.createDirectories(this.fileStorageLocationFeedback);
    } catch (Exception ex) {
      throw new FileStorageException(messageSource.getMessage("form.error.fileDirectory", null, locale));
    }
  }

  public String storeCV(MultipartFile file) {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());

    try {
      if(fileName.contains("..")) {
        throw new FileStorageException(messageSource.getMessage("form.error.fileInvalid", null, locale));
      }

      // Copy file to the target location (adding the current timestamp to have unique files).
      DateFormat dateFormat = new SimpleDateFormat("ddMMyy-hhmmss");
      fileName = dateFormat.format(new Date()) + "_" + fileName;
      Path targetLocation = this.fileStorageLocationCv.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation);
      return fileName;
    } catch (IOException ex) {
      throw new FileStorageException(messageSource.getMessage("form.error.fileNotStored", null, locale) +  fileName);
    }
  }

  public Resource loadCvAsResource(String fileName) {
    try {
      Path filePath = this.fileStorageLocationCv.resolve(fileName).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if(resource.exists()) {
        return resource;
      } else {
        throw new MyFileNotFoundException(messageSource.getMessage("form.error.fileNotFound", null, locale) + fileName);
      }
    } catch (MalformedURLException ex) {
      throw new MyFileNotFoundException(messageSource.getMessage("form.error.fileNotFound", null, locale) + fileName, ex);
    }
  }

  public void deleteCv(String file) {
    try {
      Path filePath = this.fileStorageLocationCv.resolve(file).normalize();
      Resource resource = new UrlResource(filePath.toUri());
      if(resource.exists()) {
        Files.delete(filePath);
      } else {
        throw new MyFileNotFoundException(messageSource.getMessage("form.error.fileNotFound", null, locale) + file);
      }
    } catch (MalformedURLException ex) {
      throw new MyFileNotFoundException(messageSource.getMessage("form.error.fileNotFound", null, locale) + file);
    } catch (IOException ex) {
      throw new FileStorageException(messageSource.getMessage("form.error.fileNotDeleted", null, locale) +  file);
    }
  }

  public void checkFileExtentsion(MultipartFile file) {
    String fileName = file.getOriginalFilename();
    int lastIndex = fileName.lastIndexOf('.');

    if(!fileExtensions.contains(fileName.substring(lastIndex))) {
      throw new FileStorageException(messageSource.getMessage("form.error.fileExtension", null, locale));
    }
  }

}
