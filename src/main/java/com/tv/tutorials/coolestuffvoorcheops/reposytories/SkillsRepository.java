package com.tv.tutorials.coolestuffvoorcheops.reposytories;

import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.model.Skills;

public interface SkillsRepository extends  CrudRepository<Skills, Integer>  {
	//List<Address> findByStreetNameAndPostalCode(String streetName, String postalCode); {

}
