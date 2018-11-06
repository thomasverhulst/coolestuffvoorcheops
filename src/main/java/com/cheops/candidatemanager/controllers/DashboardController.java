package com.cheops.candidatemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cheops.candidatemanager.services.IDashboardService;

@Controller
public class DashboardController {

	@Autowired
	private IDashboardService dashboardService;

	@GetMapping("/")
	public String registerUser(Model model) {
    // model.addAttribute("upcomingMeetings", dashboardService.getUpcomingFirstAndSecondScreenings());
    model.addAttribute("newCandidates", dashboardService.get3LatestAddedCandidates());
    model.addAttribute("lastRecruited", dashboardService.getLatest5RecruitedCandidates());

    // How to use for Recent events?
    // dashboardService.getLastMonthsRecruits() use for last block = new rec

    return "index";

//		List<Candidate> l=dachboardService.getUpcomingFirstAndSecondScreenings();
//		System.out.println( "de lengte vand e lijst is "+l.size());
//		List<Candidate> m =dachboardService.get3LatestAddedCandidates();
//		List<Candidate> m2 =dashboardService.getLatest5RecruitedCandidates();
//		List<Candidate> m3 =dashboardService.getLastMonthsRecruits();
//
//		map.addAttribute("upcommingFirstAndSecondScreeningsCandidates", dashboardService.getUpcomingFirstAndSecondScreenings());
//		map.addAttribute("lastMonthsRecruities",dashboardService.getLastMonthsRecruits());
	}
}
