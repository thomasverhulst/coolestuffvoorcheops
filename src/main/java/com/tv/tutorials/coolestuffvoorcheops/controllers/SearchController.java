package com.tv.tutorials.coolestuffvoorcheops.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

//import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.thymeleaf.util.StringUtils;

import com.tv.tutorials.coolestuffvoorcheops.models.CandaidateSearchModel;
import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.models.CandidateSearchResolver;
import com.tv.tutorials.coolestuffvoorcheops.models.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.models.Search;
import com.tv.tutorials.coolestuffvoorcheops.models.Skills;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.AddressService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.SalaryPackageService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.SkillsService;

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

	@RequestMapping(value = "/searchCandidate", method = RequestMethod.POST)
	public String gpostCandidateByNameAndSirName(@RequestParam("name") String name,
			@RequestParam("sirName") String sirName, ModelMap modelMap, HttpSession session) {

		if (!(StringUtils.isEmpty(name) && StringUtils.isEmpty(sirName))) {
			if (name.equals("")) {
				name = "!";
			}

			if (sirName.equals("")) {
				sirName = "!";
			}
		}

		session.setAttribute("name", name);
		session.setAttribute("sirName", sirName);

		List<CandidateSearchResolver> candidates = candidateservice.findAllByNameLikeOrSirNameLike(name, sirName);
		return goToResultpage(modelMap, candidates, session);

	}

	@RequestMapping(value = "/searchInAllCandidates", method = RequestMethod.POST)
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

	@RequestMapping(value = "/searchAllRecruitedCandidates", method = RequestMethod.POST)
	public String searchAllRecruitedCandidates(ModelMap modelMap, HttpSession session) {
		List<CandidateSearchResolver> candidates = candidateservice.findAllRecruited();
		return goToResultpage(modelMap, candidates, session);
	}

	@RequestMapping(value = "/searchAllCandidates", method = RequestMethod.POST)
	public String searchAllCandidates(ModelMap modelMap, HttpSession session) {

		List<CandidateSearchResolver> candidates = candidateservice.getAllCandidates();
		return goToResultpage(modelMap, candidates, session);
	}

	@RequestMapping(value = "/searchAllCandidatesWithActiveApplicationProcess", method = RequestMethod.POST)
	public String searchAllCandidatesWithActiveApplicationProcess(ModelMap modelMap, HttpSession session) {
		List<CandidateSearchResolver> candidates = candidateservice.getAllCandidatesWithActiveApplicationProcess();
		return goToResultpage(modelMap, candidates, session);
	}

	@RequestMapping(value = "/searchAllCandidatesWithoutActiveApplicationProcess", method = RequestMethod.POST)
	public String searchAllCandidatesWithoutActiveApplicationProcess(ModelMap modelMap, HttpSession session) {
		List<Candidate> candidates = candidateservice.getAllCandidatesWithoutActiveApplicationProcess();
		modelMap.addAttribute("candidates", candidates);
		modelMap.addAttribute("candaidatesearchmodel", new CandaidateSearchModel());
		return "searchcandidateresult";
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

	@RequestMapping(value = "/toSearchPage", method = RequestMethod.GET)
	public String toSearchPage(ModelMap modelMap, HttpSession session) {

		List<Candidate> candidateList = (List<Candidate>) session.getAttribute("candidateResults");
		modelMap.addAttribute("candidates", candidateList);
		return "searchcandidateresult";
	}

	@RequestMapping(value = "/searchssalarypackage", method = RequestMethod.POST)
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
	public String searchCandidateDetails(ModelMap map, @PathVariable("candidateId") int candidateId) throws IOException {
		
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
