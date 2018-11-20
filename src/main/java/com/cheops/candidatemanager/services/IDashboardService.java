package com.cheops.candidatemanager.services;

import java.util.List;

import com.cheops.candidatemanager.models.Candidate;

public interface IDashboardService {

	List<Candidate> getUpcomingFirstAndSecondScreenings();

	List<Candidate> get3LatestAddedCandidates();

	List<Candidate> getLatest5RecruitedCandidates();

	List<Candidate> getLastMonthsRecruits();

}