package com.cheops.candidatemanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.models.CandidateSearchResolver;
import com.cheops.candidatemanager.services.IDashboardService;
import com.cheops.candidatemanager.services.impl.CandidateService;

@Controller
public class DashboardController {

	@Autowired
	CandidateService candidateService;
	@Autowired
	private IDashboardService dashboardService;


	@GetMapping("/")
	public String registerUser(ModelMap map) {

    System.out.println("I am first and second: " + dashboardService.getUpcomingFirstAndSecondScreenings());


	// get the candidates were field is not null or empty.
	List<Candidate> m3 =candidateService.getAllNotRecruitedCandidates();
	// get the candidatehs were isexempoyee = true
	List<CandidateSearchResolver> m5 =candidateService.getAllExEmployees();


	map.addAttribute("upcommingFirstAndSecondScreeningsCandidates", dashboardService.getUpcomingFirstAndSecondScreenings());

	map.addAttribute("3latestAddedCandidates",dashboardService.get3LatestAddedCandidates());
	map.addAttribute("latest5RecruitedCandidates",dashboardService.getLatest5RecruitedCandidates());
	map.addAttribute("lastMonthsRecruities",dashboardService.getLastMonthsRecruits());

	return "index";
}
}
