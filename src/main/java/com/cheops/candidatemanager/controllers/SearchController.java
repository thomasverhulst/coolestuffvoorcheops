package com.cheops.candidatemanager.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import com.cheops.candidatemanager.models.*;
import com.cheops.candidatemanager.pojos.CityListWrapper;
import com.cheops.candidatemanager.services.IAddressService;
import com.cheops.candidatemanager.services.ICandidateService;
import com.cheops.candidatemanager.services.ISkillService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.cheops.candidatemanager.pojos.ExperienceWrapper;
import com.cheops.candidatemanager.pojos.StatusWrapper;
import com.cheops.candidatemanager.services.impl.SalaryPackageService;

@Controller
public class SearchController {

	Logger logger = Logger.getLogger(SearchController.class);

	@Autowired
	private ICandidateService candidateService;

	@Autowired
	private ISkillService skillService;

	@GetMapping("/search")
	public String showRegister(ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute("locations", new CityListWrapper());
		modelMap.addAttribute("experience", new ExperienceWrapper());
		modelMap.addAttribute("status", new StatusWrapper());
		List<CandidateSearchResolver> candidates = candidateService.getAllCandidates();
		return goToResultpage(modelMap, candidates, session);
	}

	@RequestMapping(value = "/searchCandidate")
	public String getCandidateByNameAndLastName(ModelMap modelMap, HttpSession session) {
		String name = (String) session.getAttribute("name");
		String lastName = (String) session.getAttribute("lastName");
		List<CandidateSearchResolver> candidates = candidateService.findAllByNameLikeOrLastNameLike(name, lastName);
		modelMap.addAttribute("candidates", candidates);
		modelMap.addAttribute("candaidatesearchmodel", new CandidateSearchModel());
		return "searchcandidateresult";
	}

	@PostMapping(value = "/searchCandidate")
	public String gpostCandidateByNameAndLastName(@RequestParam("name") String name, @RequestParam("lastName") String lastName, ModelMap modelMap, HttpSession session) {
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(lastName)) {
			if (name.equals("")) {
				name = "%";
			}

			if (lastName.equals("")) lastName = "%";
		}

		session.setAttribute("name", name);
		session.setAttribute("lastName", lastName);
		List<CandidateSearchResolver> candidates = candidateService.findAllByNameLikeOrLastNameLike(name, lastName);
		return goToResultpage(modelMap, candidates, session);
	}

	@PostMapping(value = "/exEmployees")
	public String searchExEmployee(@ModelAttribute("search") Search search, ModelMap modelMap, HttpSession session) {
		List<CandidateSearchResolver> candidates = candidateService.getAllExEmployees();
		return goToResultpage(modelMap, candidates, session);

	}

	@PostMapping(value = "/notRecruited")
	public String searchNotRecruited(@ModelAttribute("search") Search search, ModelMap modelMap, HttpSession session) {
		List<CandidateSearchResolver> candidates = candidateService.getAllNotRecruitedCandidates();
		return goToResultpage(modelMap, candidates, session);
	}

	// te refactoren/ verplaatsen naar service
	@PostMapping(value = "/searchInAllCandidates")
	public String searchInAllCandidates(@ModelAttribute("search") Search search, @ModelAttribute("experience") ExperienceWrapper experience, @ModelAttribute("locations") CityListWrapper locations, @ModelAttribute("status") StatusWrapper status, ModelMap modelMap, HttpSession session) {
		List<CandidateSearchResolver> candidates = new ArrayList<CandidateSearchResolver>();
		boolean allreadyZeroResults = false;
		// moet dit naar een service?
		if (search.isDotnet()) {
			// add .netters if asked
			candidates.addAll(candidateService.findAllDotnet());
		}
		if (search.isJava()) {
			// add javaers if asked
			candidates.addAll(candidateService.findAllJava());
		}
		if (search.isFrontender()) {
			// add frontenders if asked
			candidates.addAll(candidateService.findAllFrontend());
		}

		// get list with skillIds
		List<Integer> skillsIdList = new ArrayList<Integer>();
		// if already candidates added from code above
		if (!candidates.isEmpty()) {
			skillsIdList = candidates.stream().map(c -> c.getCandidate().getSkill().getId()).collect(Collectors.toList());
			System.out.println("lengte" + skillsIdList.size());
		} else {
			List<Skill> skillsIds = (List<Skill>) skillService.findAllSkills();
			skillsIdList = skillsIds.stream().map(Skill::getId).collect(Collectors.toList());
		}

		logger.info("Nog \"+ candidates.size() +\" KANDIDATEN");
		System.out.println("Nog "+ candidates.size() +" KANDIDATEN");

		if (!experience.getExperienceIntervals().isEmpty()) {
			System.out.println("experience gekozen" + experience.getExperienceIntervals());

			if (experience.getExperienceIntervals().toString().contains("2")) {
				List<Integer> skillsIdsToKeep = skillService.findAllByExperienceLessThan(3);
				skillsIdList.retainAll(skillsIdsToKeep);
				if (skillsIdList.isEmpty()) allreadyZeroResults = true;
				candidates.clear();
				candidates.addAll(candidateService.findAllBySkillsId(skillsIdList));
			} else if (experience.getExperienceIntervals().toString().contains(" - ") && allreadyZeroResults == false) {
			  List<Integer> skillsIdsToKeep = skillService.findAllByExperienceGreaterThanAndExperienceLessThan(2, 6);
				skillsIdList.retainAll(skillsIdsToKeep);
				if (skillsIdList.isEmpty()) allreadyZeroResults = true;
				candidates.clear();
				candidates.addAll(candidateService.findAllBySkillsId(skillsIdList));
			} else if (experience.getExperienceIntervals().toString().contains("> 5") && allreadyZeroResults == false) {
				List<Integer> skillsIdsToKeep = skillService.findAllByExperienceGreaterThan(5);
				skillsIdList.retainAll(skillsIdsToKeep);
				if (skillsIdList.isEmpty()) allreadyZeroResults = true;
				candidates.clear();
				candidates.addAll(candidateService.findAllBySkillsId(skillsIdList));
			}
		}

		// get ids of candidates
		System.out.println("Nog "+ candidates.size() +" KANDIDATEN");
		System.out.println(search.getName());
		System.out.println(search.getLastName());

		if ((!search.getName().isEmpty() || !search.getLastName().isEmpty()) && allreadyZeroResults == false) {
			if (StringUtils.isEmpty(search.getName()) || StringUtils.isEmpty(search.getLastName())) {
				if (search.getName().equals("")) search.setName("%");
				if (search.getLastName().equals("")) search.setLastName("%");
			}

			session.setAttribute("name", search.getName());
			session.setAttribute("lastName", search.getLastName());
			System.out.println(search.getName());
			System.out.println(search.getLastName());
			List<Integer> candidateIdToKeep = getActualCandidateIds(candidateService.findAllByNameLikeOrLastNameLike(search.getName(), search.getLastName()));
			System.out.println("goede naam " + candidateIdToKeep.size());

			if (!candidates.isEmpty()) {
				List<Integer> candidateIdList2 = getActualCandidateIds(candidates);
				System.out.println("aantaltot" + candidateIdList2.size());
				System.out.println("in de naam");
				candidateIdList2.retainAll(candidateIdToKeep);
				candidates.clear(); // lijst candidaten

        if (candidateIdToKeep.isEmpty()) {
					allreadyZeroResults = true;
				} else {
					candidates.addAll(candidateService.findAllByIdIn(candidateIdToKeep));
				}

			} else {
				System.out.println("we zijn hier");
				System.out.println(candidateIdToKeep.size());

				if (candidateIdToKeep.isEmpty()) {
					allreadyZeroResults = true;
				} else {
					candidates.addAll(candidateService.findAllByIdIn(candidateIdToKeep));
				}
			}
		}

		System.out.println("Nog "+ candidates.size() +" KANDIDATEN");
		// get ids of candidates
		List<Integer> candidateIdList = getActualCandidateIds(candidates);

		if (!locations.getCities().isEmpty() && allreadyZeroResults == false) {
			System.out.println("locaties gokozen" + locations.getCities().toString());
			List<CandidateSearchResolver> candidatesToKeep = candidateService.findAllPreferredlocationContaining(locations.getCities());
			System.out.println("lengtee" + candidatesToKeep.size());
			System.out.println("cand" + candidates.size());
			List<Integer> candidatesToKeepIdList = new ArrayList<Integer>();

			for (CandidateSearchResolver candidateSearchResolver : candidatesToKeep) {
				candidatesToKeepIdList.add(candidateSearchResolver.getCandidate().getId());
			}

			for (CandidateSearchResolver candidateSearchResolver : candidatesToKeep) {
				System.out.println("Tokeep" + candidateSearchResolver.getCandidate().getId());
			}

			if (!candidates.isEmpty()) {
				System.out.println("Er waren " + candidateIdList.size() + "kanidataten");
				candidateIdList.retainAll(candidatesToKeepIdList);
				candidates.clear();

				if (candidateIdList.isEmpty()) {
					allreadyZeroResults = true;
				} else {
					candidates.addAll(candidateService.findAllByIdIn(candidateIdList));
					System.out.println("er zijn nog " + candidates.size() + " kandidaten");
				}
			} else {
				if (candidatesToKeep.isEmpty()) {
					allreadyZeroResults = true;
				} else {
					candidates.addAll(candidatesToKeep);
					System.out.println("was nog niet vol");
				}
			}
		}

		// https://www.mkyong.com/java8/java-8-streams-filter-examples/
		System.out.println("Nog "+ candidates.size() +" KANDIDATEN");
		if (!status.getStatusList().isEmpty() && allreadyZeroResults == false) {
			// status.getStatusList().toString() containt selected status value such as
			// "Eerste intervieuw, stripped because in a list it is surrounded with []"
			String stripperdStatus = status.getStatusList().toString().substring(1, status.getStatusList().toString().length() - 1);

			if (!candidates.isEmpty()) {
				List<Integer> candidateIds = getActualCandidateIds(candidates);
				List<CandidateSearchResolver> candidatesList = candidateService.findAllByIdIn(candidateIds);
				List<CandidateSearchResolver> candidatesToKeep = candidatesList.stream().filter(x -> stripperdStatus.equals(x.getApplicationStatus())).collect(Collectors.toList());
				candidates.clear();

				if (candidatesToKeep.isEmpty()) {
					allreadyZeroResults = true;
				} else {
					candidates.addAll(candidatesToKeep);
					System.out.println("was nog niet vol");
				}
			} else {
				List<Candidate> candidatesList = candidateService.findAllById();
				List<CandidateSearchResolver> candidatesSearchResolver = candidateService.fillExpertiseAndStatus(candidatesList);
				List<CandidateSearchResolver> candidatesToKeep = candidatesSearchResolver.stream().filter(x -> stripperdStatus.equals(x.getApplicationStatus())).collect(Collectors.toList());
				candidates.addAll(candidatesToKeep);
			}
		}

		if (search.isEmployed() && allreadyZeroResults == false) {
			// search only in recruited candiates
			List<Integer> applicationProcessIdList = candidates.stream().map(c -> c.getCandidate().getApplicationProcess().getId()).collect(Collectors.toList());
			candidates.clear(); // lijst candidaten
			candidates.addAll(candidateService.findAllRecruitedIn(applicationProcessIdList));
		}

		System.out.println(search.toString());
		return goToResultpage(modelMap, candidates, session);
	}

	@RequestMapping(value = "/toSearchPage")
	public String toSearchPage(ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute("candidate", new Candidate());
		modelMap.addAttribute("search", new Search());
    modelMap.addAttribute("status", new StatusWrapper());
		modelMap.addAttribute("experience", new ExperienceWrapper());
		modelMap.addAttribute("locations", new CityListWrapper());
		List<Candidate> candidateList = (List<Candidate>) session.getAttribute("candidateResults");
		modelMap.addAttribute("candidates", candidateList);
		return "searchcandidateresult";
	}

  // Custom methods.
  private List<Integer> getActualCandidateIds(List<CandidateSearchResolver> candidates) {
    List<Integer> candidateIdList = new ArrayList<Integer>();
    for (CandidateSearchResolver candidateSearchResolver : candidates) {
      candidateIdList.add(candidateSearchResolver.getCandidate().getId());
    }
    return candidateIdList;
  }

  private String goToResultpage(ModelMap modelMap, List<CandidateSearchResolver> candidates, HttpSession session) {
    session.setAttribute("candidateResults", candidates);
    modelMap.addAttribute("search", new Search());
    modelMap.addAttribute("status", new StatusWrapper());
    modelMap.addAttribute("candidate", new Candidate());
    modelMap.addAttribute("experience", new ExperienceWrapper());
    modelMap.addAttribute("locations", new CityListWrapper());
    modelMap.addAttribute("candidates", candidates);
    modelMap.addAttribute("candaidatesearchmodel", new CandidateSearchModel());
    return "searchcandidateresult";
  }

  //	@PostMapping(value = "/searchAllRecruitedCandidates")
//	public String searchAllRecruitedCandidates(ModelMap modelMap, HttpSession session) {
//		List<CandidateSearchResolver> candidates = candidateService.findAllRecruited();
//		return goToResultpage(modelMap, candidates, session);
//	}
//
//	@PostMapping(value = "/searchAllCandidates")
//	public String searchAllCandidates(ModelMap modelMap, HttpSession session) {
//		List<CandidateSearchResolver> candidates = candidateService.getAllCandidates();
//		return goToResultpage(modelMap, candidates, session);
//	}
//
//	@PostMapping(value = "/searchAllCandidatesWithActiveApplicationProcess")
//	public String searchAllCandidatesWithActiveApplicationProcess(ModelMap modelMap, HttpSession session) {
//		List<CandidateSearchResolver> candidates = candidateService.getAllCandidatesWithActiveApplicationProcess();
//		return goToResultpage(modelMap, candidates, session);
//	}
//
//	@PostMapping(value = "/searchAllNotRecruitedCandidates")
//	public String searchAllNotRecruitedCandidates(ModelMap modelMap, HttpSession session) {
//		List<CandidateSearchResolver> candidates = candidateservice.getAllNotRecruitedCandidates2();
//		return goToResultpage(modelMap, candidates, session);
//	}

//	@PostMapping(value = "/searchAllExEmployees")
//	public String searchAllExEmployees(ModelMap modelMap, HttpSession session) {
//		List<CandidateSearchResolver> candidates = candidateservice.getAllExEmployees();
//		return goToResultpage(modelMap, candidates, session);
//	}

//	@PostMapping(value = "/searchAllCandidatesWithoutActiveApplicationProcess")
//	public String searchAllCandidatesWithoutActiveApplicationProcess(ModelMap modelMap, HttpSession session) {
//		List<CandidateSearchResolver> candidates = candidateservice.getAllCandidatesWithoutActiveApplicationProcess();
//		return goToResultpage(modelMap, candidates, session);
//
//		// modelMap.addAttribute("candidates", candidates);
//		// modelMap.addAttribute("candaidatesearchmodel", new CandidateSearchModel());
//		// return "searchcandidateresult";
//	}

  //	@GetMapping(value = "/searchAllCandidatesWithLocation")
//	public String searchAllCandidatesWithLocation(ModelMap modelMap, @ModelAttribute("locations") CityListWrapper locations, HttpSession session) {
//		List<CandidateSearchResolver> candidates = new ArrayList<CandidateSearchResolver>();
//		if (locations.getCities() != null) candidates = candidateService.findAllPreferredlocationContaining(locations.getCities());
//		return goToResultpage(modelMap, candidates, session);
//	}

//	@GetMapping(value = "/searchAllCandidatesWithExperience")
//	public String searchAllCandidatesWithExperience(ModelMap modelMap, @ModelAttribute("experience") ExperienceWrapper experience, HttpSession session) {
//	  List<CandidateSearchResolver> candidates = new ArrayList<CandidateSearchResolver>();
//
//	  if (experience.getExperienceIntervals() != null) {
//			System.out.println("experience" + experience.getExperienceIntervals());
//
//			if (experience.getExperienceIntervals().toString().contains("2")) {
//				candidates = candidateService.findAllByExperienceLessThan(3);
//			} else if (experience.getExperienceIntervals().toString().contains("- 5")) {
//				candidates = candidateService.findAllByExperienceGreaterThanAndExperienceLessThan(2, 6);
//			} else if (experience.getExperienceIntervals().toString().contains("> 5")) {
//				candidates = candidateService.findAllByExperienceGreaterThan(5);
//			}
//		}
//
//		return goToResultpage(modelMap, candidates, session);
//	}
//@PostMapping(value = "/searchssalarypackage")
//public String getEmployeeByName(@RequestParam("name") String name, @RequestParam("sirName") String sirName, ModelMap modelMap) {
//  List<CandidateSearchResolver> candidates = candidateservice.findAllByNameLikeOrSirNameLike(name, sirName);
//  modelMap.addAttribute("candidates", candidates);
//  return "searchssalarypackage";
//}
  //	@GetMapping(value = "searchcandidatedetails/{candidateId}")
//	public String searchCandidateDetails(ModelMap map, @PathVariable("candidateId") int candidateId)
//			throws IOException {
//
//		map.addAttribute("candidate", candidateservice.getCandidateById(candidateId));
//
//		map.addAttribute("address",
//				addressService.getAddressById(candidateservice.getCandidateById(candidateId).getAddressId()));
//
//		return "updatecandidate";
//	}
  //@GetMapping(value = "searchcv/{candidateId}")
  ////	public String searchCv(@PathVariable("candidateId") int candidateId) {
  ////		return "searchcv";
  ////	}
  ////
  ////	@GetMapping(value = "searchskills/{candidateId}")
  ////	public String searchSkills(Model model, @PathVariable("candidateId") int candidateId) {
  ////		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
  ////
  ////		Candidate tmpCandidate = candidateservice.getCandidateById(candidateId);
  ////		Skills tmpSkills = skillService.getSkillsById(tmpCandidate.getSkillsId());
  ////
  ////		model.addAttribute("skills", tmpSkills);
  ////		return "searchskills";
  ////	}
  ////
  ////	@GetMapping(value = "searchcurrentsalarypackage/{candidateId}")
  ////	public String searchCurrentSalarypackage(Model model, @PathVariable("candidateId") int candidateId) {
  ////		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
  ////		Candidate candidate = candidateservice.getCandidateById(candidateId);
  ////		// huidige loonpakket!
  ////		if (candidate.getCurrentSallaryPackageId() < 0) {
  ////			SalaryPackage tmpSalarypackage = salaryPackageService
  ////					.getSalaryPackageById(candidate.getCurrentSallaryPackageId());
  ////			model.addAttribute("currentSalaryPackage", tmpSalarypackage);
  ////
  ////		} else {
  ////			model.addAttribute("msg",
  ////					"<a href=\"/salarypackage\">Google.com</a> van deze kandidaat is nog geen huidig loonpakket ingevuld, wil u dat doen?");
  ////
  ////		}
  ////
  ////		return "searchssalarypackage";
  ////	}
  ////
  ////	@GetMapping(value = "searchproposedsalarypackage/{candidateId}")
  ////	public String searchProposedSalarypackage(Model model, @PathVariable("candidateId") int candidateId) {
  ////		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
  ////		Candidate candidate = candidateservice.getCandidateById(candidateId);
  ////		// voorgestelde loonpakket!
  ////		SalaryPackage tmpSalarypackage = salaryPackageService
  ////				.getSalaryPackageById(candidate.getProposedSallaryPackageId());
  ////		model.addAttribute("proposedSalaryPackage", tmpSalarypackage);
  ////		return "searchssalarypackage";
  ////	}
  ////
  ////	@GetMapping(value = "searchapplicationproces/{candidateId}")
  ////	public String searchApplicationproces(@PathVariable("candidateId") String candidateId) {
  ////		return "searchapplicationproces";
  ////	}
}