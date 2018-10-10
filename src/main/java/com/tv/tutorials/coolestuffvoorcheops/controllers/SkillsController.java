package com.tv.tutorials.coolestuffvoorcheops.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
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
import com.tv.tutorials.coolestuffvoorcheops.models.Skills;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.impl.SkillsService;

@Controller
public class SkillsController {

	private final Logger logger = Logger.getLogger(SkillsController.class);

	@Autowired
	private CandidateService candidateservice;
	@Autowired
	private SkillsService skillsService;

	@RequestMapping(value = "searchskills1/{candidateId}", method = RequestMethod.GET)
	public String searchSkills(Model model, @PathVariable("candidateId") int candidateId, HttpSession session) {

		Integer skillsId = candidateservice.getCandidateById(candidateId).getSkillsId();
		if (skillsId <= 0) {
			Candidate c = candidateservice.getCandidateById(candidateId);
			session.setAttribute("candidate", c);
			session.setAttribute("isupdate", true);
			model.addAttribute("candidate", c);
			return "toupdateskills";
		} else {
			model.addAttribute("skills", skillsService.getSkillsById(skillsId));
		}
		return "updateskills";
	}

	@PostMapping("/updateSkills/{skillsId}")
	public String update(@Valid Skills skills, @PathVariable("skillsId") int id, BindingResult result,
			RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return "updateskills";
		}

		skillsService.updateSkills(skills);
		return "updatesucces";
	}

	@RequestMapping(value = "/addSkills/{candidateId}", method = RequestMethod.GET)
	public String addSkills(Model model, @PathVariable("candidateId") int candidateId, HttpSession session) {
		model.addAttribute("candidate", session.getAttribute("candidate"));
		model.addAttribute("skills", new Skills());
		boolean update = (boolean) session.getAttribute("isupdate");
		logger.debug("t = " + update);
		if (update) {
			return "skills";
		} else {
			return "updateskills";
		}

	}
}
