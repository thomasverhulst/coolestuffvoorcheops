package com.tv.tutorials.coolestuffvoorcheops.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tv.tutorials.coolestuffvoorcheops.model.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.model.Skills;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.SalaryPackageRepository;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.SkillsRepository;
import com.tv.tutorials.coolestuffvoorcheops.services.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.SalaryPackageService;
import com.tv.tutorials.coolestuffvoorcheops.services.SkillsService;

@Controller
public class SalaryPackageController {

	@Autowired
	CandidateService candidateservice;
	@Autowired
	SalaryPackageService salaryPackageService;
	@Autowired
	private SalaryPackageRepository salaryPackageRepository;
	
	@RequestMapping(value = "searchcurrentsalarypackage2/{candidateId}", method = RequestMethod.GET)
    public String searchCurrentSalaryPackage( Model model ,@PathVariable("candidateId") int candidateId ) {
		Integer salaryPackageId = candidateservice.getCandidateById(candidateId).getCurrentSallaryPackageId();
		//model.addAttribute("salaryPackage",salaryPackageRepository.findById(salaryPackageId).get());//shortcut service
		model.addAttribute("salaryPackage", salaryPackageService.getSalaryPackageById(salaryPackageId));//via service => nullpointer? (niet bij skills) AUTOWIRD STOND UIT
		return "updatesalarypackage";
    }
	
	// dit is de methode om een update te doen...
	@PostMapping("/updateSalaryPackage/{salaryPackageId}")
	public String save(@Valid SalaryPackage salaryPackage, @PathVariable("salaryPackageId") int id ,BindingResult result, RedirectAttributes redirect) {

		if (result.hasErrors()) {

			return "updatesalarypackage";
		}
	
		Optional<SalaryPackage> tmp = salaryPackageRepository.findById(id);
		if (tmp.isPresent() ) {
			SalaryPackage s =tmp.get();
			
			s= salaryPackage;
			s.setId(id);
			salaryPackageRepository.save(s);
		}
		else {
			
			System.out.println("tmp = null");
			salaryPackageRepository.save(salaryPackage);
		}
		
		redirect.addFlashAttribute("success", "Saved employee successfully!");

		return "test";

	}

}
