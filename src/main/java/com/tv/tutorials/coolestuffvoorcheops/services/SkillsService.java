package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.ArrayList;
import java.util.List;

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
		//return null;
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

}
