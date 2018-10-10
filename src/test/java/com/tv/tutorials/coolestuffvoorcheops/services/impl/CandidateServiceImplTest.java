package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tv.tutorials.coolestuffvoorcheops.models.ApplicationProcess;
import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.models.CandidateSearchResolver;
import com.tv.tutorials.coolestuffvoorcheops.models.Skills;
import com.tv.tutorials.coolestuffvoorcheops.repositories.ApplicationProcessRepository;
import com.tv.tutorials.coolestuffvoorcheops.repositories.CandidateRepository;
import com.tv.tutorials.coolestuffvoorcheops.repositories.SkillsRepository;
import com.tv.tutorials.coolestuffvoorcheops.services.IApplicationProcessService;
import com.tv.tutorials.coolestuffvoorcheops.services.ICandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.ISkillService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateServiceImplTest {

	private static final String NAME = "Thomas";
	private static final String SURNAME = "Verhulst";
	private static final Integer CANDIDATEID = 1;
	private static final Integer EXPERIENCE = 3;

	@Autowired
	private ICandidateService candidateService;
	@Autowired
	private ISkillService skillService;
	@Autowired
	private IApplicationProcessService applicationService;

	@Autowired
	private CandidateRepository candidateRepo;
	@Autowired
	private SkillsRepository skillRepo;
	@Autowired
	private ApplicationProcessRepository applicationProcessRepo;

	@Before
	public void fillDb() {

		// Candidates
		Candidate henkie = new Candidate("Henkie", "Penkie", "henkie.penkie@gmail.com", Date.from(Instant.now()),
				"5555", "6666", "", "male");
		Candidate yannick = new Candidate("Yannick", "Pire", "y.p@gmail.com", Date.from(Instant.now()), "5555", "6666",
				"", "male");
		Candidate thomas = new Candidate("Thomas", "Verhulst", "t.v@gmail.com", Date.from(Instant.now()), "5555",
				"6666", "", "male");
		Candidate vvv = new Candidate("vvv", "vvv", "v.v@gmail.com", Date.from(Instant.now()), "5555", "6666", "",
				"male");

		// Skills
		Skills skillDotNet = new Skills("DotNet", 3, "Brussel", "DotNet", true, false, false);
		Skills skillJava = new Skills("Java", 3, "Brussel", "Java", false, true, false);
		Skills skillFE = new Skills("FrontEnd", 3, "Brussel", "Front-End", false, false, true);
		Skills skillJavaFE = new Skills("Java", 3, "Brussel", "Java", false, true, true);

		// ApplicationProcess
		ApplicationProcess appProcessHenkie = new ApplicationProcess(Date.from(Instant.now()), true,
				Date.from(Instant.now()), null, "Alain", "Goed", true, Date.from(Instant.now()), "", "", false, null,
				"", false);
		ApplicationProcess appProcessYannick = new ApplicationProcess(Date.from(Instant.now()), true,
				Date.from(Instant.now()), null, "Alain", "Goed", true, Date.from(Instant.now()), "", "", true, null, "",
				true);

		ApplicationProcess appProcessVvv = new ApplicationProcess(null, false, null, null, "Alain", "Goed", false, null,
				"", "", false, null, "", true);

		candidateService.addCandidate(henkie);
		candidateService.addCandidate(yannick);
		candidateService.addCandidate(thomas);
		candidateService.addCandidate(vvv);

		skillDotNet = skillService.addSkills(skillDotNet);
		skillJava = skillService.addSkills(skillJava);
		skillFE = skillService.addSkills(skillFE);
		skillJavaFE = skillService.addSkills(skillJavaFE);

		appProcessHenkie = applicationService.addApplicationProcess(appProcessHenkie);
		appProcessYannick = applicationService.addApplicationProcess(appProcessYannick);
		appProcessVvv = applicationService.addApplicationProcess(appProcessVvv);

		henkie.setSkillsId(skillDotNet.getId());
		henkie.setApplicationProcessId(appProcessHenkie.getId());
		candidateService.updateCandidate(henkie);

		yannick.setSkillsId(skillJava.getId());
		yannick.setApplicationProcessId(appProcessYannick.getId());
		candidateService.updateCandidate(yannick);

		thomas.setSkillsId(skillFE.getId());
		candidateService.updateCandidate(thomas);

		vvv.setSkillsId(skillJavaFE.getId());
		vvv.setApplicationProcessId(appProcessVvv.getId());
		candidateService.updateCandidate(vvv);
	}

	@After
	public void flushDb() {
		candidateRepo.deleteAll();
		skillRepo.deleteAll();
		applicationProcessRepo.deleteAll();
	}

	@Test
	public void getAllCandidatesTest() {
		List<CandidateSearchResolver> listCandidates = candidateService.getAllCandidates();
		assertThat(listCandidates).hasSize(4);
	}

	@Test
	public void findAllByNameLikeOrSirNameLikeTestFilledNameAndSurname() {
		List<CandidateSearchResolver> listCandidates = candidateService.findAllByNameLikeOrSirNameLike(NAME, SURNAME);
		assertThat(listCandidates).hasSize(1);
	}

	@Test
	public void getCandidateByIdTest() {
		Candidate candidate = candidateService.getCandidateById(CANDIDATEID);
		assertThat(candidate).isNotNull();
	}

	@Test
	public void findAllDotnetTest() {
		List<CandidateSearchResolver> candidateList = candidateService.findAllDotnet();
		assertThat(candidateList).isNotEmpty();

		Candidate candidate = candidateList.get(0).getCandidate();
		assertNotNull(candidate);

		Skills skill = skillService.getSkillsById(candidate.getSkillsId());
		assertNotNull(skill);
		assertThat(skill.isDotnet());

	}

	@Test
	public void findAllJavaTest() {
		List<CandidateSearchResolver> candidateList = candidateService.findAllJava();
		assertThat(candidateList).isNotEmpty();

		Candidate candidate = candidateList.get(0).getCandidate();
		assertNotNull(candidate);

		Skills skill = skillService.getSkillsById(candidate.getSkillsId());
		assertNotNull(skill);
		assertThat(skill.isJava());
	}

	@Test
	public void findAllFrontEndTest() {
		List<CandidateSearchResolver> candidateList = candidateService.findAllFrontend();
		assertThat(candidateList).isNotEmpty();

		Candidate candidate = candidateList.get(0).getCandidate();
		assertNotNull(candidate);

		Skills skill = skillService.getSkillsById(candidate.getSkillsId());
		assertNotNull(skill);
		assertThat(skill.isFrontend());
	}

	@Test
	public void findAllRecruitedTest() {
		List<CandidateSearchResolver> candidateList = candidateService.findAllRecruited();
		assertThat(candidateList).isNotEmpty();

		Candidate candidate = candidateList.get(0).getCandidate();
		assertNotNull(candidate);

		ApplicationProcess appProcess = applicationService
				.getApplicationProcessById(candidate.getApplicationProcessId());
		assertNotNull(appProcess);
		assertNotNull(appProcess.getIsRecruited());
	}

	@Test
	public void findAllRecruitedInTest() {

		List<Integer> applicationProcessId = applicationService.getAllApplicationProcesses().stream()
				.map(ApplicationProcess::getId).collect(Collectors.toList());

		List<CandidateSearchResolver> candidateList = candidateService.findAllRecruitedIn(applicationProcessId);
		assertThat(candidateList).isNotEmpty();

		for (CandidateSearchResolver candidateSearchResolver : candidateList) {
			Candidate candidate = candidateSearchResolver.getCandidate();
			assertNotNull(candidate);

			ApplicationProcess appProcess = applicationService
					.getApplicationProcessById(candidate.getApplicationProcessId());
			assertNotNull(appProcess);
			assertNotNull(appProcess.getIsRecruited());
		}

	}

	@Test
	public void getAllCandidatesWithActiveApplicationProcessTest() {
		List<CandidateSearchResolver> candidateList = candidateService.getAllCandidatesWithActiveApplicationProcess();
		for (CandidateSearchResolver candidateSR : candidateList) {
			Candidate candidate = candidateSR.getCandidate();
			ApplicationProcess appProcess = applicationService
					.getApplicationProcessById(candidate.getApplicationProcessId());
			assertFalse(appProcess.getIsRecruited());
			assertTrue(appProcess.getToBeInvitedForFirstConversation());
		}
	}

	@Test
	public void getAllCandidatesWithoutActiveApplicationProcessTest() {
		List<Candidate> candidateList = candidateService.getAllCandidatesWithoutActiveApplicationProcess();
		for (Candidate candidate : candidateList) {
			ApplicationProcess appProcess = applicationService
					.getApplicationProcessById(candidate.getApplicationProcessId());
			assertFalse(appProcess.getToBeInvitedForFirstConversation());
		}
	}

	@Test
	public void findByExperienceGreaterThanTest() {
		List<CandidateSearchResolver> candidates = candidateService.getAllCandidates();
		candidates = candidateService.findByExperienceGreaterThan(EXPERIENCE, candidates);
		assertThat(candidates).isNotEmpty();
	}

	@Test
	public void addCandidateTest() {
		Integer count = candidateService.getAllCandidates().size();
		Candidate candidate = new Candidate("Jan", "Janssen", "Jan.Janssen@gmail.com", Date.from(Instant.now()), "5555",
				"6666", "", "male");

		candidateService.addCandidate(candidate);

		assertThat(candidateService.getAllCandidates().size()).isEqualTo(++count);

	}

	@Test
	public void deleteCandidateTest() {
		Integer count = candidateService.getAllCandidates().size();
		Candidate candidate = new Candidate("Jan", "Janssen", "Jan.Janssen@gmail.com", Date.from(Instant.now()), "5555",
				"6666", "", "male");

		candidate = candidateService.addCandidate(candidate);
		candidateService.deleteCandidate(candidate.getId());
		assertThat(candidateService.getAllCandidates().size()).isEqualTo(count);
	}

	@Test
	public void updateCandidateTest() {
		Candidate candidate = candidateService.findAllByNameLikeOrSirNameLike("Yannick", "Pire").get(0).getCandidate();
		candidate.setName("fjdqsklfjsdkljfqsdkl");
		candidateService.updateCandidate(candidate);
		assertThat(candidateService.findAllByNameLikeOrSirNameLike("Yannick", "Pire").get(0).getCandidate().getName())
				.isEqualToIgnoringNewLines("fjdqsklfjsdkljfqsdkl");
	}

}
