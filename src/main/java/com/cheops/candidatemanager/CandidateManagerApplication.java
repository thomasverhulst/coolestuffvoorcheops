package com.cheops.candidatemanager;

import com.cheops.candidatemanager.services.IStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.cheops.candidatemanager.configuration.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class CandidateManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CandidateManagerApplication.class, args);
  }

  @Bean
  CommandLineRunner init(IStorageService storageService) {
    return (args) -> {
      storageService.deleteAll();
      storageService.init();
    };
  }
}
