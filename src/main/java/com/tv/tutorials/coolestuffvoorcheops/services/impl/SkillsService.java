package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.tutorials.coolestuffvoorcheops.models.ApplicationProcess;
import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
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
		// skillsRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Skills getSkillsById(int skillsId) {
		Skills e = skillsRepository.findById(skillsId).get();
		return e;
	}

	@Override
	public Skills addSkills(Skills skills) {
		return (Skills) skillsRepository.save(skills);
	}

	@Override
	public void updateSkills(Skills skills) {
		// TODO Auto-generated method stub
		skillsRepository.save(skills);

		if (getSkillsById(skills.getId()) == null) {
			// skillsRepository.save(skills);
			System.out.println("we zitten vast");
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

			System.out.println("tmp = null");
			skillsRepository.save(skills);
		}
	}

	public List<Integer> findAllDotnet() {

		System.out.println("hooolk");
		// int id=0;
		// List<Skills> list = skillsRepository.findAllByDotnetGreaterThan(id) ;
		boolean isdotnett = true;
		List<Skills> list = skillsRepository.findAllByDotnet(isdotnett);
		System.out.println("lengte lijst " + list.size());
		System.out.println("en we zijn hier");
		// List<Integer> skillId = null;
		List<Integer> skillId = new ArrayList<Integer>();
		for (Skills skills : list) {
			skillId.add(skills.getId());
		}
		return skillId;
	}

	public List<Integer> findAllJava() {
		boolean isJava = true;
		List<Skills> list = skillsRepository.findAllByJava(isJava);
		// refactor optie
		List<Integer> skillId = new ArrayList<Integer>();
		for (Skills skills : list) {
			skillId.add(skills.getId());
		}
		return skillId;
	}

	public List<Integer> findAllFrontend() {

		boolean isFrontend = true;
		List<Skills> list = skillsRepository.findAllByFrontend(isFrontend);

		List<Integer> skillId = new ArrayList<Integer>();
		for (Skills skills : list) {
			skillId.add(skills.getId());
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
				System.out.println("eentje minder");
			}
		}

		List<Skills> skills = new ArrayList<Skills>();
		skills = (List<Skills>) l;
		List<Integer> skillsIdList = l.stream().map(Skills::getId).collect(Collectors.toList());

		System.out.println("skillid lijst" + skillsIdList.size());
		return skillsIdList;
	}

}
