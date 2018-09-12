package com.tv.tutorials.coolestuffvoorcheops.reposytories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.tv.tutorials.coolestuffvoorcheops.model.Skills;

public interface SkillsRepository2<T, ID extends Serializable> extends  Repository<Skills, Integer>  {
	//List<Address> findByStreetNameAndPostalCode(String streetName, String postalCode); {
	
	<S extends T> S save(S entity);
  
//T findOne(ID primaryKey);
   
Iterable<T> findAll();

Long count();

void delete(T entity);
   
//boolean exists(ID primaryKey);

	
}

//interface MyBaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
//	  T findOne(ID id);
//	  T save(T entity);
//	}
