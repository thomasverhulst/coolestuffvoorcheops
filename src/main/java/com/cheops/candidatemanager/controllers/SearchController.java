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
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.cheops.candidatemanager.models.CandaidateSearchModel;
import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.models.CandidateSearchResolver;
import com.cheops.candidatemanager.models.Meeting;
import com.cheops.candidatemanager.models.NewCandidate;
import com.cheops.candidatemanager.models.SalaryPackage;
import com.cheops.candidatemanager.models.Search;
import com.cheops.candidatemanager.models.Skills;
import com.cheops.candidatemanager.pojos.CitieListWrapper;
import com.cheops.candidatemanager.pojos.ExperienceWrapper;
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
	public String showRegister(ModelMap modelMap, HttpSession session) {

		// https://stackoverflow.com/questions/13242394/spring-mvc-multiple-modelattribute-on-the-same-form
		// voorlopige lijst met tetsdata
//		List<String> cities = new ArrayList<String>();
//		cities.add("London");
//		cities.add("Tokyo");
//		cities.add("New York");
//		cities.add("'t Stad");

		CitieListWrapper citieWrapper = new CitieListWrapper();
		//citieWrapper.setCities(cities);
		
		modelMap.addAttribute("locations", citieWrapper);
		
		
		ExperienceWrapper experienceWrapper = new ExperienceWrapper();
		
//		List<String> experienceIntervals = new ArrayList<String>();
//		experienceIntervals.add("<= 2");
//		experienceIntervals.add("3 - 5");
//		experienceIntervals.add("< 5");
//		experienceWrapper.setExperienceIntervals(experienceIntervals);
		
		modelMap.addAttribute("experience", experienceWrapper);
		
		List<CandidateSearchResolver> candidates = candidateservice.getAllCandidates();
		return goToResultpage(modelMap, candidates, session);

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
	public String searchInAllCandidates(@ModelAttribute("search") Search search ,@ModelAttribute("experience") ExperienceWrapper experience, ModelMap modelMap,
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
//		if (search.isEmployed()) {
//			// search only in recruited candiates
//			List<Integer> applicationProcessIdList = candidates.stream()
//					.map(c -> c.getCandidate().getApplicationProcessId()).collect(Collectors.toList());
//			candidates.clear(); // lijst candidaten
//			candidates.addAll(candidateservice.findAllRecruitedIn(applicationProcessIdList));
//		}
		
		// get list with skillIds
		List<Integer> skillsIdList = new ArrayList<Integer>();
		// if already candidates added from code above
		if (!candidates.isEmpty()) {
			 skillsIdList = candidates.stream()
					.map(c -> c.getCandidate().getSkillsId()).collect(Collectors.toList());
			System.out.println("lengte"+ skillsIdList.size());
		}else {
			 List<Skills> l=(List<Skills>) skillsService.findAllSkills();
					 
					 skillsIdList= l.stream().map(Skills::getId)
						.collect(Collectors.toList());
			 
		}
		
		
		
		
		//List <CandidateSearchResolver> candidates = new ArrayList<CandidateSearchResolver>();
		if (experience.getExperienceIntervals() != null) {
			System.out.println("experience"+experience.getExperienceIntervals());
			if (experience.getExperienceIntervals().toString().contains("2")) {
				List<Integer>skillsIdsToKeep =skillsService.findAllByExperienceLessThan(3);
				System.out.println("lengte"+ skillsIdsToKeep.size());

				skillsIdList.retainAll( skillsIdsToKeep );
				System.out.println("lengte"+ skillsIdList.size());
				candidates.clear();
				candidates.addAll(candidateservice.findAllBySkillsId(skillsIdList));
				
			}else if(experience.getExperienceIntervals().toString().contains(" - ")){
				
				List<Integer>skillsIdsToKeep =skillsService.findAllByExperienceGreaterThanAndExperienceLessThan(2,6);
				System.out.println("lengte"+ skillsIdsToKeep.size());

				skillsIdList.retainAll( skillsIdsToKeep );
				System.out.println("lengte"+ skillsIdList.size());
				candidates.clear();
				candidates.addAll(candidateservice.findAllBySkillsId(skillsIdList));
				
				
				//candidates.addAll(candidateservice.findAllByExperienceGreaterThanAndExperienceLessThan(2,6));
			}else if(experience.getExperienceIntervals().toString().contains("> 5")){
				System.out.println("we zijn hier");
				List<Integer>skillsIdsToKeep =skillsService.findAllByExperienceGreaterThan(5);
				System.out.println("lengte"+ skillsIdsToKeep.size());

				skillsIdList.retainAll( skillsIdsToKeep );
				System.out.println("lengte"+ skillsIdList.size());
				candidates.clear();
				candidates.addAll(candidateservice.findAllBySkillsId(skillsIdList));
				//candidates.addAll(candidateservice.findAllByExperienceGreaterThan(5));
			}
			
			
			
		}
		
		if (search.isEmployed()) {
			// search only in recruited candiates
			List<Integer> applicationProcessIdList = candidates.stream()
					.map(c -> c.getCandidate().getApplicationProcessId()).collect(Collectors.toList());
			candidates.clear(); // lijst candidaten
			candidates.addAll(candidateservice.findAllRecruitedIn(applicationProcessIdList));
		}
		
		
		
		
		
//		if (search.getExperience() != 0) {
//			// TODO: filter te bouwen
//			// moet hier nog 1 worden afgetrokken omdate enkel groter dan gezocht wordt?
//			// COMBINATIE GREATER THAN EN in
//			List<CandidateSearchResolver> filterdByExperience = candidateservice
//					.findByExperienceGreaterThan(search.getExperience(), candidates);
//			candidates = filterdByExperience;
//		}
		return goToResultpage(modelMap, candidates, session);
	}
	
	@GetMapping(value = "/searchAllCandidatesWithLocation")
	public String searchAllCandidatesWithLocation(ModelMap modelMap, @ModelAttribute("locations") CitieListWrapper locations, HttpSession session) {
		
		List <CandidateSearchResolver> candidates = new ArrayList<CandidateSearchResolver>();
		if (locations.getCities() != null) {
			candidates =candidateservice.findAllPreferredlocationContaining(locations.getCities());
		}
		
		return goToResultpage(modelMap, candidates, session);
	}
			
	@GetMapping(value = "/searchAllCandidatesWithExperience")
	public String searchAllCandidatesWithExperience(ModelMap modelMap, @ModelAttribute("experience") ExperienceWrapper experience, HttpSession session) {
		
		List <CandidateSearchResolver> candidates = new ArrayList<CandidateSearchResolver>();
		if (experience.getExperienceIntervals() != null) {
			System.out.println("experience"+experience.getExperienceIntervals());
			if (experience.getExperienceIntervals().toString().contains("2")) {
				candidates =candidateservice.findAllByExperienceLessThan(3);
				
			}else if(experience.getExperienceIntervals().toString().contains("- 5")){
				candidates =candidateservice.findAllByExperienceGreaterThanAndExperienceLessThan(2,6);
			}else if(experience.getExperienceIntervals().toString().contains("> 5")) {
				candidates =candidateservice.findAllByExperienceGreaterThan(5);
			}
			
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
		//Search search = new Search();
		modelMap.addAttribute("search", new Search());
		ExperienceWrapper experienceWrapper = new ExperienceWrapper();
		CitieListWrapper citieWrapper = new CitieListWrapper();
		modelMap.addAttribute("candidate", new NewCandidate());
		modelMap.addAttribute("experience", experienceWrapper);
		modelMap.addAttribute("locations", citieWrapper);
		modelMap.addAttribute("candidates", candidates);
		modelMap.addAttribute("candaidatesearchmodel", new CandaidateSearchModel());
		return "searchcandidateresult";
	}
	//////////////////////////////////////////dftg//searchAllCandidatesWithoutActiveApplicationProcess
	@RequestMapping(value = "/toSearchPage")
	public String toSearchPage(ModelMap modelMap, HttpSession session) {

		modelMap.addAttribute("candidate", new NewCandidate());
		modelMap.addAttribute("search", new Search());
		modelMap.addAttribute("experience", new ExperienceWrapper());
		modelMap.addAttribute("locations", new CitieListWrapper());
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

	@GetMapping(value = "/dispatcher/{test}")
	public String searchDispatcher(@PathVariable("test") String test) {
		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		return "searchssalarypackage";
	}

	@GetMapping(value = "searchcandidatedetails/{candidateId}")
	public String searchCandidateDetails(ModelMap map, @PathVariable("candidateId") int candidateId)
			throws IOException {

		map.addAttribute("candidate", candidateservice.getCandidateById(candidateId));

		map.addAttribute("address",
				addressService.getAddressById(candidateservice.getCandidateById(candidateId).getAddressId()));

		return "updatecandidate";
	}

	@GetMapping(value = "searchcv/{candidateId}")
	public String searchCv(@PathVariable("candidateId") int candidateId) {
		return "searchcv";
	}

	@GetMapping(value = "searchskills/{candidateId}")
	public String searchSkills(Model model, @PathVariable("candidateId") int candidateId) {
		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed

		Candidate tmpCandidate = candidateservice.getCandidateById(candidateId);
		Skills tmpSkills = skillsService.getSkillsById(tmpCandidate.getSkillsId());

		model.addAttribute("skills", tmpSkills);
		return "searchskills";
	}

	@GetMapping(value = "searchcurrentsalarypackage/{candidateId}")
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

	@GetMapping(value = "searchproposedsalarypackage/{candidateId}")
	public String searchProposedSalarypackage(Model model, @PathVariable("candidateId") int candidateId) {
		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		Candidate candidate = candidateservice.getCandidateById(candidateId);
		// voorgestelde loonpakket!
		SalaryPackage tmpSalarypackage = salaryPackageService
				.getSalaryPackageById(candidate.getProposedSallaryPackageId());
		model.addAttribute("proposedSalaryPackage", tmpSalarypackage);
		return "searchssalarypackage";
	}

	@GetMapping(value = "searchapplicationproces/{candidateId}")
	public String searchApplicationproces(@PathVariable("candidateId") String candidateId) {
		return "searchapplicationproces";
	}

}
