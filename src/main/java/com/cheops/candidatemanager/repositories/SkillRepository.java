package com.cheops.candidatemanager.repositories;

import java.util.Collection;
import java.util.List;

import com.cheops.candidatemanager.models.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill, Integer> {
	List<Skill> findAllByDotnet(boolean isdotnett);
	List<Skill> findAllByJava(boolean isJava);
	List<Skill> findAllByFrontend(boolean isFrontend);
	Collection<? extends Skill> findAllByExperienceGreaterThan(double experience);
	List<Skill> findAllByPreferredLocationContaining(String location);
	Collection<? extends Skill> findAllByExperienceLessThan(double i);
	Collection<? extends Skill> findAllByExperienceGreaterThanAndExperienceLessThan(double i, double j);
	List<Skill> findAllByIdIn(List<Integer> skillsIdList);
}