package com.tv.tutorials.coolestuffvoorcheops.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.models.Search;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.AddressService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.ApplicationProcessService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.SalaryPackageService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.SkillsService;

public class SearchControllerTest {

	@InjectMocks
	private SearchController searchController;

	@Mock
	private CandidateService candidateService;

	@Mock
	private AddressService addressService;

	@Mock
	private SkillsService skillService;

	@Mock
	private ApplicationProcessService applicationService;

	@Mock
	private SalaryPackageService salaryPackageService;

	@Mock
	private Model model;

	@Mock
	private ModelMap modelMap;

	@Mock
	private HttpSession httpSession;

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

	@Test
	public void getCandidateByNameAndSirNameTest() {
		Candidate henkie = new Candidate("Henkie", "Penkie", "henkie.penkie@gmail.com", Date.from(Instant.now()),
				"5555", "6666", "", "male");
		candidateService.addCandidate(henkie);

		httpSession.setAttribute("name", "Henkie");
		httpSession.setAttribute("sirName", "Penkie");

		assertThat(searchController.getCandidateByNameAndSirName(modelMap, httpSession))
				.isEqualToIgnoringCase("searchcandidateresult");

	}

}
