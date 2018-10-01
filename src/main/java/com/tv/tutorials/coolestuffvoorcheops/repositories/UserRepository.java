package com.tv.tutorials.coolestuffvoorcheops.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByName(String name);
}
