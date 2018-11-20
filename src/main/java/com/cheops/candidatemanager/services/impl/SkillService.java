package com.cheops.candidatemanager.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.cheops.candidatemanager.models.Skill;
import com.cheops.candidatemanager.repositories.SkillsRepository;
import com.cheops.candidatemanager.services.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService implements ISkillService {

	@Autowired
	private SkillsRepository skillsRepository;

	@Override
	public List<Skill> getAllSkills() {
		List<Skill> list = new ArrayList<>();
		skillsRepository.findAll().forEach(list::add);
		return list;
	}

	@Override
	public Skill getSkillsById(int skillsId) {
		return skillsRepository.findById(skillsId).get();
	}

	@Override
	public Skill addSkills(Skill skill) {
		return skillsRepository.save(skill);
	}

	@Override
	public void updateSkills(Skill skill) {
		skillsRepository.save(skill);
	}

	@Override
	public void deleteSkills(int skillsId) {
		skillsRepository.delete(getSkillsById(skillsId));
	}

	public List<Integer> findAllDotnet() {

		boolean isdotnet = true;
		List<Skill> list = skillsRepository.findAllByDotnet(isdotnet);
		List<Integer> skillId = new ArrayList<Integer>();
		for (Skill skill : list) {
			if (skill.isDotnet()) {
				skillId.add(skill.getId());
			}
		}
		return skillId;
	}

	public List<Integer> findAllJava() {
		boolean isJava = true;
		List<Skill> list = skillsRepository.findAllByJava(isJava);
		// refactor optie
		List<Integer> skillId = new ArrayList<Integer>();
		for (Skill skill : list) {
			if (skill.isJava()) {
				skillId.add(skill.getId());
			}
		}
		return skillId;
	}

	public List<Integer> findAllFrontend() {

		boolean isFrontend = true;
		List<Skill> list = skillsRepository.findAllByFrontend(isFrontend);

		List<Integer> skillId = new ArrayList<Integer>();
		for (Skill skill : list) {
			if (skill.isFrontend()) {
				skillId.add(skill.getId());
			}
		}
		return skillId;
	}

	public List<Integer> findAllByExperienceGreaterThan(int minimumExperience, List<Integer> skillId) {

		ArrayList<Skill> skillList = (ArrayList<Skill>) skillsRepository.findAllById(skillId);
		Iterator<Skill> i = skillList.iterator();
		while (i.hasNext()) {
			Skill s = i.next();
			if (s.getExperience() < minimumExperience) {
				i.remove();
			}
		}

		return skillList.stream().map(Skill::getId).collect(Collectors.toList());

	}

	public List<Integer> findAllByPreferredlocationContaining(List<String> cities) {
		// Set to avoid double entries
		Set<Skill> skillIds = new HashSet <Skill>();
		for (String location : cities) {
			skillIds.addAll(skillsRepository.findAllByPreferredLocationContaining(location));
		}
		System.out.println("skilidlength"+skillIds.size());
		 return  skillIds.stream().map(Skill::getId).collect(Collectors.toList());
		
		//return skillsRepository.findAllById(skillsIds);
		
		//return mainList;
	}

}
