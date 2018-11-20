package com.cheops.candidatemanager.repositories;

import java.io.Serializable;

import com.cheops.candidatemanager.models.Skill;
import org.springframework.data.repository.Repository;

public interface SkillsRepository2<T, ID extends Serializable> extends Repository<Skill, Integer> {
	// List<Address> findByStreetNameAndPostalCode(String streetName, String

	<S extends T> S save(S entity);


	Iterable<T> findAll();

	Long count();

	void delete(T entity);

}

// interface MyBaseRepository<T, ID extends Serializable> extends Repository<T,

// T findOne(ID id);
// T save(T entity);
// }
