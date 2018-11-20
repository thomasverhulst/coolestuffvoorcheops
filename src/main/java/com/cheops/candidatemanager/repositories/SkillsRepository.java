package com.cheops.candidatemanager.repositories;

import java.util.List;

import com.cheops.candidatemanager.models.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillsRepository extends CrudRepository<Skill, Integer> {

	List<Skill> findAllByDotnet(boolean isdotnett);

	List<Skill> findAllByJava(boolean isJava);

	List<Skill> findAllByFrontend(boolean isFrontend);

	Object findAllByExperienceGreaterThan(int experience);

}