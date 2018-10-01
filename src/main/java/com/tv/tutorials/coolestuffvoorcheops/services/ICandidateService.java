package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.List;

import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;

public interface ICandidateService {

	List<Candidate> getAllCandidates();

	Candidate getCandidateById(int candidateId);

	Candidate addCandidate(Candidate candidate);

	void updateCandidate(Candidate candidate);

	void deleteCandidate(int candidateId);
}
