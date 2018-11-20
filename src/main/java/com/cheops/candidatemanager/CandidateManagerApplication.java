package com.cheops.candidatemanager;

import com.cheops.candidatemanager.configuration.FileStorageProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
public class CandidateManagerApplication {

  public static void main(String[] args) {
    SpringApplication.run(CandidateManagerApplication.class, args);
  }

}
