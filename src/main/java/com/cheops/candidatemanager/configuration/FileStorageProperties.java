package com.cheops.candidatemanager.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

  private String uploadDirCv;
  private String uploadDirFeedback;
  private String extensions;

  public String getUploadDirCv() {
    return uploadDirCv;
  }

  public void setUploadDirCv(String uploadDirCv) {
    this.uploadDirCv = uploadDirCv;
  }

  public String getUploadDirFeedback() {
    return uploadDirFeedback;
  }

  public void setUploadDirFeedback(String uploadDirFeedback) {
    this.uploadDirFeedback = uploadDirFeedback;
  }

  public String getExtensions() {
    return extensions;
  }

  public void setExtensions(String extensions) {
    this.extensions = extensions;
  }

}
