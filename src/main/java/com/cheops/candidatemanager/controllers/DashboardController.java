package com.cheops.candidatemanager.controllers;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.models.CandidateSearchResolver;
import com.cheops.candidatemanager.models.WorkHistory;
import com.cheops.candidatemanager.repositories.WorkHistoryRepository;
import com.cheops.candidatemanager.services.IDashboardService;
import com.cheops.candidatemanager.services.impl.CandidateService;
import com.cheops.candidatemanager.services.impl.DashboardService;
import com.cheops.candidatemanager.services.impl.NewCandidateService;
import com.cheops.candidatemanager.services.impl.WorkHistoryService;

@Controller
public class DashboardController {

	@Autowired
	CandidateService candidateService;
	@Autowired
	private IDashboardService idashboardService;

	@Autowired
	private DashboardService dashboardService;
	@Autowired
	NewCandidateService newCandidateService;
	@Autowired
	WorkHistoryService workHistoryService;
	@Autowired
	WorkHistoryRepository workHistoryRepository;

	@GetMapping("/")

	public String registerUser(ModelMap map) {

    System.out.println("I am first and second: " + idashboardService.getUpcomingFirstAndSecondScreenings());


    
    workHistoryService.getAllExEmployees();
    
	// get the candidates were field is not null or empty.
	List<Candidate> m3 =candidateService.getAllNotRecruitedCandidates();
	// get the candidatehs were isexempoyee = true
	List<CandidateSearchResolver> m5 =candidateService.getAllExEmployees();
	
	List<CandidateSearchResolver> m6 =  dashboardService.getAllExEmployeesNew();
	
	dashboardService.getLatest5RecruitedCandidatesNew();
	dashboardService.getLastMonthsRecruitsNew();

	map.addAttribute("upcommingFirstAndSecondScreeningsCandidates", idashboardService.getUpcomingFirstAndSecondScreenings());

	map.addAttribute("3latestAddedCandidates",idashboardService.get3LatestAddedCandidates());
	map.addAttribute("latest5RecruitedCandidates",idashboardService.getLatest5RecruitedCandidates());
	map.addAttribute("lastMonthsRecruities",idashboardService.getLastMonthsRecruits());

	return "index";

}
}
