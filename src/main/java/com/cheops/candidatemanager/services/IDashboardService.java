package com.cheops.candidatemanager.services;

import java.util.List;

import com.cheops.candidatemanager.models.Candidate;

public interface IDashboardService {

	// komende gesprekken
	List<Candidate> getUpcommingFirstAndSecondScreenings();

	List<Candidate> getLastMonthsRecruities();

	//ok
	List<Candidate> get3LatestAddedCandidates();

	List<Candidate> getLatest5RecruitedCandidates();

}