package com.cheops.candidatemanager.repositories;

import com.cheops.candidatemanager.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);
	User findFirstByUsername(String username);
}
