package com.tv.tutorials.coolestuffvoorcheops.controllers;

import javax.servlet.http.HttpSession;
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

import com.tv.tutorials.coolestuffvoorcheops.model.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.services.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.SalaryPackageService;

@Controller
public class SalaryPackageController {

	@Autowired
	CandidateService candidateservice;
	@Autowired
	SalaryPackageService salaryPackageService;
	
	@RequestMapping(value = "searchcurrentsalarypackage2/{candidateId}", method = RequestMethod.GET)
    public String searchCurrentSalaryPackage( Model model ,@PathVariable("candidateId") int candidateId, HttpSession session ) {
		Integer salaryPackageId = candidateservice.getCandidateById(candidateId).getCurrentSallaryPackageId();
		//model.addAttribute("salaryPackage",salaryPackageRepository.findById(salaryPackageId).get());//shortcut service
		if (salaryPackageId <=0) {
			System.out.println("deze heeft nog geen salarypackage");
			session.setAttribute("candidate", candidateservice.getCandidateById(candidateId));
			session.setAttribute("isupdate", true);
			model.addAttribute("salarypackage", new SalaryPackage());
			//map.addAttribute("lateCreate", true);
			return "salarypackage";
			//return "test";
		}else {
			model.addAttribute("salaryPackage", salaryPackageService.getSalaryPackageById(salaryPackageId));//via service => nullpointer? (niet bij skills) AUTOWIRD STOND UIT
		}
			return "updatesalarypackage";
    }
	
	// dit is de methode om een update te doen...
	@PostMapping("/updateSalaryPackage/{salaryPackageId}")
	public String save(@Valid SalaryPackage salaryPackage, @PathVariable("salaryPackageId") int id ,BindingResult result, RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return "updatesalarypackage";
		}
		
		salaryPackageService.saveOrUpdateSalaryPackage(id, salaryPackage);		
		redirect.addFlashAttribute("success", "Saved employee successfully!");

		return "updatesucces";

	}

}
