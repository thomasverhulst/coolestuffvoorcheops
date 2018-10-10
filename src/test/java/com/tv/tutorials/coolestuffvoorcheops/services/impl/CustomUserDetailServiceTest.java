package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomUserDetailServiceTest {

	@Autowired
	private UserDetailsService userDetailsService;

	@Test(expected = UsernameNotFoundException.class)
	public void loadUserByUsernameTest() {
		assertThat(userDetailsService.loadUserByUsername("admin")).isNotNull();

	}

}
