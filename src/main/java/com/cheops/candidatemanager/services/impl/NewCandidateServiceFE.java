package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.exceptions.CandidateDoesNotExistException;
import com.cheops.candidatemanager.models.*;
import com.cheops.candidatemanager.repositories.NewCandidateFERepository;
import com.cheops.candidatemanager.services.ICandidateServiceFE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class NewCandidateServiceFE implements ICandidateServiceFE {

  private Locale locale = LocaleContextHolder.getLocale();

  @Autowired
  private NewCandidateFERepository newCandidateFERepository;

  @Autowired
  private MessageSource messageSource;

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
      throw new CandidateDoesNotExistException(messageSource.getMessage("candidate.doesNotExist", null, locale));
    }

    newCandidateFERepository.save(candidate);
  }

  @Override
  public void deleteCandidate(NewCandidateFE candidate) throws CandidateDoesNotExistException {
	  if (!newCandidateFERepository.existsById(candidate.getId())) {
      throw new CandidateDoesNotExistException(messageSource.getMessage("candidate.doesNotExist", null, locale));
    }

    newCandidateFERepository.delete(candidate);
  }

}
