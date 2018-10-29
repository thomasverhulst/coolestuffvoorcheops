package com.cheops.candidatemanager;

import com.cheops.candidatemanager.services.IStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.cheops.candidatemanager.configuration.StorageProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.cheops.candidatemanager.repositories", "com.cheops.candidatemanager.services", "com.cheops.candidatemanager.configuration", "com.cheops.candidatemanager.controllers"})
@EntityScan("com.cheops.candidatemanager.models")
@EnableJpaRepositories("com.cheops.candidatemanager.repositories")
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
