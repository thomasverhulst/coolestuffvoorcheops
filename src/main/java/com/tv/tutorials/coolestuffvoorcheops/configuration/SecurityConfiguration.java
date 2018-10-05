package com.tv.tutorials.coolestuffvoorcheops.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.tv.tutorials.coolestuffvoorcheops.repositories.UserRepository;

@Primary
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("**/secure/**").authenticated().anyRequest().permitAll().and().formLogin()
				.permitAll();
	}

	private PasswordEncoder getPasswordEncoder() {

		return new PasswordEncoder() {
			@Override
			public String encode(CharSequence rawPassword) {
				return rawPassword.toString();
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				return true;
			}

		};
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http
	// // ...
	// .headers()
	//
	// .addHeaderWriter(new
	// StaticHeadersWriter("X-Content-Security-Policy","script-src 'self'"))
	//
	// .frameOptions()
	// .sameOrigin() // x frame dealing with clickjacking
	// // ...
	// // .xssProtection()
	// // .block(false)
	// //**.csrf().disable()
	// .httpPublicKeyPinning()
	// .includeSubDomains(true)
	// .reportUri("http://example.net/pkp-report")
	// .addSha256Pins("d6qzRu9zOECb90Uez27xWltNsj0e1Md7GkYYkVoZWmM=",
	// "E9CZ9INDbd+2eRQozYqqbQ2yXLVKB9+xcprMF+44U1g=");
	//
	//
	// //https://docs.spring.io/spring-security/site/docs/current/reference/htmlsingle/
	// }

}
