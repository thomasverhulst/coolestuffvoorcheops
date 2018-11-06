package com.cheops.candidatemanager.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import com.cheops.candidatemanager.models.Skills;
import com.cheops.candidatemanager.repositories.SkillsRepository;
import com.cheops.candidatemanager.services.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillsService implements ISkillService {
	@Autowired
	private SkillsRepository skillsRepository;

	@Override
	public List<Skills> getAllSkills() {
		List<Skills> list = new ArrayList<>();
		skillsRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Skills getSkillsById(int skillsId) {
		return skillsRepository.findById(skillsId).get();
	}

	@Override
	public Skills addSkills(Skills skills) {
		return skillsRepository.save(skills);
	}

	@Override
	public void updateSkills(Skills skills) {
		skillsRepository.save(skills);
	}

	@Override
	public void deleteSkills(int skillsId) {
		skillsRepository.delete(getSkillsById(skillsId));
	}

	public List<Integer> findAllDotnet() {

		boolean isdotnet = true;
		List<Skills> list = skillsRepository.findAllByDotnet(isdotnet);
		List<Integer> skillId = new ArrayList<Integer>();
		for (Skills skills : list) {
			if (skills.isDotnet()) {
				skillId.add(skills.getId());
			}
		}
		return skillId;
	}

	public List<Integer> findAllJava() {
		boolean isJava = true;
		List<Skills> list = skillsRepository.findAllByJava(isJava);
		// refactor optie
		List<Integer> skillId = new ArrayList<Integer>();
		for (Skills skills : list) {
			if (skills.isJava()) {
				skillId.add(skills.getId());
			}
		}
		return skillId;
	}

	public List<Integer> findAllFrontend() {

		boolean isFrontend = true;
		List<Skills> list = skillsRepository.findAllByFrontend(isFrontend);

		List<Integer> skillId = new ArrayList<Integer>();
		for (Skills skills : list) {
			if (skills.isFrontend()) {
				skillId.add(skills.getId());
			}
		}
		return skillId;
	}

	public List<Integer> findAllByExperienceGreaterThan(int minimumExperience, List<Integer> skillId) {

		ArrayList<Skills> skillsList = (ArrayList<Skills>) skillsRepository.findAllById(skillId);
		Iterator<Skills> i = skillsList.iterator();
		while (i.hasNext()) {
			Skills s = i.next();
			if (s.getExperience() < minimumExperience) {
				i.remove();
			}
		}

		return skillsList.stream().map(Skills::getId).collect(Collectors.toList());

	}

}
