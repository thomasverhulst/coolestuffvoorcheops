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

import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.models.SalaryPackage;
import com.tv.tutorials.coolestuffvoorcheops.models.Skills;
import com.tv.tutorials.coolestuffvoorcheops.repositories.SkillsRepository;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.SkillsService;

@Controller
public class SkillsController {

	@Autowired
	private CandidateService candidateservice;
	@Autowired
	private SkillsService skillsService;
	@Autowired
	private SkillsRepository skillsRepository;

	@RequestMapping(value = "searchskills1/{candidateId}", method = RequestMethod.GET)
	public String searchSkills(Model model, @PathVariable("candidateId") int candidateId,HttpSession session) {
		System.out.println("we zijn in de GOEDE skills CONTROLLER" + candidateId);
		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed

		Integer skillsId = candidateservice.getCandidateById(candidateId).getSkillsId();
		if (skillsId <= 0) {
			Candidate c = candidateservice.getCandidateById(candidateId);
			session.setAttribute("candidate", c);
			session.setAttribute("isupdate", true);
			model.addAttribute("candidate", c);
			return "toupdateskills";
		}else {
			model.addAttribute("skills", skillsService.getSkillsById(skillsId));
		}
		
		return "updateskills";
	}

	// @RequestMapping(value ="/updateSkills2/{skillsId}" , method =
	// RequestMethod.POST)
	// public String updateSkills(Model model , @ModelAttribute("skills") Skills
	// skills, @PathVariable ("skillsId") Integer skillsId ) {
	// System.out.println( skills.getId()+ "skillsid");
	// skillsService.updateSkills(skillsService.getSkillsById(skillsId));
	// System.out.println("we zijn in de skills poster");
	// //candidate.setAddressId(tmpAddress.getId());
	// //candidateservice.updateCandidate(candidate);
	// return "test";
	// }

	// dit is de methode om een update te doen...
	@PostMapping("/updateSkills/{skillsId}")
	public String update(@Valid Skills skills, @PathVariable("skillsId") int id, BindingResult result,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return "updateskills";
		}

		skillsService.saveOrUpdateSkills(id, skills);
		// redirect.addFlashAttribute("success", "Saved employee successfully!");
		return "updatesucces";
	}
	
	
	@RequestMapping(value = "/addSkills/{candidateId}", method = RequestMethod.GET)
	public String addSkills(Model model, @PathVariable("candidateId") int candidateId,HttpSession session) {
		System.out.println("we zijn in de GOEDE skills CONTROLLER" + candidateId);
		// https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		System.out.println("HOOOIUY");
		model.addAttribute("candidate", session.getAttribute("candidate"));
		model.addAttribute("skills", new Skills());
		boolean t = (boolean) session.getAttribute("isupdate");
		System.out.println("t = "+t);
		if (t) {
			System.out.println("hier");
			return  "skills";
		}else {
			return "updateskills";
		}
		
	}
}
