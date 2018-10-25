package com.cheops.candidatemanager;

import com.cheops.candidatemanager.services.IStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;

import com.cheops.candidatemanager.configuration.StorageProperties;

@SpringBootApplication
// @EnableJpaRepositories("com.cheops.candidatemanager.repositories")
// @ComponentScan({"com.cheops.candidatemanager.*"})
// https://www.baeldung.com/spring-security-openid-connect
// @EnableResourceServer // als ik dit aan zet doet hij het niet meer?
// https://dzone.com/articles/build-a-secure-spa-with-spring-boot-and-oauth
@EnableConfigurationProperties(StorageProperties.class)
public class CandidateManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CandidateManagerApplication.class, args);
	}

	@Bean
	protected ResourceServerConfigurerAdapter resourceServerConfigurerAdapter() {
		return new ResourceServerConfigurerAdapter() {
			@Override
			public void configure(HttpSecurity http) throws Exception {
				http.authorizeRequests().antMatchers("/", "/index.html", "/sign-in-widget-config").permitAll()
						.anyRequest().authenticated();

			}
		};
	}

	@EnableGlobalMethodSecurity(prePostEnabled = true)
	protected static class GlobalSecurityConfiguration extends GlobalMethodSecurityConfiguration {
		@Override
		protected MethodSecurityExpressionHandler createExpressionHandler() {
			return new OAuth2MethodSecurityExpressionHandler();
		}
	}

	@Bean
	CommandLineRunner init(IStorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}