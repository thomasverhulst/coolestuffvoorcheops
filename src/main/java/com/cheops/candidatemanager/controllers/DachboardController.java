package com.cheops.candidatemanager.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.services.impl.DachboardService;

@Controller
public class DachboardController {

	@Autowired
	private DachboardService dachboardService;
	@RequestMapping(value = "/")
	public String registerUser(ModelMap map) {
		System.out.println("sdkujfhsuihfs");
		
		//List<Candidate> l=dachboardService.getUpcommingFirstAndSecondScreenings();
		//System.out.println( "de lengte vand e lijst is "+l.size());
		//List<Candidate> m =dachboardService.get3LatestAddedCandidates();
		List<Candidate> m2 =dachboardService.getLatest5RecruitedCandidates();
		List<Candidate> m3 =dachboardService.getLastMonthsRecruities();
	
		map.addAttribute("upcommingFirstAndSecondScreeningsCandidates", dachboardService.getUpcommingFirstAndSecondScreenings());
		map.addAttribute("3latestAddedCandidates",dachboardService.get3LatestAddedCandidates());
		map.addAttribute("latest5RecruitedCandidates",dachboardService.getLatest5RecruitedCandidates());
		map.addAttribute("lastMonthsRecruities",dachboardService.getLastMonthsRecruities());

		return "index";
		
	}
}
