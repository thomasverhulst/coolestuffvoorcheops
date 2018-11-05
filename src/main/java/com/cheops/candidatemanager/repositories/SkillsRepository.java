package com.cheops.candidatemanager.repositories;

import java.util.List;

import com.cheops.candidatemanager.models.Skills;
import org.springframework.data.repository.CrudRepository;

public interface SkillsRepository extends CrudRepository<Skills, Integer> {
	// List<Address> findByStreetNameAndPostalCode(String streetName, String

	List<Skills> findAllByDotnet(boolean isdotnett);

	List<Skills> findAllByJava(boolean isJava);

	List<Skills> findAllByFrontend(boolean isFrontend);

	Object findAllByExperienceGreaterThan(int experience);

}