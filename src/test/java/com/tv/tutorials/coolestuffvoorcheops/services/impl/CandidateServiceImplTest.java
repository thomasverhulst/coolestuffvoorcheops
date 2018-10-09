package com.tv.tutorials.coolestuffvoorcheops.services.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tv.tutorials.coolestuffvoorcheops.models.CandidateSearchResolver;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CandidateServiceImplTest {

	@Autowired
	private CandidateService candidateService;

	@Test
	public void getAllCandidatesTest() {
		List<CandidateSearchResolver> listCandidates = candidateService.getAllCandidates();
		assertThat(!listCandidates.isEmpty());
	}

}
