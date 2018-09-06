package com.tv.tutorials.coolestuffvoorcheops.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	    http
	      // ...
	      .headers()
	      	
	        .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
	        
			.frameOptions()
				.sameOrigin()  // x frame dealing with clickjacking
	      // ...
			//	.xssProtection()
			//		.block(false)
		//**.csrf().disable()
			.httpPublicKeyPinning()
		.includeSubDomains(true)
		.reportUri("http://example.net/pkp-report")
		.addSha256Pins("d6qzRu9zOECb90Uez27xWltNsj0e1Md7GkYYkVoZWmM=", "E9CZ9INDbd+2eRQozYqqbQ2yXLVKB9+xcprMF+44U1g=");
	   
	   
	    //https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/
	  }
	
	
	
}
