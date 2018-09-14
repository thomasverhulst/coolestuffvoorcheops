package com.tv.tutorials.coolestuffvoorcheops.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tv.tutorials.coolestuffvoorcheops.model.ApplicationProcess;
import com.tv.tutorials.coolestuffvoorcheops.model.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.ApplicationProcessRepository;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.SalaryPackageRepository;
import com.tv.tutorials.coolestuffvoorcheops.services.ApplicationProcessService;
import com.tv.tutorials.coolestuffvoorcheops.services.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.SalaryPackageService;

@Controller
public class ApplicationProcessController {

	@Autowired
	CandidateService candidateservice;
	@Autowired
	ApplicationProcessService applicationProcessService;
	@Autowired
	private ApplicationProcessRepository applicationProcessRepository;
	
	@RequestMapping(value = "searchapplicationproces2/{candidateId}", method = RequestMethod.GET)
    public String searchApplicationProcess( Model model ,@PathVariable("candidateId") int candidateId ) {
		Integer applicationProcessId = candidateservice.getCandidateById(candidateId).getApplicationProcessId() ;
		model.addAttribute("applicationProcess", applicationProcessService.getApplicationProcessById(applicationProcessId));
		return "updateapplicationprocess";
    }
	
	// dit is de methode om een update te doen...
	@PostMapping("/updateApplicationProcess/{applicationProcessId}")
	public String save(@Valid ApplicationProcess applicationProcess, @PathVariable("applicationProcessId") int id ,BindingResult result, RedirectAttributes redirect) {

		if (result.hasErrors()) {

			return "updateapplicationprocess";
		}
	
		Optional<ApplicationProcess> tmp = applicationProcessRepository.findById(id);
		if (tmp.isPresent() ) {
			ApplicationProcess s =tmp.get();
			
			s= applicationProcess;
			s.setId(id);
			applicationProcessRepository.save(s);
		}
		else {
			
			System.out.println("tmp = null");
			applicationProcessRepository.save(applicationProcess);
		}
		
		redirect.addFlashAttribute("success", "Saved employee successfully!");

		return "test";

	}


}