package com.tv.tutorials.coolestuffvoorcheops.reposytories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import com.tv.tutorials.coolestuffvoorcheops.model.Skills;

public interface SkillsRepository extends  CrudRepository<Skills, Integer>  {
	//List<Address> findByStreetNameAndPostalCode(String streetName, String postalCode); {
	
	
}

//interface MyBaseRepository<T, ID extends Serializable> extends Repository<T, ID> {
//	  T findOne(ID id);
//	  T save(T entity);
//	}
