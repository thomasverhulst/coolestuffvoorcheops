package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tv.tutorials.coolestuffvoorcheops.models.Skills;
import com.tv.tutorials.coolestuffvoorcheops.repositories.SkillsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SkillsServiceImplTest {

	private final static int testId = 1;
	@Autowired
	SkillsService SkillsService;

	@Autowired
	SkillsRepository skillsRepository;

	@Test
	public void getAllSkillsTest() {
		List<Skills> listSkills = SkillsService.getAllSkills();
		assertThat(!listSkills.isEmpty());
	}

	@Test
	public void getSkillsIdTest() {
		Skills skills = skillsRepository.findById(testId).get();
		assertNotNull(skills);
	}

	@Test
	public void findAllDotnetTest() {
		boolean isdotnet = true;
		List<Skills> listSkills = skillsRepository.findAllByDotnet(isdotnet);
		assertNotNull(listSkills);
	}

	@Test
	public void findAllJavaTest() {
		boolean isJava = true;
		List<Skills> listSkills = skillsRepository.findAllByJava(isJava);
		assertNotNull(listSkills);
	}

	@Test
	public void findAllFriontendTest() {
		boolean isFrontend = true;
		List<Skills> listSkills = skillsRepository.findAllByJava(isFrontend);
		assertNotNull(listSkills);
	}

	// ArrayList<Skills> l = (ArrayList<Skills>)
	// skillsRepository.findAllById(skillId);

}
