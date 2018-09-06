package com.tv.tutorials.coolestuffvoorcheops.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.tv.tutorials.coolestuffvoorcheops.model.Address;
import com.tv.tutorials.coolestuffvoorcheops.model.ApplicationProcess;
import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.model.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.model.Skills;
import com.tv.tutorials.coolestuffvoorcheops.services.AddressService;
import com.tv.tutorials.coolestuffvoorcheops.services.ApplicationProcessService;
import com.tv.tutorials.coolestuffvoorcheops.services.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.IStorageService;
import com.tv.tutorials.coolestuffvoorcheops.services.SalaryPackageService;
import com.tv.tutorials.coolestuffvoorcheops.services.SkillsService;

@Controller
public class CancidateController {

	private final IStorageService storageService;
	public String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	//goede uitleg https://www.mkyong.com/spring-boot/spring-boot-hibernate-search-example/
	@Autowired
	AddressService addressService;
	
	@Autowired
	CandidateService candidateservice;
	
	@Autowired
	SkillsService skillsService;
	
	@Autowired
	SalaryPackageService salaryPackageService;
	
	@Autowired
	ApplicationProcessService applicationProcessService;
	
	 @Autowired
	    public CancidateController(IStorageService storageService) {
	        this.storageService = storageService;
	    }
	@GetMapping("/register")
	public String showRegister(ModelMap map) {
		//naar register.html, addres en candadate worden meegegeven
		// ModelMap https://stackoverflow.com/questions/13242394/spring-mvc-multiple-modelattribute-on-the-same-form
		
//		map.addAttribute("files", storageService.loadAll().map(
//                path -> MvcUriComponentsBuilder.fromMethodName(FileUploaderController.class,
//                        "serveFile", path.getFileName().toString()).build().toString())
//                .collect(Collectors.toList()));
		
		map.addAttribute("address", new Address());
		map.addAttribute("candidate", new Candidate());
		return "register";
	}
	
	@RequestMapping(value ="/registerCandidate",  method = RequestMethod.POST)
	public String register(Model model , @ModelAttribute("address") Address address, @ModelAttribute("candidate") Candidate candidate, HttpSession session ) throws IOException {
		
		//https://stackoverflow.com/questions/2227395/spring-3-0-set-and-get-session-attribute
		Candidate tmpCandidate =candidateservice.addCandidate(candidate);
		session.setAttribute("candidate", tmpCandidate);
		
		
		// save file 
		MultipartFile file = tmpCandidate.getFile();
		Path filenameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
    	
    	Files.write(filenameAndPath, file.getBytes());
		//
		Address tmpAddress = addressService.addAddress(address);
		
		candidate.setAddressId(tmpAddress.getId());
		candidateservice.updateCandidate(candidate);
		System.out.println("we hebben iest weggeschreven");
		System.out.println("Address saven is gelukt? " );
		
		
		System.out.println("Kandidaat id "+ tmpCandidate.getId());
		model.addAttribute("skills", new Skills());
		return "skills";
	}
	
	@RequestMapping(value ="/registerSkills", method = RequestMethod.POST)
	public String registerSkills(Model model , @ModelAttribute("skills") Skills skills, HttpSession session ) {
		//voorlopig terug naar dde testpagina
		
		//SkillsService ms = new SkillsService();
		Skills tmpSkills = skillsService.addSkills(skills);
		System.out.println("Skills id = "+ tmpSkills.getId());
		
		// get candidate from session
		Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
		// set SkillsId to candidate from session
		sessionCandidate.setSkillsId(tmpSkills.getId());
		//Update candidate in database
		candidateservice.updateCandidate(sessionCandidate);
		//Update candidate in session (not needed?)
		session.setAttribute("candidate", sessionCandidate);
		model.addAttribute("salarypackage", new SalaryPackage());
		return "salarypackage";
	}
	
	@RequestMapping(value ="/registerSalaryPackage", method = RequestMethod.POST)
	public String registerSalaryPackage(Model model , @ModelAttribute("salarypackage") SalaryPackage salaryPackage, HttpSession session ) {
		System.out.println("we passeren bij salary");
		System.out.println("we passeren hier "+ salaryPackage.getGrossSalary() );
		//Candidate tmpCandidate =candidateservice.addCandidate(salarypackage);
		SalaryPackage tmpSalarypackage = salaryPackageService.addSalaryPackage(salaryPackage);
		
		
		// get candidate from session
		Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
		// set SkillsId to candidate from session
		sessionCandidate.setCurrentSallaryPackageId(tmpSalarypackage.getId());
		//Update candidate in database
		candidateservice.updateCandidate(sessionCandidate);
		//Update candidate in session (not needed?)
		session.setAttribute("candidate", sessionCandidate);

		
		
		System.out.println("salary id = "+tmpSalarypackage.getId());
		model.addAttribute("applicationprocess", new ApplicationProcess());

		return "applicationProcess";
	}
	
	@RequestMapping(value ="/registerApplicationProcess", method = RequestMethod.POST)
	public String registerApplicationProcess(Model model , @ModelAttribute("applicationprocess") ApplicationProcess applicationProcess,HttpSession session ) {
		System.out.println("we passeren bij applicatiprocess");
		System.out.println("we passeren bij applicatiprocess "+ applicationProcess.getFeedbackFinancialProposal() );
		
		ApplicationProcess tmpApplicationprocess = applicationProcessService.addApplicationProcess(applicationProcess);
		// get candidate from session
		Candidate sessionCandidate = (Candidate) session.getAttribute("candidate");
		// set SkillsId to candidate from session
		sessionCandidate.setApplicationProcessId(tmpApplicationprocess.getId());
		//Update candidate in database
		candidateservice.updateCandidate(sessionCandidate);
		//Update candidate in session (not needed?)
		session.setAttribute("candidate", sessionCandidate);
		
		System.out.println("applicationprocess Id  = "+tmpApplicationprocess.getId());
		return "registrationsucces";
	}
	// naar voorstel
	@RequestMapping(value ="/registerSalaryPackageProposal")
	public String registerSalaryPackageProposal(Model model ) {
		//voorlopig terug naar dde testpagina
		
		System.out.println("we passeren bij salarypackageProposal");
		model.addAttribute("salarypackage", new SalaryPackage());

		return "salarypackageproposal";
	}
	
	@RequestMapping(value ="/sendmail", method = RequestMethod.POST)
	public String sendMail(Model model , @ModelAttribute("salarypackage") SalaryPackage salaryPackage ) {
		//voorlopig terug naar dde testpagina

		System.out.println("we passeren bij salarypackageProposal");
		System.out.println("we passeren hier "+ salaryPackage.getGrossSalary() );

		//model.addAttribute("salarypackage", new SalaryPackage());

		return "mailsucces";
	}
}
