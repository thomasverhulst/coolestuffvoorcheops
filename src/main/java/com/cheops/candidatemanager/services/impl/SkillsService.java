package com.cheops.candidatemanager.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.cheops.candidatemanager.models.ApplicationProcess;
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

	public List<Integer> findAllByPreferredlocationContaining(List<String> cities) {
		// Set to avoid double entries
		Set<Skills> skills = new HashSet <Skills>();
		for (String location : cities) {
			skills.addAll(skillsRepository.findAllByPreferredLocationContaining(location));
		}
		System.out.println("skilidlength"+skills.size());
		 return  skills.stream().map(Skills::getId).collect(Collectors.toList());
		
	}
						
	public List<Integer> findAllByExperienceLessThan(int i) {
		List<Skills> skills = new ArrayList<Skills>();
		skills.addAll(skillsRepository.findAllByExperienceLessThan(i));
		return skills.stream().map(Skills::getId).collect(Collectors.toList());
	}

	public List<Integer> findAllByExperienceGreaterThanAndExperienceLessThan(int i, int j) {
		List<Skills> skills = new ArrayList<Skills>();
		skills.addAll(skillsRepository.findAllByExperienceGreaterThanAndExperienceLessThan( i,  j));
		return skills.stream().map(Skills::getId).collect(Collectors.toList());
	}

	public List<Integer> findAllByExperienceGreaterThan(int i) {
		List<Skills> skills = new ArrayList<Skills>();
		skills.addAll(skillsRepository.findAllByExperienceGreaterThan( i));
		return skills.stream().map(Skills::getId).collect(Collectors.toList());
	}
	
	
	
//	public List<Integer> findAllByExperienceLessThanIn(int i, List<Integer> skillId) {
//
//		List<Skills> skills = (List<Skills>) skillsRepository.findAllById(skillId);
//		skills.addAll(skillsRepository.findAllByExperienceLessThanAndIn(i,skills));
//		return skills.stream().map(Skills::getId).collect(Collectors.toList());
//		
//		
////		ArrayList<Skills> skillsList = (ArrayList<Skills>) skillsRepository.findAllById(skillId);
////		Iterator<Skills> i = skillsList.iterator();
////		while (i.hasNext()) {
////			Skills s = i.next();
////			if (s.getExperience() < minimumExperience) {
////				i.remove();
////			}
////		}
////
////		return skillsList.stream().map(Skills::getId).collect(Collectors.toList());
//
//	}

	public List<Skills> findAllSkillsById(List<Integer> skillsIdList) {
		return skillsRepository.findAllByIdIn(skillsIdList);
		
	}

	public Iterable<Skills> findAllSkills() {
		return skillsRepository.findAll();
	}

//	public List<Integer> findAllByExperienceLessThanIn(int i, List<Skills> findAllSkillsById) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
