package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.List;

import com.tv.tutorials.coolestuffvoorcheops.model.Skills;

public interface ISkillService {

	List<Skills> getAllSkills();

	Skills getSkillsById(int skillsId);

	Skills addSkills(Skills skills);

	void updateSkills(Skills skills);

	void deleteSkills(int skillsId);
}
