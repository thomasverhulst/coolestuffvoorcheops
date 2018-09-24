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

import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;
//import com.google.common.base.Optional;
import com.tv.tutorials.coolestuffvoorcheops.model.Skills;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.SkillsRepository;
import com.tv.tutorials.coolestuffvoorcheops.services.CandidateService;
import com.tv.tutorials.coolestuffvoorcheops.services.SkillsService;

@Controller
public class SkillsController {

	@Autowired
	CandidateService candidateservice;
	@Autowired
	SkillsService skillsService;
	@Autowired
	SkillsRepository skillsRepository;
	
	@RequestMapping(value = "searchskills1/{candidateId}", method = RequestMethod.GET)
    public String searchSkills( Model model ,@PathVariable("candidateId") int candidateId ) {
		System.out.println("we zijn in de GOEDE skills CONTROLLER"+ candidateId);
		//https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
	
		// de kandidaat bestaat
		//if (tmp.isPresent() ) {
		Integer skillsId = candidateservice.getCandidateById(candidateId).getSkillsId();
		
	
		
		
		
		model.addAttribute("skills", skillsService.getSkillsById(skillsId) );
		System.out.println("skillid = "+ skillsId);
		
		// als er nog geen skills zijn moet deze ook naar een pagina die vraagd of ze toegevoegd moeten worden
		
		return "updateskills";
        //werkt return "searchcandidatedetails";
    }
	
	@RequestMapping(value ="/updateSkills2/{skillsId}" , method = RequestMethod.POST)
	public String updateSkills(Model model , @ModelAttribute("skills") Skills skills, @PathVariable ("skillsId") Integer skillsId ) {
		System.out.println( skills.getId()+ "skillsid");
		skillsService.updateSkills(skillsService.getSkillsById(skillsId));
			System.out.println("we zijn in de skills poster");
			//candidate.setAddressId(tmpAddress.getId());
			//candidateservice.updateCandidate(candidate);
			return "test";
	}
	
	// dit is de methode om een update te doen...
	@PostMapping("/updateSkills/{skillsId}")
	public String update(@Valid Skills skills, @PathVariable("skillsId") int id ,BindingResult result, RedirectAttributes redirect) {

		if (result.hasErrors()) {
			return "updateskills";
		}
	
		skillsService.saveOrUpdateSkills(id, skills);		
		//redirect.addFlashAttribute("success", "Saved employee successfully!");
		return "updatesucces";

	}
	
}
