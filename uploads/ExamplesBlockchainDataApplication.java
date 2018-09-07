package io.examples.blockchain;


// uitler upload https://spring.io/guides/gs/uploading-files/
import javax.servlet.ServletException;

import org.apache.catalina.servlet4preview.ServletContext;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import io.examples.blockchain.config.StorageProperties;
import io.examples.blockchain.services.IStorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class ExamplesBlockchainDataApplication extends SpringBootServletInitializer implements ServletContextInitializer{

	public static void main(String[] args) {
		SpringApplication.run(ExamplesBlockchainDataApplication.class, args);
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(examplesBlockchainDataApplication);
	    }

	    private static Class<ExamplesBlockchainDataApplication> examplesBlockchainDataApplication = ExamplesBlockchainDataApplication.class;
	
//uit seositecheckup rapport Congratulations! Your server has disabled directory browsing.
	// omgekeerde van wat hier gebeurt https://stackoverflow.com/questions/35004870/embedded-tomcat-directory-listing-for-spring-boot-application
	@Override
	public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
		// TODO Auto-generated method stub
		 servletContext.setInitParameter("listings", "false");
	}
	
	@Bean
    CommandLineRunner init(IStorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
