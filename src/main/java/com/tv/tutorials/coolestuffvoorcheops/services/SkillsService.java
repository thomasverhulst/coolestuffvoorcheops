package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.tutorials.coolestuffvoorcheops.model.Skills;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.SkillsRepository;

@Service
public class SkillsService implements ISkillService {
	@Autowired
	private SkillsRepository skillsRepository;

	@Override
	public List<Skills> getAllSkills() {
		List<Skills> list = new ArrayList<>();
		//skillsRepository.findAll().forEach(e -> list.add(e));
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
		 
		 if (getSkillsById(skills.getId())==null) {
			 //skillsRepository.save(skills);
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
		if (tmp.isPresent() ) {
			Skills s =tmp.get();
			
			s= skills;
			s.setId(id);
			skillsRepository.save(s);
		}
		else {
			
			System.out.println("tmp = null");
			skillsRepository.save(skills);
		}
	}
	
	public List<Integer>  findAllDotnet() {
		
		System.out.println("hooolk");
		//int id=0;
		//List<Skills> list =  skillsRepository.findAllByDotnetGreaterThan(id) ;
		boolean isdotnett = true;
		List<Skills> list =  skillsRepository.findAllByDotnet(isdotnett);
		System.out.println("lengte lijst "+list.size());
		System.out.println("en we zijn hier");
		//List<Integer>  skillId = null;
		List<Integer> skillId = new ArrayList<Integer>();
		for (Skills skills : list) {
			skillId.add(skills.getId());
		}
		return skillId;
	}

	public List<Integer> findAllJava() {
		boolean isJava = true;
		List<Skills> list =  skillsRepository.findAllByJava(isJava);
		// refactor optie
		List<Integer> skillId = new ArrayList<Integer>();
		for (Skills skills : list) {
			skillId.add(skills.getId());
		}
		return skillId;
	}

	public List<Integer> findAllFrontend() {
		
		boolean isFrontend = true;
		List<Skills> list =  skillsRepository.findAllByFrontend(isFrontend);
		
		List<Integer> skillId = new ArrayList<Integer>();
		for (Skills skills : list) {
			skillId.add(skills.getId());
		}
		return skillId;
	}

}
