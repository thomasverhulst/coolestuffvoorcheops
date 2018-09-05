package com.tv.tutorials.coolestuffvoorcheops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@SpringBootApplication
//@EnableJpaRepositories("com.tv.tutorials.coolestuffvoorcheops.repositories")
//@ComponentScan({"com.tv.tutorials.coolestuffvoorcheops.*"})
//https://www.baeldung.com/spring-security-openid-connect
//@EnableResourceServer // als ik dit aan zet doet hij het niet meer?
// https://dzone.com/articles/build-a-secure-spa-with-spring-boot-and-oauth
public class CoolestuffvoorcheopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoolestuffvoorcheopsApplication.class, args);
	}
	
	
	@Bean
	protected ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {

	   return new ResourceServerConfigurerAdapter() {

	       @Override

	       public void configure(HttpSecurity http) throws Exception {

	           http.authorizeRequests()

	                   .antMatchers("/", "/index.html", "/sign-in-widget-config").permitAll()

	                   .anyRequest().authenticated();

	       }

	   };

	}
}
