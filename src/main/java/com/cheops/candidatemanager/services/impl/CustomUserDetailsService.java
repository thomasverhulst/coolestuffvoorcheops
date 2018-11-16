package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.exceptions.UserAlreadyExistException;
import com.cheops.candidatemanager.exceptions.UserDoesNotExistException;
import com.cheops.candidatemanager.models.Role;
import com.cheops.candidatemanager.models.User;
import com.cheops.candidatemanager.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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

  private Locale locale = LocaleContextHolder.getLocale();

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired
  private MessageSource messageSource;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UsernameNotFoundException("Username not found.");
    }

    List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
    return buildUserForAuthentication(user, authorities);
  }

  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(e -> users.add(e));
    return users;
  }

  public User getUserById(int userId) {
    User user = userRepository.findById(userId).get();
    return user;
  }

  public User getUserByUsername(String username) throws UserDoesNotExistException {
    User user = userRepository.findByUsername(username);

    if (user == null) {
      throw new UserDoesNotExistException(messageSource.getMessage("user.doesNotExist", null, locale));
    }

    return user;
  }

  public User addUser(User user) throws UserAlreadyExistException {
    if (userExist(user.getUsername())) {
      throw new UserAlreadyExistException(messageSource.getMessage("user.alreadyExists", new Object[]{user.getUsername()}, locale));
    }

    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    user.setActive(true);
    return userRepository.save(user);
  }

  public void saveUser(User user) throws UserDoesNotExistException {
    if (!userRepository.existsById(user.getId())) {
      throw new UserDoesNotExistException(messageSource.getMessage("user.doesNotExist", null, locale));
    }

    userRepository.save(user);
  }

  public void deleteUser(User user) throws UserDoesNotExistException {
    if (!userRepository.existsById(user.getId())) {
      throw new UserDoesNotExistException(messageSource.getMessage("user.doesNotExist", null, locale));
    }

    userRepository.delete(user);
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
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getActive(), true, true, true, authorities);
  }

  private boolean userExist(final String username) {
    return userRepository.findFirstByUsername(username) != null;
  }

}