package com.cheops.candidatemanager.services.impl;

import java.util.Optional;

import com.cheops.candidatemanager.models.CustomUserDetails;
import com.cheops.candidatemanager.models.User;
import com.cheops.candidatemanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> optionalUser = userRepository.findByName(username);

		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));
		return optionalUser.map(CustomUserDetails::new).get();

		// optionalUsers.orElseThrow(() -> new UsernameNotFoundException("Username not
		// found"));
		// return optionalUser.map(CustomUserDetails::new).get();
	}// Minuut 22 https://www.youtube.com/watch?v=egXtoL5Kg08

	public void addUser(User temp) {
		// TODO Auto-generated method stub

	}

}
