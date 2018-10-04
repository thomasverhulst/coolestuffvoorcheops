package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.List;

import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.models.CandidateSearchResolver;

public interface ICandidateService {

	List<CandidateSearchResolver> getAllCandidates();

	Candidate getCandidateById(int candidateId);

	Candidate addCandidate(Candidate candidate);

	void updateCandidate(Candidate candidate);

	void deleteCandidate(int candidateId);
}
