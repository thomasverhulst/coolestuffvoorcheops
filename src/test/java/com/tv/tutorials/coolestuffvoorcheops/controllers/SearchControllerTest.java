package com.tv.tutorials.coolestuffvoorcheops.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.models.Search;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.AddressService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.SalaryPackageService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.SkillsService;

public class SearchControllerTest {

	@InjectMocks
	private SearchController searchController;

	@Mock
	private CandidateService candidateservice;

	@Mock
	private AddressService addressService;

	@Mock
	private SkillsService skillsService;

	@Mock
	private SalaryPackageService salaryPackageService;

	@Mock
	private Model model;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void showRegisterTest() {

		model.addAttribute("candidate", new Candidate());
		model.addAttribute("saerch", new Search());
		assertThat(searchController.showRegister(model)).isEqualToIgnoringCase("search");
	}

}
