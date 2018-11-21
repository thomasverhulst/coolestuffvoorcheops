package com.cheops.candidatemanager.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {

  String storeCV(MultipartFile file);

  Resource loadCvAsResource(String fileName);

  void deleteCv(String file);

  void checkFileExtentsion(MultipartFile file);

}
