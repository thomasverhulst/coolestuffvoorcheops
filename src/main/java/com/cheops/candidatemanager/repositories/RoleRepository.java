package com.cheops.candidatemanager.repositories;

import com.cheops.candidatemanager.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
  Role findByRole(String role);
}