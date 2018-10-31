package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.models.Role;
import com.cheops.candidatemanager.models.User;
import com.cheops.candidatemanager.repositories.RoleRepository;
import com.cheops.candidatemanager.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);
    System.out.println("user in: " + user);

    if (user != null) {
      List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
      return buildUserForAuthentication(user, authorities);
    } else {
      throw new UsernameNotFoundException("Username not found.");
    }
  }

  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(e -> users.add(e));
    return users;
  }

  public User getUserById(int userId) {
    User u = userRepository.findById(userId).get();
    return u;
  }

  public void saveUser(User user) {
    // Todo: implement see below

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setActive(true);
    userRepository.save(user);

    //Role userRole = roleRepository.findByRole("ADMIN");
//    user.setRoles(new HashSet<>(Arrays.asList(userRole)));
//    userRepository.save(user);
    // https://www.djamware.com/post/5b2f000880aca77b083240b2/spring-boot-security-and-data-mongodb-authentication-example
  }

  private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
    Set<GrantedAuthority> roles = new HashSet<>();
    userRoles.forEach((role) -> {
      roles.add(new SimpleGrantedAuthority(role.getRole()));
    });

    ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
    return grantedAuthorities;
  }

  private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
    System.out.println("user: " + user.getPassword() + " " + user.getUsername());
     return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getActive(), true, true, true, authorities);
  }

}