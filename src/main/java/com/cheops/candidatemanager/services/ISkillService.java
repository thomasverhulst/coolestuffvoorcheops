package com.cheops.candidatemanager.services;

import java.util.List;

import com.cheops.candidatemanager.models.Skills;

public interface ISkillService {

	List<Skills> getAllSkills();

	Skills getSkillsById(int skillsId);

	Skills addSkills(Skills skills);

	void updateSkills(Skills skills);

	void deleteSkills(int skillsId);
}
