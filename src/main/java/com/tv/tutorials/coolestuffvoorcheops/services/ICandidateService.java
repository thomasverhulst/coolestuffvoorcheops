package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.List;

import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;

public interface ICandidateService {

	List<Candidate> getAllCandidates();

	Candidate getCandidateById(int candidateId);

	Candidate addCandidate(Candidate candidate);

	void updateCandidate(Candidate candidate);

	void deleteCandidate(int candidateId);
}
