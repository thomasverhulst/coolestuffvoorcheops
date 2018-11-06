package com.cheops.candidatemanager.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;

import javax.validation.Valid;

import com.cheops.candidatemanager.models.ApplicationProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cheops.candidatemanager.services.impl.ApplicationProcessService;
import com.cheops.candidatemanager.services.impl.CandidateService;

@Controller
public class ApplicationProcessController {
	//static final String UPLOADDIRECTORY
	private final static String feedbackuploadDirectory = System.getProperty("user.dir") + "/uploadsfeedback";
	@Autowired
	private CandidateService candidateservice;
	@Autowired
	private ApplicationProcessService applicationProcessService;

	@GetMapping(value = "searchapplicationproces2/{candidateId}")
	public String searchApplicationProcess(Model model, @PathVariable("candidateId") int candidateId) {
		Integer applicationProcessId = candidateservice.getCandidateById(candidateId).getApplicationProcessId();
		
		model.addAttribute("applicationProcess",
				applicationProcessService.getApplicationProcessById(applicationProcessId));
		return "updateapplicationprocess";
	}

	@PostMapping("/updateApplicationProcess/{applicationProcessId}")
	public String save(@Valid ApplicationProcess applicationProcess, @PathVariable("applicationProcessId") int id,
										 BindingResult result, RedirectAttributes redirect) throws IOException {

		if (result.hasErrors()) {

			return "updateapplicationprocess";
		}
		if (id != 0) {
			applicationProcess.setId(id);
		}
		
		//check if isrecruited is set, if so, add timestamp	
		if(applicationProcess.getIsRecruited()&& applicationProcess.getIsRecruitedTimeStamp() != null ) {
			//as timestamp still is null, set new timestamp
			Timestamp isRecruitedTimeStamp = new Timestamp(System.currentTimeMillis());
			applicationProcess.setIsRecruitedTimeStamp(isRecruitedTimeStamp);			
		}
				
		//check if isExemployee is set, if so, add timestamp and unset "isrecruited	
		if(applicationProcess.getIsExEmployee() ) {
			Timestamp isExEmployeeTimeStamp = new Timestamp(System.currentTimeMillis());
			applicationProcess.setIsExEmployeeTimeStamp(isExEmployeeTimeStamp);
			applicationProcess.setIsRecruited(false);
		}
		
		//check if feedbackfile is added
		if (applicationProcess.getFile() != null && !applicationProcess.getFile().isEmpty()) {	
			MultipartFile file = applicationProcess.getFile();
		
			Path filenameAndPath = Paths.get(feedbackuploadDirectory, file.getOriginalFilename());
			Files.write(filenameAndPath, file.getBytes());
			// set link to cv
			applicationProcess.setFeedbackFileName(file.getOriginalFilename());
			
		}
		
		applicationProcessService.updateApplicationProcess(applicationProcess);
		redirect.addFlashAttribute("success", "Saved employee successfully!");
		return "updatesucces";
	}

}