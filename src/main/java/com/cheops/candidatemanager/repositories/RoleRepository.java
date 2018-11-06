package com.cheops.candidatemanager.repositories;

import com.cheops.candidatemanager.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
  Role findByRole(String role);
}