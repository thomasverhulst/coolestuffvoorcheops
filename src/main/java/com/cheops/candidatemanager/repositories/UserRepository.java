package com.cheops.candidatemanager.repositories;

import com.cheops.candidatemanager.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByName(String name);
}
