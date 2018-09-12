package com.tv.tutorials.coolestuffvoorcheops.reposytories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.model.Skills;

public interface SkillsRepository<T, ID extends Serializable> extends  CrudRepository<Skills, Integer>  {
	//List<Address> findByStreetNameAndPostalCode(String streetName, String postalCode); {

}
