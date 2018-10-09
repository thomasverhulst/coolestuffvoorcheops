package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.models.CandidateSearchResolver;
import com.tv.tutorials.coolestuffvoorcheops.services.ICandidateService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CandidateServiceImplTest {

	private static final String NAME = "Thomas";
	private static final String SURNAME = "Verhulst";
	private static final Integer ID = 1;

	@Autowired
	private ICandidateService candidateService;

	@Test
	public void getAllCandidatesTest() {
		List<CandidateSearchResolver> listCandidates = candidateService.getAllCandidates();
		assertThat(!listCandidates.isEmpty());
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
		Candidate candidate = candidateService.getCandidateById(ID);
		assertThat(candidate != null);
	}

}
