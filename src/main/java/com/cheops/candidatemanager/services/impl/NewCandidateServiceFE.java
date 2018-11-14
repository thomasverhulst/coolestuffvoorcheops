package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.exceptions.CandidateDoesNotExistException;
import com.cheops.candidatemanager.models.*;
import com.cheops.candidatemanager.repositories.NewCandidateFERepository;
import com.cheops.candidatemanager.services.ICandidateServiceFE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewCandidateServiceFE implements ICandidateServiceFE {

  @Autowired
  private NewCandidateFERepository newCandidateFERepository;

	@Override
	public NewCandidateFE getCandidateById(int candidateId) {
		return newCandidateFERepository.findById(candidateId).get();
	}

  @Override
	public NewCandidateFE addCandidate(NewCandidateFE candidate) {
		newCandidateFERepository.save(candidate);
		return candidate;
	}

	@Override
  public void saveCandidate(NewCandidateFE candidate) throws CandidateDoesNotExistException {
    if (!newCandidateFERepository.existsById(candidate.getId())) {
      throw new CandidateDoesNotExistException("This candidate doesn't exist.");
    }

    newCandidateFERepository.save(candidate);
  }

}
