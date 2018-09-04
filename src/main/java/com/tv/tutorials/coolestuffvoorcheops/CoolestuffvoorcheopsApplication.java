package com.tv.tutorials.coolestuffvoorcheops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("com.tv.tutorials.coolestuffvoorcheops.repositories")
//@ComponentScan({"com.tv.tutorials.coolestuffvoorcheops.*"})
//https://www.baeldung.com/spring-security-openid-connect
public class CoolestuffvoorcheopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoolestuffvoorcheopsApplication.class, args);
	}
}
