package com.cheops.candidatemanager.controllers;

import javax.validation.Valid;

import com.cheops.candidatemanager.models.ApplicationProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cheops.candidatemanager.services.impl.ApplicationProcessService;
import com.cheops.candidatemanager.services.impl.CandidateService;

@Controller
public class ApplicationProcessController {

	@Autowired
	private CandidateService candidateservice;
	@Autowired
	private ApplicationProcessService applicationProcessService;

	@RequestMapping(value = "searchapplicationproces2/{candidateId}", method = RequestMethod.GET)
	public String searchApplicationProcess(Model model, @PathVariable("candidateId") int candidateId) {
		Integer applicationProcessId = candidateservice.getCandidateById(candidateId).getApplicationProcessId();
//		ApplicationProcess applicationProcess = applicationProcessService.getApplicationProcessById(applicationProcessId);
//		// if blof is not null add filename from blob to feedbackfilename
//			if (applicationProcess.getFeedBackFile() != null) {
//				
//				applicationProcess.setFeedbackFileName(feedbackFileName);
//			}
		
		model.addAttribute("applicationProcess",
				applicationProcessService.getApplicationProcessById(applicationProcessId));
		return "updateapplicationprocess";
	}

	@PostMapping("/updateApplicationProcess/{applicationProcessId}")
	public String save(@Valid ApplicationProcess applicationProcess, @PathVariable("applicationProcessId") int id,
										 BindingResult result, RedirectAttributes redirect) {

		if (result.hasErrors()) {

			return "updateapplicationprocess";
		}
		if (id != 0) {
			applicationProcess.setId(id);
		}
		applicationProcessService.updateApplicationProcess(applicationProcess);
		redirect.addFlashAttribute("success", "Saved employee successfully!");
		return "updatesucces";
	}

}