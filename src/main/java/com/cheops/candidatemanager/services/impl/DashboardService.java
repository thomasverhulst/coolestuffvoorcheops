package com.cheops.candidatemanager.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.services.IDashboardService;

@Service
public class DashboardService implements IDashboardService {

	@Autowired
	private CandidateService candidateService;

	public List<Candidate> getUpcomingFirstAndSecondScreenings() {
		List<Candidate> upcommingScreenings = new ArrayList<Candidate>();
		upcommingScreenings.addAll(candidateService.getUpcomingMonthsFirstScreenings());
		upcommingScreenings.addAll(candidateService.getUpcomingMonthsTechnicalScreenings());
		
		if (!upcommingScreenings.isEmpty()) {
			return upcommingScreenings;
		}else {
			return Collections.<Candidate>emptyList();
		}
	}

	public List<Candidate> getLastMonthsRecruits() {
    List<Candidate> lastMonthRecruities = new ArrayList<Candidate>(candidateService.getLastMonthsRecruits());

		if (!lastMonthRecruities.isEmpty()) {
			return lastMonthRecruities;
		} else {
			return Collections.<Candidate>emptyList();
		}
	}

	public List<Candidate> get3LatestAddedCandidates() {
    List<Candidate> latest3AddedCandidates = new ArrayList<Candidate>(candidateService.get3LatestAddedCandidates());

		if (!latest3AddedCandidates.isEmpty()) {
			return latest3AddedCandidates;
		} else {
			return Collections.<Candidate>emptyList();
		}
	}
// dit ziet er verdacht uit, geen ! bij de isempty?
	public List<Candidate> getLatest5RecruitedCandidates() {
    List<Candidate> recentlyRecruited = new ArrayList<Candidate>(candidateService.getLast5Recruited());

		if (recentlyRecruited.isEmpty()) {
			return recentlyRecruited;
		} else {
			return Collections.<Candidate>emptyList();
		}
	}

}