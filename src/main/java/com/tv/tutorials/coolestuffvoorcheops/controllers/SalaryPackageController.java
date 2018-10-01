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

import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.model.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.model.Update;
import com.tv.tutorials.coolestuffvoorcheops.services.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.SalaryPackageService;

@Controller
public class SalaryPackageController {

	@Autowired
	CandidateService candidateservice;
	@Autowired
	SalaryPackageService salaryPackageService;

	@RequestMapping(value = "searchcurrentsalarypackage2/{candidateId}", method = RequestMethod.GET)
	public String searchCurrentSalaryPackage(Model model, @PathVariable("candidateId") int candidateId,
			HttpSession session) {
		Integer salaryPackageId = candidateservice.getCandidateById(candidateId).getCurrentSallaryPackageId();
		// model.addAttribute("salaryPackage",salaryPackageRepository.findById(salaryPackageId).get());//shortcut
		// service
		if (salaryPackageId <= 0) {
			System.out.println("deze heeft nog geen salarypackage");
			Candidate c = candidateservice.getCandidateById(candidateId);
			session.setAttribute("candidate", c);
			session.setAttribute("isupdate", true);
			session.setAttribute("currentSalaryPackage0OrProposedSalarypackage1", 0);

			// model.addAttribute("salarypackage", new SalaryPackage());
			// map.addAttribute("lateCreate", true);
			model.addAttribute("candidate", c);
			Update update = new Update();
			System.out.println("HOI WE ZIJN DAAR");
			update.setCurrentSalaryPackage0OrProposedSalarypackage1(0);
			model.addAttribute("currentSalaryPackage0OrProposedSalarypackage1", update);
			return "toupdatesalarypackage";
			// return "test";
		} else {
			model.addAttribute("salaryPackage", salaryPackageService.getSalaryPackageById(salaryPackageId));// via
																											// service
																											// =>
																											// nullpointer?
																											// (niet bij
																											// skills)
																											// AUTOWIRD
																											// STOND UIT
		}
		return "updatesalarypackage";
	}

	@RequestMapping(value = "searchproposedsalarypackage2/{candidateId}", method = RequestMethod.GET)
	public String searchProposedSalaryPackage(Model model, @PathVariable("candidateId") int candidateId,
			HttpSession session) {
		Integer salaryPackageId = candidateservice.getCandidateById(candidateId).getProposedSallaryPackageId();
		// model.addAttribute("salaryPackage",salaryPackageRepository.findById(salaryPackageId).get());//shortcut
		// service
		if (salaryPackageId <= 0) {
			System.out.println("deze heeft nog geen voorgesteld salarypackage");
			Candidate c = candidateservice.getCandidateById(candidateId);
			session.setAttribute("candidate", c);
			session.setAttribute("isupdate", true);
			session.setAttribute("currentSalaryPackage0OrProposedSalarypackage1", 1);
			// model.addAttribute("currentSalaryPackage0OrProposedSalarypackage1", 1);
			model.addAttribute("candidate", c);
			Update update = new Update();
			System.out.println("HOI WE ZIJN GIER");
			update.setCurrentSalaryPackage0OrProposedSalarypackage1(1);
			model.addAttribute("currentSalaryPackage0OrProposedSalarypackage1", update);
			// model.addAttribute("salarypackage", new SalaryPackage());
			// map.addAttribute("lateCreate", true);
			return "toupdatesalarypackage";
			// return "test";
		} else {
			model.addAttribute("salaryPackage", salaryPackageService.getSalaryPackageById(salaryPackageId));// via
																											// service
																											// =>
																											// nullpointer?
																											// (niet bij
																											// skills)
																											// AUTOWIRD
																											// STOND UIT
		}
		return "updatesalarypackage";
	}

	@RequestMapping(value = "searchupdatesalarypackage/{candidateId}", method = RequestMethod.GET)
	public String searchUpdateSalaryPackage(Model model, @PathVariable("candidateId") int candidateId,
			HttpSession session) {
		model.addAttribute("candidate", session.getAttribute("candidate"));
		model.addAttribute("salarypackage", new SalaryPackage());

		String returning = "updatesalarypackage";
		boolean t = (boolean) session.getAttribute("isupdate");
		if (t) {

			int tmp = (int) session.getAttribute("currentSalaryPackage0OrProposedSalarypackage1");
			if (tmp == 0) {
				System.out.println("update y");
				returning = "salarypackage";
			} else {
				System.out.println("update x");
				returning = "salarypackageproposal";
			}

		}

		return returning;

	}

	// dit is de methode om een update te
	// doen...@PostMapping("/updateCandidate/{candidateId}/{addressId}")
	@PostMapping("/updateSalaryPackage/{salaryPackageId}")
	public String save(@Valid SalaryPackage salaryPackage, @PathVariable("salaryPackageId") int id,
			BindingResult result, RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return "updatesalarypackage";
		}

		salaryPackageService.saveOrUpdateSalaryPackage(id, salaryPackage);
		redirect.addFlashAttribute("success", "Saved employee successfully!");

		return "updatesucces";

	}

}
