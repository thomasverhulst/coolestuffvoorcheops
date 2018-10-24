package com.cheops.candidatemanager.repositories;

import java.io.Serializable;

import com.cheops.candidatemanager.models.Skills;
import org.springframework.data.repository.Repository;

public interface SkillsRepository2<T, ID extends Serializable> extends Repository<Skills, Integer> {
	// List<Address> findByStreetNameAndPostalCode(String streetName, String
	// postalCode); {

	<S extends T> S save(S entity);

	// T findOne(ID primaryKey);

	Iterable<T> findAll();

	Long count();

	void delete(T entity);

	// boolean exists(ID primaryKey);

}

// interface MyBaseRepository<T, ID extends Serializable> extends Repository<T,
// ID> {
// T findOne(ID id);
// T save(T entity);
// }
