package com.cheops.candidatemanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.services.IDashboardService;

@Controller
public class DashboardController {

	@Autowired
	private IDashboardService dashboardService;

	@GetMapping("/")
	public String registerUser(ModelMap map) {

    System.out.println("I am first and second: " + dashboardService.getUpcomingFirstAndSecondScreenings());

		//List<Candidate> l=dachboardService.getUpcomingFirstAndSecondScreenings();
		//System.out.println( "de lengte vand e lijst is "+l.size());
		//List<Candidate> m =dachboardService.get3LatestAddedCandidates();
		List<Candidate> m2 =dashboardService.getLatest5RecruitedCandidates();
		List<Candidate> m3 =dashboardService.getLastMonthsRecruits();
	
		map.addAttribute("upcommingFirstAndSecondScreeningsCandidates", dashboardService.getUpcomingFirstAndSecondScreenings());
		map.addAttribute("3latestAddedCandidates",dashboardService.get3LatestAddedCandidates());
		map.addAttribute("latest5RecruitedCandidates",dashboardService.getLatest5RecruitedCandidates());
		map.addAttribute("lastMonthsRecruities",dashboardService.getLastMonthsRecruits());

		return "index";
		
	}
}
