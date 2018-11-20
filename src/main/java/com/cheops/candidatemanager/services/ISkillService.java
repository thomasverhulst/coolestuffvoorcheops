package com.cheops.candidatemanager.services;

import java.util.List;

import com.cheops.candidatemanager.models.Skill;

public interface ISkillService {

	List<Skill> getAllSkills();

	Skill getSkillsById(int skillsId);

	Skill addSkills(Skill skill);

	void updateSkills(Skill skill);

	void deleteSkills(int skillsId);

	List<Integer> findAllDotnet();

	List<Integer> findAllJava();

	List<Integer> findAllFrontend();

	List<Integer> findAllByExperienceGreaterThan(int minimumExperience, List<Integer> skillId);

	List<Integer> findAllByPreferredlocationContaining(List<String> cities);

	List<Integer> findAllByExperienceLessThan(double i);

	List<Integer> findAllByExperienceGreaterThanAndExperienceLessThan(double i, double j);

	List<Integer> findAllByExperienceGreaterThan(double i);

	List<Skill> findAllSkillsById(List<Integer> skillsIdList);

	Iterable<Skill> findAllSkills();

}
