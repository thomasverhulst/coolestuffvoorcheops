package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tv.tutorials.coolestuffvoorcheops.models.ApplicationProcess;
import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.models.CandidateSearchResolver;
import com.tv.tutorials.coolestuffvoorcheops.models.Skills;
import com.tv.tutorials.coolestuffvoorcheops.services.IApplicationProcessService;
import com.tv.tutorials.coolestuffvoorcheops.services.ICandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.ISkillService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CandidateServiceImplTest {

	private static final String NAME = "Thomas";
	private static final String SURNAME = "Verhulst";
	private static final Integer CANDIDATEID = 1;
	private static final List<Integer> APPLICATIONPROCESSID = Arrays.asList(new Integer[] { 1, 4, 13, 15 });
	private static final Integer EXPERIENCE = 3;

	@Autowired
	private ICandidateService candidateService;
	@Autowired
	private ISkillService skillService;
	@Autowired
	private IApplicationProcessService applicationService;

	@Test
	public void getAllCandidatesTest() {
		List<CandidateSearchResolver> listCandidates = candidateService.getAllCandidates();
		assertThat(listCandidates).isNotEmpty();
	}

	@Test
	public void findAllByNameLikeOrSirNameLikeTestFilledNameAndSurname() {
		List<CandidateSearchResolver> listCandidates = candidateService.findAllByNameLikeOrSirNameLike(NAME, SURNAME);
		assertThat(listCandidates.size() >= 1);
	}

	@Test
	public void findAllByNameLikeOrSirNameLikeTestWithoutNameAndSurname() {
		List<CandidateSearchResolver> listCandidates = candidateService.findAllByNameLikeOrSirNameLike("", "");
		assertThat(listCandidates.size() >= 1);
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
		List<CandidateSearchResolver> candidateList = candidateService.findAllRecruitedIn(APPLICATIONPROCESSID);
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

	public void findByExperienceGreaterThanTest() {
		List<CandidateSearchResolver> candidates = candidateService.getAllCandidates();
		candidates = candidateService.findByExperienceGreaterThan(EXPERIENCE, candidates);
		assertThat(candidates).isNotEmpty();
	}

}
