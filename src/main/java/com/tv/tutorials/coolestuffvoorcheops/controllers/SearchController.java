package com.tv.tutorials.coolestuffvoorcheops.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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

import com.tv.tutorials.coolestuffvoorcheops.model.Address;
import com.tv.tutorials.coolestuffvoorcheops.model.CandaidateSearchModel;
import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.model.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.model.Search;
import com.tv.tutorials.coolestuffvoorcheops.model.Skills;
import com.tv.tutorials.coolestuffvoorcheops.services.AddressService;
import com.tv.tutorials.coolestuffvoorcheops.services.ApplicationProcessService;
import com.tv.tutorials.coolestuffvoorcheops.services.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.SalaryPackageService;
import com.tv.tutorials.coolestuffvoorcheops.services.SkillsService;

@Controller
public class SearchController {

	//https://www.mkyong.com/spring-boot/spring-boot-hibernate-search-example/
	// https://stackoverflow.com/questions/48800517/how-to-search-multiple-fields-in-a-search-box-in-spring-data-jpa
	@Autowired
	CandidateService candidateservice;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	SkillsService skillsService;
	
	@Autowired
	SalaryPackageService salaryPackageService;
	
	@Autowired
	ApplicationProcessService applicationProcessService;
	
	@GetMapping("/search")
	public String showRegister(Model modelMap) {
		//naar register.html, addres en candadate worden meegegeven
		// ModelMap https://stackoverflow.com/questions/13242394/spring-mvc-multiple-modelattribute-on-the-same-form
		//map.addAttribute("address", new Address());
		//map.addAttribute("candidate", new Candidate());
		modelMap.addAttribute("candidate", new Candidate());
		modelMap.addAttribute("saerch", new Search());
		System.out.println("Search is hier");
		return "search";
	}
	
	@RequestMapping(value ="/searchCandidate2",  method = RequestMethod.POST)
	public String register(Model model , @ModelAttribute("address") Address address, @ModelAttribute("candidate") Candidate candidate, HttpSession session ) {
		
		//https://stackoverflow.com/questions/2227395/spring-3-0-set-and-get-session-attribute
		//Candidate tmpCandidate =candidateservice.addCandidate(candidate);
		//session.setAttribute("candidate", tmpCandidate);
		
		
		//Address tmpAddress = addressService.addAddress(address);
		
		//candidate.setAddressId(tmpAddress.getId());
		//candidateservice.updateCandidate(candidate);
		System.out.println("we hebben iest weggeschreven");
		System.out.println("Address saven is gelukt? " );
		return null;
		}
	@RequestMapping(value = "/searchCandidate" )
    public String getCandidateByNameAndSirName( ModelMap modelMap,  HttpSession session) {
		System.out.println("we zijn toch al hier");
		String name = (String) session.getAttribute("name");
		String sirName =(String) session.getAttribute("sirName");
        List<Candidate> candidates = candidateservice.findAllByNameLikeOrSirNameLike(name, sirName) ;
        modelMap.addAttribute("candidates", candidates);
        modelMap.addAttribute("candaidatesearchmodel", new CandaidateSearchModel());
       
        return "candidatesearchresult";
    }
	
	@RequestMapping(value = "/searchCandidate", method = RequestMethod.POST)
    public String gpostCandidateByNameAndSirName(@RequestParam("name") String name, @RequestParam("sirName") String sirName, ModelMap modelMap, HttpSession session) {
		System.out.println("we zijn toch al hier");
		
		session.setAttribute("name", name);
		session.setAttribute("sirName", sirName);
	
        List<Candidate> candidates = candidateservice.findAllByNameLikeOrSirNameLike(name, sirName) ;
        modelMap.addAttribute("candidates", candidates);
        modelMap.addAttribute("candaidatesearchmodel", new CandaidateSearchModel());
       
        return "candidatesearchresult";
    }
	
	@RequestMapping(value = "/searchInAllCandidates", method = RequestMethod.POST)
    public String searchInAllCandidates(@ModelAttribute("search") Search search, ModelMap modelMap, HttpSession session) {
		System.out.println("we zijn toch al hier");
		
		//session.setAttribute("name", name);
		//session.setAttribute("sirName", sirName);
		System.out.println("tis"+search.isDotnet());
		List<Candidate> candidates= new ArrayList<Candidate>();
		
		if (search.isDotnet()) {
			System.out.println("we zijn er in");
			//add .netters if asked
			 candidates.addAll(candidateservice.findAllDotnet());
		} 
		if(search.isJava()){
			//add javaers if asked
			candidates.addAll(candidateservice.findAllJava());
		}
		if(search.isFrontender()){
			//add frontenders if asked
			candidates.addAll(candidateservice.findAllFrontend());
		}
        
        modelMap.addAttribute("candidates", candidates);
        modelMap.addAttribute("candaidatesearchmodel", new CandaidateSearchModel());
       
        return "candidatesearchresult";
    }	
	
										
	@RequestMapping(value = "/searchAllRecruitedCandidates", method = RequestMethod.POST)
    public String searchAllRecruitedCandidates(ModelMap modelMap, HttpSession session) {
		List<Candidate> candidates= candidateservice.findAllRecruited();
		modelMap.addAttribute("candidates", candidates);
	    modelMap.addAttribute("candaidatesearchmodel", new CandaidateSearchModel());
		return "candidatesearchresult";
	}
	
	@RequestMapping(value = "/searchAllCandidates", method = RequestMethod.POST)
    public String searchAllCandidates(ModelMap modelMap, HttpSession session) {
		List<Candidate> candidates= candidateservice.getAllCandidates();
		modelMap.addAttribute("candidates", candidates);
	    modelMap.addAttribute("candaidatesearchmodel", new CandaidateSearchModel());
		return "candidatesearchresult";
	}
	
	@RequestMapping(value = "/searchssalarypackage", method = RequestMethod.POST)
    public String getEmployeeByName(@RequestParam("name") String name, @RequestParam("sirName") String sirName, ModelMap modelMap) {
		System.out.println("we zijn toch al hier");
		
		// haal candidateid uit sessie, zoek zo id van die candidaat zijn gegegvens, haal "link cv op, zoek cv
        List<Candidate> candidates = candidateservice.findAllByNameLikeOrSirNameLike(name, sirName) ;
        modelMap.addAttribute("candidates", candidates);
        return "searchssalarypackage";
    }
	
	@RequestMapping(value = "/dispatcher/{test}", method = RequestMethod.GET)
    public String searchDispatcher( @PathVariable("test") String test) {
		System.out.println("we zijn in de dispatcher"+ test);
		//https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		
        return "searchssalarypackage";
    }
	
	// GOEDE REQUESTMAPPINGS HIERONDER komt vanuit candidatesearchresult
	// goede uitleg pathvariabelen https://stackoverflow.com/questions/46171030/spring-boot-thymeleaf-could-not-parse-as-expression-with-an-url
	
	@RequestMapping(value = "searchcandidatedetails/{candidateId}", method = RequestMethod.GET)
    public String searchCandidateDetails( ModelMap map ,@PathVariable("candidateId") int candidateId ) {
		System.out.println("we zijn in de GOEDE CONTROLLER"+ candidateId);
		//https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		
		map.addAttribute("candidate", candidateservice.getCandidateById(candidateId));
		//Address tmpAddress=addressService.getAddressById(tmpCandidate.getAddressId());
		map.addAttribute("address", addressService.getAddressById(candidateservice.getCandidateById(candidateId).getAddressId()));
		//map.addAttribute("update", new Update(true));
		//map.addAttribute("address", tmpAddress);
		//map.addAttribute("candidate", tmpCandidate);
		//return "register"; ik krijg geen voorwaardelijke knoppen, zo kan het niet hergebruikt worden
		//werkt , ziet er uit zoals register 
		return "updatecandidate";
        //werkt return "searchcandidatedetails";
    }
	
	///{candidateId}
	@RequestMapping(value = "updatecandidate", method = RequestMethod.POST)
    public String updateCandidate( ModelMap map , @ModelAttribute("address") Address address,@ModelAttribute("candidate") Candidate candidate, HttpSession session) {
		System.out.println("we zijn in de GOEDE UPDATE CONTROLLER");
		//https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		//Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
		//Candidate tmpCandidate =candidateservice.getCandidateById(candidateId);
		//Address tmpAddress=addressService.getAddressById(tmpCandidate.getAddressId());
		Candidate tmp = candidateservice.getCandidateById(8);
		tmp.setPhoneNumber(tmp.getPhoneNumber() +88888);
	
		//return "register"; ik krijg geen voorwaardelijke knoppen, zo kan het niet hergebruikt worden
		//werkt , ziet er uit zoals register 
		// modelmap, hgetattribute ipv modelattribute?
		
		 addressService.updateAddress((Address) map.get("address"));
			
			//candidate.setAddressId(tmpAddress.getId());
			candidateservice.updateCandidate(tmp);
		return "test";
        //werkt return "searchcandidatedetails";
    }	
	
	@RequestMapping(value = "searchcv/{candidateId}", method = RequestMethod.GET)
    public String searchCv( @PathVariable("candidateId") int candidateId) {
		System.out.println("we zijn in de GOEDE CONTROLLER"+ candidateId);
		
		Candidate tmpCandidate =candidateservice.getCandidateById(candidateId);

		//map.addAttribute("address", tmpAddress);
		
        return "searchcv";
    }
	
	@RequestMapping(value = "searchskills/{candidateId}", method = RequestMethod.GET)
    public String searchSkills(Model model, @PathVariable("candidateId") int candidateId) {
		System.out.println("we zijn in de GOEDE CONTROLLER"+ candidateId);
		//https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		
		Candidate tmpCandidate =candidateservice.getCandidateById(candidateId);
		Skills tmpSkills=skillsService.getSkillsById(tmpCandidate.getSkillsId())  ;

		model.addAttribute("skills", tmpSkills);	
        return "searchskills";
    }
	
	
	@RequestMapping(value = "searchcurrentsalarypackage/{candidateId}", method = RequestMethod.GET)
    public String searchCurrentSalarypackage(Model model, @PathVariable("candidateId") int candidateId) {
		System.out.println("we zijn in de GOEDE CONTROLLER"+ candidateId);
		//https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		Candidate candidate =candidateservice.getCandidateById(candidateId);
		System.out.println("we zijn hier"+ candidate.getEmail());
		// huidige loonpakket!
		if (candidate.getCurrentSallaryPackageId()<0) {
			SalaryPackage tmpSalarypackage = salaryPackageService.getSalaryPackageById(candidate.getCurrentSallaryPackageId());
			model.addAttribute("currentSalaryPackage", tmpSalarypackage);

		}else {
			model.addAttribute("msg", "<a href=\"/salarypackage\">Google.com</a> van deze kandidaat is nog geen huidig loonpakket ingevuld, wil u dat doen?");
			//MyMessageDialog.openError(parent.getShell(), "Hehe", "<a href=\"http://google.com\">Google.com</a> Test");

			//MyMessageDialog.openError(parent.getShell(), "Hehe", "<a href=\"/salarypackage\">Google.com</a> van deze kandidaat is nog geen huidig loonpakket ingevuld, wil u dat doen?");

		}
		
		return "searchssalarypackage";
    }
	
	@RequestMapping(value = "searchproposedsalarypackage/{candidateId}", method = RequestMethod.GET)
    public String searchProposedSalarypackage(Model model, @PathVariable("candidateId") int candidateId) {
		System.out.println("we zijn in de GOEDE CONTROLLER"+ candidateId);
		//https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		Candidate candidate =candidateservice.getCandidateById(candidateId);
		System.out.println("we zijn hier"+ candidate.getEmail());
		// huidige loonpakket!
		SalaryPackage tmpSalarypackage = salaryPackageService.getSalaryPackageById(candidate.getProposedSallaryPackageId());
		model.addAttribute("proposedSalaryPackage", tmpSalarypackage);
        return "searchssalarypackage";
    }
	
	@RequestMapping(value = "searchapplicationproces/{candidateId}", method = RequestMethod.GET)
    public String searchApplicationproces( @PathVariable("candidateId") String candidateId) {
		System.out.println("we zijn in de GOEDE CONTROLLER"+ candidateId);
		//https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		
        return "searchapplicationproces";
    }	
	
}
