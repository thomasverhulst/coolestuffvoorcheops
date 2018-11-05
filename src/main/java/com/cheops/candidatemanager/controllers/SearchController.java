package com.cheops.candidatemanager.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.cheops.candidatemanager.models.CandaidateSearchModel;
import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.models.CandidateSearchResolver;
import com.cheops.candidatemanager.models.SalaryPackage;
import com.cheops.candidatemanager.models.Search;
import com.cheops.candidatemanager.models.Skills;
import com.cheops.candidatemanager.services.impl.AddressService;
import com.cheops.candidatemanager.services.impl.CandidateService;
import com.cheops.candidatemanager.services.impl.SalaryPackageService;
import com.cheops.candidatemanager.services.impl.SkillsService;

@Controller
public class SearchController {

	private static final String uploadDirectory = System.getProperty("user.dir") + "/uploads/";

	// https://www.mkyong.com/spring-boot/spring-boot-hibernate-search-example/
	// https://stackoverflow.com/questions/48800517/how-to-search-multiple-fields-in-a-search-box-in-spring-data-jpa
	@Autowired
	private CandidateService candidateservice;

	@Autowired
	private AddressService addressService;

	@Autowired
	private SkillsService skillsService;

	@Autowired
	private SalaryPackageService salaryPackageService;

	@GetMapping("/search")
	public String showRegister(Model modelMap) {

		// https://stackoverflow.com/questions/13242394/spring-mvc-multiple-modelattribute-on-the-same-form

		modelMap.addAttribute("candidate", new Candidate());
		modelMap.addAttribute("saerch", new Search());
		return "search";
	}

	@RequestMapping(value = "/searchCandidate")
	public String getCandidateByNameAndSirName(ModelMap modelMap, HttpSession session) {
		String name = (String) session.getAttribute("name");
		String sirName = (String) session.getAttribute("sirName");
		List<CandidateSearchResolver> candidates = candidateservice.findAllByNameLikeOrSirNameLike(name, sirName);
		modelMap.addAttribute("candidates", candidates);
		modelMap.addAttribute("candaidatesearchmodel", new CandaidateSearchModel());

		return "searchcandidateresult";
	}

	@PostMapping(value = "/searchCandidate")
	public String gpostCandidateByNameAndSirName(@RequestParam("name") String name,
			@RequestParam("sirName") String sirName, ModelMap modelMap, HttpSession session) {

		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(sirName)) {
			if (name.equals("")) {
				name = "%";
			}

			if (sirName.equals("")) {
				sirName = "%";
			}
		}

		session.setAttribute("name", name);
		session.setAttribute("sirName", sirName);

		List<CandidateSearchResolver> candidates = candidateservice.findAllByNameLikeOrSirNameLike(name, sirName);
		return goToResultpage(modelMap, candidates, session);

	}
	
	@PostMapping(value = "/exEmployees")
	public String searchExEmployee(@ModelAttribute("search") Search search, ModelMap modelMap,
			HttpSession session) {
		List<CandidateSearchResolver> candidates = candidateservice.getAllExEmployees();
		return goToResultpage(modelMap, candidates, session);

	}
	
	@PostMapping(value = "/notRecruited")
	public String searchNotRecruited(@ModelAttribute("search") Search search, ModelMap modelMap,
			HttpSession session) {
		List<CandidateSearchResolver> candidates = candidateservice.getAllNotRecruitedCandidates2();
		return goToResultpage(modelMap, candidates, session);

	}

	@PostMapping(value = "/searchInAllCandidates")
	public String searchInAllCandidates(@ModelAttribute("search") Search search, ModelMap modelMap,
			HttpSession session) {
		List<CandidateSearchResolver> candidates = new ArrayList<CandidateSearchResolver>();
		// moet dit naar een service?
		if (search.isDotnet()) {
			// add .netters if asked
			candidates.addAll(candidateservice.findAllDotnet());
		}
		if (search.isJava()) {
			// add javaers if asked
			candidates.addAll(candidateservice.findAllJava());
		}
		if (search.isFrontender()) {
			// add frontenders if asked
			candidates.addAll(candidateservice.findAllFrontend());
		}
		if (search.isEmployed()) {
			// search only in recruited candiates
			List<Integer> applicationProcessIdList = candidates.stream()
					.map(c -> c.getCandidate().getApplicationProcessId()).collect(Collectors.toList());
			candidates.clear(); // lijst candidaten
			candidates.addAll(candidateservice.findAllRecruitedIn(applicationProcessIdList));
		}
		if (search.getExperience() != 0) {
			// TODO: filter te bouwen
			// moet hier nog 1 worden afgetrokken omdate enkel groter dan gezocht wordt?
			// COMBINATIE GREATER THAN EN in
			List<CandidateSearchResolver> filterdByExperience = candidateservice
					.findByExperienceGreaterThan(search.getExperience(), candidates);
			candidates = filterdByExperience;
		}
		return goToResultpage(modelMap, candidates, session);
	}

	@PostMapping(value = "/searchAllRecruitedCandidates")
	public String searchAllRecruitedCandidates(ModelMap modelMap, HttpSession session) {
		List<CandidateSearchResolver> candidates = candidateservice.findAllRecruited();
		return goToResultpage(modelMap, candidates, session);
	}

	@PostMapping(value = "/searchAllCandidates")
	public String searchAllCandidates(ModelMap modelMap, HttpSession session) {

		List<CandidateSearchResolver> candidates = candidateservice.getAllCandidates();
		return goToResultpage(modelMap, candidates, session);
	}

	@PostMapping(value = "/searchAllCandidatesWithActiveApplicationProcess")
	public String searchAllCandidatesWithActiveApplicationProcess(ModelMap modelMap, HttpSession session) {
		List<CandidateSearchResolver> candidates = candidateservice.getAllCandidatesWithActiveApplicationProcess();
		return goToResultpage(modelMap, candidates, session);
	}

	@PostMapping(value = "/searchAllNotRecruitedCandidates")
	public String searchAllNotRecruitedCandidates(ModelMap modelMap, HttpSession session) {
		List<CandidateSearchResolver> candidates = candidateservice.getAllNotRecruitedCandidates2();
		return goToResultpage(modelMap, candidates, session);
	}

//	@PostMapping(value = "/searchAllExEmployees")
//	public String searchAllExEmployees(ModelMap modelMap, HttpSession session) {
//		List<CandidateSearchResolver> candidates = candidateservice.getAllExEmployees();
//		return goToResultpage(modelMap, candidates, session);
//	}

	
	@PostMapping(value = "/searchAllCandidatesWithoutActiveApplicationProcess")
	public String searchAllCandidatesWithoutActiveApplicationProcess(ModelMap modelMap, HttpSession session) {
		List<CandidateSearchResolver> candidates = candidateservice.getAllCandidatesWithoutActiveApplicationProcess();
		return goToResultpage(modelMap, candidates, session);
		
		//modelMap.addAttribute("candidates", candidates);
		//modelMap.addAttribute("candaidatesearchmodel", new CandaidateSearchModel());
		//return "searchcandidateresult";
	}

	// adding result candidate list to session in order to make "return buttons"
	// possible on update pages
	public String goToResultpage(ModelMap modelMap, List<CandidateSearchResolver> candidates, HttpSession session) {
		// adding results to session for return buttons
		session.setAttribute("candidateResults", candidates);
		// session.getAttribute("candidateResults")
		modelMap.addAttribute("candidates", candidates);
		modelMap.addAttribute("candaidatesearchmodel", new CandaidateSearchModel());
		return "searchcandidateresult";
	}
	//////////////////////////////////////////dftg//searchAllCandidatesWithoutActiveApplicationProcess
	@GetMapping(value = "/toSearchPage")
	public String toSearchPage(ModelMap modelMap, HttpSession session) {

		List<Candidate> candidateList = (List<Candidate>) session.getAttribute("candidateResults");
		modelMap.addAttribute("candidates", candidateList);
		return "searchcandidateresult";
	}

	@PostMapping(value = "/searchssalarypackage")
	public String getEmployeeByName(@RequestParam("name") String name, @RequestParam("sirName") String sirName,
			ModelMap modelMap) {

		// haal candidateid uit sessie, zoek zo id van die candidaat zijn gegegvens,

		// haal "link cv op, zoek cv
		List<CandidateSearchResolver> candidates = candidateservice.findAllByNameLikeOrSirNameLike(name, sirName);
		modelMap.addAttribute("candidates", candidates);
		return "searchssalarypackage";
	}

	@RequestMapping(value = "/dispatcher/{test}", method = RequestMethod.GET)
	public String searchDispatcher(@PathVariable("test") String test) {
		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		return "searchssalarypackage";
	}

	@RequestMapping(value = "searchcandidatedetails/{candidateId}", method = RequestMethod.GET)
	public String searchCandidateDetails(ModelMap map, @PathVariable("candidateId") int candidateId)
			throws IOException {

		map.addAttribute("candidate", candidateservice.getCandidateById(candidateId));

		map.addAttribute("address",
				addressService.getAddressById(candidateservice.getCandidateById(candidateId).getAddressId()));

		return "updatecandidate";
	}

	@RequestMapping(value = "searchcv/{candidateId}", method = RequestMethod.GET)
	public String searchCv(@PathVariable("candidateId") int candidateId) {
		return "searchcv";
	}

	@RequestMapping(value = "searchskills/{candidateId}", method = RequestMethod.GET)
	public String searchSkills(Model model, @PathVariable("candidateId") int candidateId) {
		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed

		Candidate tmpCandidate = candidateservice.getCandidateById(candidateId);
		Skills tmpSkills = skillsService.getSkillsById(tmpCandidate.getSkillsId());

		model.addAttribute("skills", tmpSkills);
		return "searchskills";
	}

	@RequestMapping(value = "searchcurrentsalarypackage/{candidateId}", method = RequestMethod.GET)
	public String searchCurrentSalarypackage(Model model, @PathVariable("candidateId") int candidateId) {
		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		Candidate candidate = candidateservice.getCandidateById(candidateId);
		// huidige loonpakket!
		if (candidate.getCurrentSallaryPackageId() < 0) {
			SalaryPackage tmpSalarypackage = salaryPackageService
					.getSalaryPackageById(candidate.getCurrentSallaryPackageId());
			model.addAttribute("currentSalaryPackage", tmpSalarypackage);

		} else {
			model.addAttribute("msg",
					"<a href=\"/salarypackage\">Google.com</a> van deze kandidaat is nog geen huidig loonpakket ingevuld, wil u dat doen?");

		}

		return "searchssalarypackage";
	}

	@RequestMapping(value = "searchproposedsalarypackage/{candidateId}", method = RequestMethod.GET)
	public String searchProposedSalarypackage(Model model, @PathVariable("candidateId") int candidateId) {
		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		Candidate candidate = candidateservice.getCandidateById(candidateId);
		// voorgestelde loonpakket!
		SalaryPackage tmpSalarypackage = salaryPackageService
				.getSalaryPackageById(candidate.getProposedSallaryPackageId());
		model.addAttribute("proposedSalaryPackage", tmpSalarypackage);
		return "searchssalarypackage";
	}

	@RequestMapping(value = "searchapplicationproces/{candidateId}", method = RequestMethod.GET)
	public String searchApplicationproces(@PathVariable("candidateId") String candidateId) {
		return "searchapplicationproces";
	}

}
