package com.tv.tutorials.coolestuffvoorcheops.reposytories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.model.User;

public interface UserRepository extends  JpaRepository<User, Integer>{
	Optional<User> findByName(String name);
}
