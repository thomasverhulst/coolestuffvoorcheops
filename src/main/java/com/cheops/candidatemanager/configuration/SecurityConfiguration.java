package com.cheops.candidatemanager.configuration;

import com.cheops.candidatemanager.services.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Bean
  public CustomUserDetailsService customUserDetailsService() {
    return new CustomUserDetailsService();
  }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    UserDetailsService userDetailsService = customUserDetailsService();
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
      .csrf().disable()
      .authorizeRequests()
      .antMatchers("/admin/**").access("hasAuthority('ADMIN')")
			.anyRequest().authenticated()
      .and()
        .formLogin()
          .loginPage("/login")
          .permitAll()
			    .defaultSuccessUrl("/", true)
      .and()
        .rememberMe()
        .key("uniqueAndSecret")
        //.rememberMeCookieName("cheops-candidatemanger-remember-me")
        .tokenValiditySeconds(604800)
      .and()
      .logout()
        .permitAll()
      .and()
      .exceptionHandling().accessDeniedPage("/403");
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web
      .ignoring()
      .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
  }

}
