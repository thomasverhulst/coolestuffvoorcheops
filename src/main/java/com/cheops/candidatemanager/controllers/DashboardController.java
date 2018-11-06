package com.cheops.candidatemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String registerUser(ModelMap map, Model model) {
//	  List<Candidate> m3 =candidateService.getAllNotRecruitedCandidates(); //get the candidates were field is not null or empty.
//    List<CandidateSearchResolver> m5 =candidateService.getAllExEmployees(); // get the candidatehs were isexempoyee = true

//    map.addAttribute("upcommingFirstAndSecondScreeningsCandidates", dashboardService.getUpcomingFirstAndSecondScreenings());
//    map.addAttribute("3latestAddedCandidates",dashboardService.get3LatestAddedCandidates());
//    map.addAttribute("latest5RecruitedCandidates",dashboardService.getLatest5RecruitedCandidates());
//    map.addAttribute("lastMonthsRecruities",dashboardService.getLastMonthsRecruits());

//    model.addAttribute("upcomingMeetings", dashboardService.getUpcomingFirstAndSecondScreenings());
    model.addAttribute("newCandidates", dashboardService.get3LatestAddedCandidates());
    model.addAttribute("lastRecruited", dashboardService.getLatest5RecruitedCandidates());

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
