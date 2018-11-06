package com.cheops.candidatemanager.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.models.SalaryPackage;
import com.cheops.candidatemanager.models.Update;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cheops.candidatemanager.services.impl.CandidateService;
import com.cheops.candidatemanager.services.impl.SalaryPackageService;

@Controller
public class SalaryPackageController {

	Logger logger = Logger.getLogger(SalaryPackageController.class);

	@Autowired
	private CandidateService candidateservice;
	@Autowired
	private SalaryPackageService salaryPackageService;

	@GetMapping(value = "searchcurrentsalarypackage2/{candidateId}")
	public String searchCurrentSalaryPackage(Model model, @PathVariable("candidateId") int candidateId,
			HttpSession session) {
		Integer salaryPackageId = candidateservice.getCandidateById(candidateId).getCurrentSallaryPackageId();
		// model.addAttribute("salaryPackage",salaryPackageRepository.findById(salaryPackageId).get());//shortcut
		// service
		if (salaryPackageId <= 0) {
			logger.debug("Geen salarispakket gevonden");
			Candidate c = candidateservice.getCandidateById(candidateId);
			session.setAttribute("candidate", c);
			session.setAttribute("isupdate", true);
			session.setAttribute("currentSalaryPackage0OrProposedSalarypackage1", 0);

			Update update = new Update();
			update.setCurrentSalaryPackage0OrProposedSalarypackage1(0);
			model.addAttribute("currentSalaryPackage0OrProposedSalarypackage1", update);
			model.addAttribute("candidate", c);
			return "toupdatesalarypackage";
		} else {
			model.addAttribute("salaryPackage", salaryPackageService.getSalaryPackageById(salaryPackageId));// via
																											// service
																											// =>
																											// nullpointer?
																											// (niet bij
																											// skills)
																											// AUTOWIR																										// STOND UIT
		}
		return "updatesalarypackage";
	}

	@GetMapping(value = "searchproposedsalarypackage2/{candidateId}")
	public String searchProposedSalaryPackage(Model model, @PathVariable("candidateId") int candidateId,
			HttpSession session) {
		Integer salaryPackageId = candidateservice.getCandidateById(candidateId).getProposedSallaryPackageId();
		// model.addAttribute("salaryPackage",salaryPackageRepository.findById(salaryPackageId).get());//shortcut
		// service
		if (salaryPackageId <= 0) {
			Candidate c = candidateservice.getCandidateById(candidateId);
			session.setAttribute("candidate", c);
			session.setAttribute("isupdate", true);
			session.setAttribute("currentSalaryPackage0OrProposedSalarypackage1", 1);
			model.addAttribute("candidate", c);
			Update update = new Update();
			update.setCurrentSalaryPackage0OrProposedSalarypackage1(1);
			model.addAttribute("currentSalaryPackage0OrProposedSalarypackage1", update);

			return "toupdatesalarypackage";
		} else {
			model.addAttribute("salaryPackage", salaryPackageService.getSalaryPackageById(salaryPackageId));
		}
		return "updatesalarypackage";
	}

	@GetMapping(value = "searchupdatesalarypackage/{candidateId}")
	public String searchUpdateSalaryPackage(Model model, @PathVariable("candidateId") int candidateId,
			HttpSession session) {
		model.addAttribute("candidate", session.getAttribute("candidate"));
		model.addAttribute("salarypackage", new SalaryPackage());

		String returning = "updatesalarypackage";
		boolean t = (boolean) session.getAttribute("isupdate");
		if (t) {

			int tmp = (int) session.getAttribute("currentSalaryPackage0OrProposedSalarypackage1");
			if (tmp == 0) {
				returning = "salarypackage";
			} else {
				returning = "salarypackageproposal";
			}

		}

		return returning;

	}

	@PostMapping("/updateSalaryPackage/{salaryPackageId}")
	public String save(@Valid SalaryPackage salaryPackage, @PathVariable("salaryPackageId") int id,
			BindingResult result, RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return "updatesalarypackage";
		}

		if (id != 0) {
			salaryPackage.setId(id);
		}
		salaryPackageService.addSalaryPackage(salaryPackage);
		redirect.addFlashAttribute("success", "Saved employee successfully!");

		return "updatesucces";

	}

}
