package com.tv.tutorials.coolestuffvoorcheops.services.impl;
// package com.tv.tutorials.coolestuffvoorcheops.services;
//
// import java.util.Optional;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Primary;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
//
// import com.tv.tutorials.coolestuffvoorcheops.model.CustomUserDetails;
// import com.tv.tutorials.coolestuffvoorcheops.model.User;
// import com.tv.tutorials.coolestuffvoorcheops.reposytories.UserRepository;
//
// @Service
// public class UserService implements UserDetailsService{
//
// @Autowired
// UserRepository userRepository;
// @Override
// public UserDetails loadUserByUsername( String username) throws
// UsernameNotFoundException {
// Optional<User> optionalUsers = userRepository.findByName(username);
//
// optionalUsers.orElseThrow(() -> new UsernameNotFoundException("Username not
// found"));
// return optionalUsers.map(CustomUserDetails::new).get();
// }// Minuut 22 https://www.youtube.com/watch?v=egXtoL5Kg08
//
//
//
//
// }
