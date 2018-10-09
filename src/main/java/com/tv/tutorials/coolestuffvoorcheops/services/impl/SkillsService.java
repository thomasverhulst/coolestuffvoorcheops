package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.tutorials.coolestuffvoorcheops.models.Skills;
import com.tv.tutorials.coolestuffvoorcheops.repositories.SkillsRepository;
import com.tv.tutorials.coolestuffvoorcheops.services.ISkillService;

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
		Skills e = skillsRepository.findById(skillsId).get();
		return e;
	}

	@Override
	public Skills addSkills(Skills skills) {
		return skillsRepository.save(skills);
	}

	@Override
	public void updateSkills(Skills skills) {
		skillsRepository.save(skills);

		if (getSkillsById(skills.getId()) == null) {
		} else {
			skillsRepository.save(skills);
		}
	}

	@Override
	public void deleteSkills(int skillsId) {
		skillsRepository.delete(getSkillsById(skillsId));
	}

	public void saveOrUpdateSkills(int id, @Valid Skills skills) {

		Optional<Skills> tmp = skillsRepository.findById(id);
		if (tmp.isPresent()) {
			Skills s = tmp.get();

			s = skills;
			s.setId(id);
			skillsRepository.save(s);
		} else {
			skillsRepository.save(skills);
		}
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

		ArrayList<Skills> l = (ArrayList<Skills>) skillsRepository.findAllById(skillId);
		Iterator<Skills> i = l.iterator();
		while (i.hasNext()) {
			Skills s = i.next();
			if (s.getExperience() < minimumExperience) {
				i.remove();
			}
		}

		List<Integer> skillsIdList = l.stream().map(Skills::getId).collect(Collectors.toList());

		return skillsIdList;
	}

}
