package com.cheops.candidatemanager.controllers;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cheops.candidatemanager.pojos.CandidateSaerchResolversMapWrapper;
import com.cheops.candidatemanager.services.ICandidateService;
@Controller
public class StateOverviewController {

	@Autowired
	  private ICandidateService iCandidateService;
	@GetMapping("/state-overvieuw")
	public String addOrUpdateCandidateView(Locale locale, Model model) {
		CandidateSaerchResolversMapWrapper candidateSaerchResolversMapWrapper = new CandidateSaerchResolversMapWrapper();
		candidateSaerchResolversMapWrapper.setCandidateresolversListMap(iCandidateService.getCandidateresolversListMap());
		System.out.println(candidateSaerchResolversMapWrapper.getCandidateresolversListMap().size());

		model.addAttribute("candidateSaerchResolverStates", candidateSaerchResolversMapWrapper);
		
		// add lists per status
		return "stateoverview";
	}
}
