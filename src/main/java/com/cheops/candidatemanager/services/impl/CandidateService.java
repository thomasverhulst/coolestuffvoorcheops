package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.exceptions.CandidateDoesNotExistException;
import com.cheops.candidatemanager.models.*;
import com.cheops.candidatemanager.repositories.CandidateRepository;
import com.cheops.candidatemanager.services.ICandidateService;
import com.cheops.candidatemanager.services.IMeetingService;
import com.cheops.candidatemanager.services.IWorkHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class CandidateService implements ICandidateService {

  private Locale locale = LocaleContextHolder.getLocale();

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private IWorkHistoryService workHistoryService;

  @Autowired
  private IMeetingService meetingService;

  @Autowired
  private MessageSource messageSource;

	@Override
	public Candidate getCandidateById(int candidateId) {
		return candidateRepository.findById(candidateId).orElse(null);
	}

  @Override
	public Candidate addCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
		return candidate;
	}

	@Override
  public void saveCandidate(Candidate candidate) throws CandidateDoesNotExistException {
    if (!candidateRepository.existsById(candidate.getId())) {
      throw new CandidateDoesNotExistException(messageSource.getMessage("candidate.doesNotExist", null, locale));
    }

    candidateRepository.save(candidate);
  }

  @Override
  public void deleteCandidate(Candidate candidate) throws CandidateDoesNotExistException {
	  if (!candidateRepository.existsById(candidate.getId())) {
      throw new CandidateDoesNotExistException(messageSource.getMessage("candidate.doesNotExist", null, locale));
    }

    candidateRepository.delete(candidate);
  }

  @Override
  public Collection<Candidate> getLast5Recruited() {
    List<WorkHistory> workHistoryList = workHistoryService.getLast5Recruited();
    List<Integer> candidateIds = workHistoryList.stream().map(WorkHistory::getCandidateId).collect(Collectors.toList());
		return candidateRepository.findAllByIdIn(candidateIds);
  }

  @Override
  public Collection<Candidate> getLastMonthsRecruits() {
	  List<WorkHistory> workHistoryList = workHistoryService.getLastMonthsRecruits();
    List<Integer> candidateIds = workHistoryList.stream().map(WorkHistory::getCandidateId).collect(Collectors.toList());
    return candidateRepository.findAllByIdIn(candidateIds);
	}

	@Override
	public Collection<Candidate> getUpcomingMonthsFirstScreenings() {
    List<Meeting> meetingList = meetingService.getUpcomingMonthsFirstScreenings();
		List<Integer> candidateIds = meetingList.stream().map(Meeting::getCandidateId).collect(Collectors.toList());
		return candidateRepository.findAllByIdIn(candidateIds);
	}

	@Override
	public Collection<Candidate> getUpcomingMonthsTechnicalScreenings() {
	  List<Meeting> meetingList = meetingService.getUpcomingMonthsTechnicalScreenings();
		List<Integer> candidateIds = meetingList.stream().map(Meeting::getCandidateId).collect(Collectors.toList());
		return candidateRepository.findAllByIdIn(candidateIds);
	}

	@Override
	public Collection<Candidate> get3LatestAddedCandidates() {
	  return candidateRepository.findFirst3ByOrderByIdDesc();
//	  List<Candidate> last3Candidates = candidateRepository.findFirst3ByOrderByIdDesc();
//    if (!last3Candidates.isEmpty()) {
//      return last3Candidates;
//    }else {
//      return Collections.<Candidate>emptyList();
//    }
	}

}
