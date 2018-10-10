package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
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
	SkillsService skillsService;

	@Autowired
	SkillsRepository skillsRepository;

	@Before
	public void fillDb() {

		// Skills
		Skills skillDotNet = new Skills("DotNet", 3, "Brussel", "DotNet", true, false, false);
		Skills skillJava = new Skills("Java", 3, "Brussel", "Java", false, true, false);
		Skills skillFE = new Skills("FrontEnd", 3, "Brussel", "Front-End", false, false, true);
		Skills skillJavaFE = new Skills("Java", 3, "Brussel", "Java", false, true, true);

		skillDotNet = skillsService.addSkills(skillDotNet);
		skillJava = skillsService.addSkills(skillJava);
		skillFE = skillsService.addSkills(skillFE);
		skillJavaFE = skillsService.addSkills(skillJavaFE);

	}

	@After
	public void flushDb() {
		skillsRepository.deleteAll();
	}

	@Test
	public void getAllSkillsTest() {
		List<Skills> listSkills = skillsService.getAllSkills();
		assertThat(!listSkills.isEmpty());
	}

	@Test
	public void getSkillsIdTest() {
		Skills skills = skillsRepository.findById(skillsService.getAllSkills().get(0).getId()).get();
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
