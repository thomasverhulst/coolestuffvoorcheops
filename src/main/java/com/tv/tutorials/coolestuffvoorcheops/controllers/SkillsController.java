package com.tv.tutorials.coolestuffvoorcheops.controllers;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	private SkillsRepository skillsRepository;
	
	@RequestMapping(value = "searchskills1/{candidateId}", method = RequestMethod.GET)
    public String searchSkills( Model model ,@PathVariable("candidateId") int candidateId ) {
		System.out.println("we zijn in de GOEDE skills CONTROLLER"+ candidateId);
		//https://stackoverflow.com/questions/1714028/mvc-which-submit-button-has-been-pressed
		Integer skillsId = candidateservice.getCandidateById(candidateId).getSkillsId();
		model.addAttribute("skills", skillsService.getSkillsById(skillsId) );
		
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
	
	@RequestMapping (value ="/updateSkills/{skillsId}",  method = RequestMethod.POST)
	public String updateSkills(Model model , @ModelAttribute("skills") Skills skills, @PathVariable("skillsId") int id ,HttpServletRequest request) {
	    //your controller code) {
		System.out.println("dits is de id "+id);
		Optional<Skills> skillsOptional =  skillsRepository.findById(id)  ;

		if (!skillsOptional.isPresent())
			///return ResponseEntity.notFound().build();

		//skills.setId(id);
		
		skillsRepository.save(skills);
		System.out.println("saven is gedaan");
		
		 String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
		
		//return ResponseEntity.noContent().build();
	}
}
