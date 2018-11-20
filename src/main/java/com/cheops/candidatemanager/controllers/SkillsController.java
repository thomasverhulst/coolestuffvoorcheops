//package com.cheops.candidatemanager.controllers;
//
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//import com.cheops.candidatemanager.models.Candidate;
//import com.cheops.candidatemanager.models.Skill;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import com.cheops.candidatemanager.services.impl.CandidateService;
//import com.cheops.candidatemanager.services.impl.SkillsService;
//
//@Controller
//public class SkillsController {
//
//	private final Logger logger = Logger.getLogger(SkillsController.class);
//
//	@Autowired
//	private CandidateService candidateservice;
//	@Autowired
//	private SkillsService skillsService;
//
//	@GetMapping(value = "searchskills1/{candidateId}")
//	public String searchSkills(Model model, @PathVariable("candidateId") int candidateId, HttpSession session) {
//
//		Integer skillsId = candidateservice.getCandidateById(candidateId).getSkillsId();
//		if (skillsId <= 0) {
//			Candidate c = candidateservice.getCandidateById(candidateId);
//			session.setAttribute("candidate", c);
//			session.setAttribute("isupdate", true);
//			model.addAttribute("candidate", c);
//			return "toupdateskills";
//		} else {
//			model.addAttribute("skills", skillsService.getSkillsById(skillsId));
//		}
//		return "updateskills";
//	}
//
//	@PostMapping("/updateSkills/{skillsId}")
//	public String update(@Valid Skill skills, @PathVariable("skillsId") int id, BindingResult result,
//											 RedirectAttributes redirect) {
//
//		if (result.hasErrors()) {
//			return "updateskills";
//		}
//		skills.setId(id);
//		skillsService.updateSkills(skills);
//		return "updatesucces";
//	}
//
//	@GetMapping(value = "/addSkills/{candidateId}")
//	public String addSkills(Model model, @PathVariable("candidateId") int candidateId, HttpSession session) {
//		model.addAttribute("candidate", session.getAttribute("candidate"));
//		model.addAttribute("skills", new Skill());
//		boolean update = (boolean) session.getAttribute("isupdate");
//		logger.debug("isUpdate = " + update);
//		if (update) {
//			return "skills";
//		} else {
//			return "updateskills";
//		}
//
//	}
//}
