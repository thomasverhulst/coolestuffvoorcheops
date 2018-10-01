package com.tv.tutorials.coolestuffvoorcheops.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.models.Skills;

public interface SkillsRepository extends CrudRepository<Skills, Integer> {
	// List<Address> findByStreetNameAndPostalCode(String streetName, String
	// postalCode); {

	// List<Skills> findAllByDotnetGreaterThan(int i);
	// List<Skills> findAllByJavaGreaterThan(int i);

	// List<Skills> findAllByDotnetTrue(boolean isdotnett);

	List<Skills> findAllByDotnet(boolean isdotnett);

	List<Skills> findAllByJava(boolean isJava);

	List<Skills> findAllByFrontend(boolean isFrontend);

	Object findAllByExperienceGreaterThan(int experience);

	// Object findAllByExperienceGreaterThanIn(int experience);

	// List<Skills> findAllByFrontenderGreaterThan(int i);

}