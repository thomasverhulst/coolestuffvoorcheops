package com.cheops.candidatemanager.services;

import java.util.List;

import com.cheops.candidatemanager.models.Skill;

public interface ISkillService {

	List<Skill> getAllSkills();

	Skill getSkillsById(int skillsId);

	Skill addSkills(Skill skill);

	void updateSkills(Skill skill);

	void deleteSkills(int skillsId);
}
