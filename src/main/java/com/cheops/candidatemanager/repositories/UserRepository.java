package com.cheops.candidatemanager.repositories;

import java.util.Optional;

import com.cheops.candidatemanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByName(String name);
}
