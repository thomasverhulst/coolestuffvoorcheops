package com.cheops.candidatemanager.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.services.IDashboardService;

@Service
public class DachboardService implements IDashboardService {

	@Autowired
	private CandidateService candidateService;

	// komende gesprekken
	public List<Candidate> getUpcommingFirstAndSecondScreenings() {
		List<Candidate> upcommingScreenings = new ArrayList<Candidate>();
		upcommingScreenings.addAll(candidateService.getUpcommingMonthsFirstScreenings());
		upcommingScreenings.addAll(candidateService.getUpcommingMonthsTechnicalScreenings());
		
		if (!upcommingScreenings.isEmpty()) {
			return upcommingScreenings;
		}else {
			return Collections.<Candidate>emptyList();
		}
					
	}

	public List<Candidate> getLastMonthsRecruities() {
		List<Candidate> lastMonthRecruities = new ArrayList<Candidate>();

		lastMonthRecruities.addAll(candidateService.getLastMonthsRecruities());
		if (!lastMonthRecruities.isEmpty()) {
			return lastMonthRecruities;
		} else {
			return Collections.<Candidate>emptyList();
		}

	}

	//ok
	public List<Candidate> get3LatestAddedCandidates() {
		List<Candidate> latest3AddedCandidates = new ArrayList<Candidate>();
		latest3AddedCandidates.addAll(candidateService.get3LatestAddedCandidates());
		System.out.println("lfd"+latest3AddedCandidates.size());
		if (!latest3AddedCandidates.isEmpty()) {
			return latest3AddedCandidates;
		} else {
			return Collections.<Candidate>emptyList();
		}

	}

	public List<Candidate> getLatest5RecruitedCandidates() {
		List<Candidate> recentlyRecruited = new ArrayList<Candidate>();
		recentlyRecruited.addAll(candidateService.getLast5Recruited());

		if (recentlyRecruited.isEmpty()) {
			return recentlyRecruited;
		} else {
			return Collections.<Candidate>emptyList();
		}

	}
}
