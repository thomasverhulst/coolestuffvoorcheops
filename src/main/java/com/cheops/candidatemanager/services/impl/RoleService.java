package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.models.Role;
import com.cheops.candidatemanager.repositories.RoleRepository;
import com.cheops.candidatemanager.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {

  @Autowired
  private RoleRepository roleRepository;

  @Override
  public List<Role> getAllRoles() {
    List<Role> roles = new ArrayList<>();
    roleRepository.findAll().forEach(roles::add);
    return roles;
  }

}
