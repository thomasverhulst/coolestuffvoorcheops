package com.cheops.candidatemanager.repositories;

import java.util.Collection;
import java.util.List;

import com.cheops.candidatemanager.models.Skills;
import org.springframework.data.repository.CrudRepository;

public interface SkillsRepository extends CrudRepository<Skills, Integer> {

	List<Skills> findAllByDotnet(boolean isdotnett);

	List<Skills> findAllByJava(boolean isJava);

	List<Skills> findAllByFrontend(boolean isFrontend);

	Collection<? extends Skills> findAllByExperienceGreaterThan(int experience);

	List<Skills> findAllByPreferredLocationContaining(String location);

	Collection<? extends Skills> findAllByExperienceLessThan(int i);

	Collection<? extends Skills> findAllByExperienceGreaterThanAndExperienceLessThan(int i, int j);


}