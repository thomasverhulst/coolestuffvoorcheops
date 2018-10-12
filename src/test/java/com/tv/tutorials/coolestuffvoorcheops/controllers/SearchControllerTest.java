package com.tv.tutorials.coolestuffvoorcheops.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
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
import com.tv.tutorials.coolestuffvoorcheops.repositories.ApplicationProcessRepository;
import com.tv.tutorials.coolestuffvoorcheops.repositories.CandidateRepository;
import com.tv.tutorials.coolestuffvoorcheops.repositories.SkillsRepository;
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
	private SkillsRepository skillRepository;

	@Mock
	private CandidateRepository candidateRepository;

	@Mock
	private ApplicationProcessRepository applicationProcessRepository;

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

	@Test
	public void gpostCandidateByNameAndSirNameTest() {
		Candidate henkie = new Candidate("Henkie", "Penkie", "henkie.penkie@gmail.com", Date.from(Instant.now()),
				"5555", "6666", "", "male");
		candidateService.addCandidate(henkie);

		httpSession.setAttribute("name", "");
		httpSession.setAttribute("sirName", "");

		assertThat(searchController.gpostCandidateByNameAndSirName("", "", modelMap, httpSession))
				.isEqualToIgnoringCase("searchcandidateresult");
	}

	@Test
	public void searchInAllCandidatesTest() {
		Search search = new Search();

		assertThat(searchController.searchInAllCandidates(search, modelMap, httpSession))
				.isEqualToIgnoringCase("searchcandidateresult");
		;
	}

	@Test
	public void searchInAllCandidatesAllSkillsTest() {
		Search search = new Search(true, true, true, true, 3);

		assertThat(searchController.searchInAllCandidates(search, modelMap, httpSession))
				.isEqualToIgnoringCase("searchcandidateresult");
	}

	@Test
	public void searchAllRecruitedCandidatesTest() {
		assertThat(searchController.searchAllRecruitedCandidates(modelMap, httpSession))
				.isEqualToIgnoringCase("searchcandidateresult");
	}

	@Test
	public void searchAllCandidatesTest() {
		assertThat(searchController.searchAllCandidates(modelMap, httpSession))
				.isEqualToIgnoringCase("searchcandidateresult");
	}

	@Test
	public void searchAllCandidatesWithActiveApplicationProcessTest() {
		assertThat(searchController.searchAllCandidatesWithActiveApplicationProcess(modelMap, httpSession))
				.isEqualToIgnoringCase("searchcandidateresult");
	}

	@Test
	public void searchAllCandidatesWithoutActiveApplicationProcessTest() {
		assertThat(searchController.searchAllCandidatesWithoutActiveApplicationProcess(modelMap, httpSession))
				.isEqualToIgnoringCase("searchcandidateresult");
	}

	@Test
	public void toSearchPageTest() {
		assertThat(searchController.toSearchPage(modelMap, httpSession)).isEqualToIgnoringCase("searchcandidateresult");
	}

	@Test
	public void getEmployeeByNameTest() {
		Candidate henkie = new Candidate("Henkie", "Penkie", "henkie.penkie@gmail.com", Date.from(Instant.now()),
				"5555", "6666", "", "male");
		candidateService.addCandidate(henkie);

		httpSession.setAttribute("name", "Henkie");
		httpSession.setAttribute("sirName", "Penkie");

		assertThat(searchController.getEmployeeByName("Henkie", "Penkie", modelMap))
				.isEqualToIgnoringCase("searchssalarypackage");
	}

	@Test
	public void searchDispatcherTest() {
		assertThat(searchController.searchDispatcher("test")).isEqualToIgnoringCase("searchssalarypackage");
	}

	@Test
	public void searchCandidateDetailsTest() throws IOException {
		Candidate henkie = new Candidate("Henkie", "Penkie", "henkie.penkie@gmail.com", Date.from(Instant.now()),
				"5555", "6666", "", "male");
		henkie.setId(1);
		candidateService.addCandidate(henkie);
		assertThat(searchController.searchCandidateDetails(modelMap, 1)).isEqualToIgnoringCase("updatecandidate");
	}

	@Test
	public void searchCvTest() {
		assertThat(searchController.searchCv(1)).isEqualToIgnoringCase("searchcv");
	}

	@Test
	public void searchApplicationprocesTest() {
		assertThat(searchController.searchApplicationproces("1")).isEqualToIgnoringCase("searchapplicationproces");
	}

	@Test
	public void searchSkillsTest() {
		Candidate henkie = new Candidate("Henkie", "Penkie", "henkie.penkie@gmail.com", Date.from(Instant.now()),
				"5555", "6666", "", "male");
		henkie.setId(1);
		candidateService.addCandidate(henkie);

		assertThat(searchController.searchSkills(model, 1)).isEqualToIgnoringCase("searchskills");
	}
}
