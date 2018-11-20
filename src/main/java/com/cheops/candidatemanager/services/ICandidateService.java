package com.cheops.candidatemanager.services;

import com.cheops.candidatemanager.models.Candidate;

import java.util.Collection;

public interface ICandidateService {

	Candidate getCandidateById(int candidateId);

	Candidate addCandidate(Candidate candidate);

	void saveCandidate(Candidate candidate);

	void deleteCandidate(Candidate candidate);

  Collection<Candidate> getLast5Recruited();

  Collection<Candidate> getLastMonthsRecruits();

  Collection<Candidate> getUpcomingMonthsFirstScreenings();

  Collection<Candidate> getUpcomingMonthsTechnicalScreenings();

  Collection<Candidate> get3LatestAddedCandidates();

}
