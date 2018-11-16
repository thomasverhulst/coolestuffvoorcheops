package com.cheops.candidatemanager.services;

import com.cheops.candidatemanager.models.NewCandidateFE;

public interface ICandidateServiceFE {

	NewCandidateFE getCandidateById(int candidateId);

	NewCandidateFE addCandidate(NewCandidateFE candidate);

	void saveCandidate(NewCandidateFE candidate);

	void deleteCandidate(NewCandidateFE candidate);

}
