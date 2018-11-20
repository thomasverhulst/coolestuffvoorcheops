package com.cheops.candidatemanager.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.cheops.candidatemanager.models.Skill;
import com.cheops.candidatemanager.repositories.SkillRepository;
import com.cheops.candidatemanager.services.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillService implements ISkillService {

	@Autowired
	private SkillRepository skillRepository;

	@Override
	public List<Skill> getAllSkills() {
		List<Skill> list = new ArrayList<>();
		skillRepository.findAll().forEach(list::add);
		return list;
	}

	@Override
	public Skill getSkillsById(int skillsId) {
		return skillRepository.findById(skillsId).get();
	}

	@Override
	public Skill addSkills(Skill skill) {
		return skillRepository.save(skill);
	}

	@Override
	public void updateSkills(Skill skill) {
		skillRepository.save(skill);
	}

	@Override
	public void deleteSkills(int skillsId) {
		skillRepository.delete(getSkillsById(skillsId));
	}

  @Override
	public List<Integer> findAllDotnet() {
		boolean isdotnet = true;
		List<Skill> list = skillRepository.findAllByDotnet(isdotnet);
		List<Integer> skillId = new ArrayList<Integer>();

		for (Skill skill : list) {
			if (skill.isDotnet()) {
				skillId.add(skill.getId());
			}
		}
		return skillId;
	}

  @Override
	public List<Integer> findAllJava() {
		boolean isJava = true;
		List<Skill> list = skillRepository.findAllByJava(isJava);
		// refactor optie
		List<Integer> skillId = new ArrayList<Integer>();

		for (Skill skill : list) {
			if (skill.isJava()) {
				skillId.add(skill.getId());
			}
		}
		return skillId;
	}

  @Override
	public List<Integer> findAllFrontend() {
		boolean isFrontend = true;
		List<Skill> list = skillRepository.findAllByFrontend(isFrontend);
		List<Integer> skillId = new ArrayList<Integer>();

		for (Skill skill : list) {
			if (skill.isFrontend()) {
				skillId.add(skill.getId());
			}
		}
		return skillId;
	}

  @Override
	public List<Integer> findAllByExperienceGreaterThan(int minimumExperience, List<Integer> skillId) {
		ArrayList<Skill> skillList = (ArrayList<Skill>) skillRepository.findAllById(skillId);
		Iterator<Skill> i = skillList.iterator();

		while (i.hasNext()) {
			Skill s = i.next();
			if (s.getExperience() < minimumExperience) {
				i.remove();
			}
		}

		return skillList.stream().map(Skill::getId).collect(Collectors.toList());
	}

  @Override
	public List<Integer> findAllByPreferredlocationContaining(List<String> cities) {
		// Set to avoid double entries
		Set<Skill> skills = new HashSet <Skill>();

		for (String location : cities) {
			skills.addAll(skillRepository.findAllByPreferredLocationContaining(location));
		}

		return  skills.stream().map(Skill::getId).collect(Collectors.toList());
	}

  @Override
	public List<Integer> findAllByExperienceLessThan(double i) {
		List<Skill> skills = new ArrayList<Skill>();
		skills.addAll(skillRepository.findAllByExperienceLessThan(i));
		return skills.stream().map(Skill::getId).collect(Collectors.toList());
	}

  @Override
	public List<Integer> findAllByExperienceGreaterThanAndExperienceLessThan(double i, double j) {
		List<Skill> skills = new ArrayList<Skill>();
		skills.addAll(skillRepository.findAllByExperienceGreaterThanAndExperienceLessThan(i,  j));
		return skills.stream().map(Skill::getId).collect(Collectors.toList());
	}

  @Override
	public List<Integer> findAllByExperienceGreaterThan(double i) {
		List<Skill> skills = new ArrayList<Skill>();
		skills.addAll(skillRepository.findAllByExperienceGreaterThan(i));
		return skills.stream().map(Skill::getId).collect(Collectors.toList());
	}

  @Override
	public List<Skill> findAllSkillsById(List<Integer> skillsIdList) {
		return skillRepository.findAllByIdIn(skillsIdList);
	}

  @Override
	public Iterable<Skill> findAllSkills() {
		return skillRepository.findAll();
	}

}
