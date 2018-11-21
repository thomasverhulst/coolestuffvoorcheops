package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.exceptions.CandidateDoesNotExistException;
import com.cheops.candidatemanager.models.*;
import com.cheops.candidatemanager.repositories.CandidateRepository;
import com.cheops.candidatemanager.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
  private ISkillService skillService;

  @Autowired
  private IApplicationProcessService applicationProcessService;

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
	public List<CandidateSearchResolver> getAllCandidates() {
		List<Candidate> candidates = new ArrayList<>();
		candidateRepository.findAll().forEach(candidates::add);
		return fillExpertiseAndStatus(candidates);
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
//		List<Integer> candidateIds = meetingList.stream().map(Meeting::getCandidate).collect(Collectors.toList());
		List<Integer> candidateIds = meetingList.stream().map(m -> m.getCandidate().getId()).collect(Collectors.toList());
		return candidateRepository.findAllByIdIn(candidateIds);
	}

	@Override
	public Collection<Candidate> getUpcomingMonthsTechnicalScreenings() {
	  List<Meeting> meetingList = meetingService.getUpcomingMonthsTechnicalScreenings();
//		List<Integer> candidateIds = meetingList.stream().map(Meeting::getCandidate).collect(Collectors.toList());
		List<Integer> candidateIds = meetingList.stream().map(m -> m.getCandidate().getId()).collect(Collectors.toList());
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

	@Override
	public List<CandidateSearchResolver> getAllExEmployees() {
		List<WorkHistory> workHistoryExEmployees = workHistoryService.getAllExEmployees();
		List<Integer> candidateIds = workHistoryExEmployees.stream().map(WorkHistory::getCandidateId).collect(Collectors.toList());
		List<Candidate> candidates = candidateRepository.findAllByIdIn(candidateIds);
		return fillExpertiseAndStatus(candidates);
	}

  @Override
  public List<CandidateSearchResolver> getAllNotRecruitedCandidates() {
    List<Integer> applicationProcessIds = applicationProcessService.getAllNotRecuitedCandidates();
    List<Candidate> candidates = candidateRepository.findAllByApplicationProcessIdIn(applicationProcessIds);
    return fillExpertiseAndStatus(candidates);
  }

  @Override
	public List<CandidateSearchResolver> findAllPreferredlocationContaining(List<String> cities) {
		List<Integer> skillsIds= skillService.findAllByPreferredlocationContaining( cities);
		List<Candidate> candidates = candidateRepository.findAllBySkillIdIn(skillsIds);
		return fillExpertiseAndStatus(candidates);
	}

	@Override
	public List<CandidateSearchResolver> findAllByExperienceLessThan(int i) {
		List<Integer> skillsIds= skillService.findAllByExperienceLessThan( i);
		List<Candidate> candidates = candidateRepository.findAllBySkillIdIn(skillsIds);
		return fillExpertiseAndStatus(candidates);
	}

	@Override
	public List<CandidateSearchResolver> findAllByExperienceGreaterThanAndExperienceLessThan(int i, int j) {
		List<Integer> skillsIds= skillService.findAllByExperienceGreaterThanAndExperienceLessThan( i, j);
		List<Candidate> candidates = candidateRepository.findAllBySkillIdIn(skillsIds);
		return fillExpertiseAndStatus(candidates);
	}

	@Override
	public List<CandidateSearchResolver> findAllByExperienceGreaterThan(int i) {
		List<Integer> skillsIds= skillService.findAllByExperienceGreaterThan( i);
		List<Candidate> candidates = candidateRepository.findAllBySkillIdIn(skillsIds);
		return fillExpertiseAndStatus(candidates);
	}

	@Override
	public Collection<CandidateSearchResolver> findAllBySkillsId(List<Integer> skillsIds) {
		List<Candidate> candidates = candidateRepository.findAllBySkillIdIn(skillsIds);
		return fillExpertiseAndStatus(candidates);
	}

	@Override
	public List<CandidateSearchResolver> findAllByIdIn(List<Integer> candidateIdList2) {
		List<Candidate> candidateList=candidateRepository.findAllByIdIn(candidateIdList2);
		return fillExpertiseAndStatus(candidateList);
	}

	@Override
	public List<Candidate> findAllById() {
		return (List<Candidate>) candidateRepository.findAll();
	}

  @Override
  public List<CandidateSearchResolver> findAllByNameLikeOrLastNameLike(String name, String lastName) {
    List<Candidate> candidates = candidateRepository.findAllByNameLikeOrLastNameLike(name, lastName);
    return fillExpertiseAndStatus(candidates);
  }

  @Override
  public List<CandidateSearchResolver> findAllDotnet() {
    List<Integer> skillsIds = skillService.findAllDotnet();
    List<Candidate> candidates = candidateRepository.findAllBySkillIdIn(skillsIds);
    return fillExpertiseAndStatus(candidates);
  }

  @Override
  public List<CandidateSearchResolver> findAllJava() {
    List<Integer> skillsIds = skillService.findAllJava();
    List<Candidate> candidates = candidateRepository.findAllBySkillIdIn(skillsIds);
    return fillExpertiseAndStatus(candidates);
  }

  @Override
  public List<CandidateSearchResolver> findAllFrontend() {
    List<Integer> skillsIds = skillService.findAllFrontend();
    List<Candidate> candidates = candidateRepository.findAllBySkillIdIn(skillsIds);
    return fillExpertiseAndStatus(candidates);
  }

  @Override
  public List<CandidateSearchResolver> findAllRecruitedIn(List<Integer> applicationProcessId) {
    List<Candidate> candidates = new ArrayList<Candidate>();
    List<ApplicationProcess> t = applicationProcessService.getAllApplicationProcessById(applicationProcessId);

    if (t != null) {
      List<ApplicationProcess> applicationProcessIdsRecruited = applicationProcessService.findAllByIsRecruitedIn(t);

      for (ApplicationProcess applicationProcess : applicationProcessIdsRecruited) {
        Candidate candidate = candidateRepository.findByapplicationProcessId(applicationProcess.getId());
        if (candidate != null) candidates.add(candidate);
      }
    }

    return fillExpertiseAndStatus(candidates);
  }

	@Override
	public List<CandidateSearchResolver> fillExpertiseAndStatus(List<Candidate> candidates) {
		List<CandidateSearchResolver> candidateResolverList = new ArrayList<CandidateSearchResolver>();

		for (Candidate candidate : candidates) {
			// Everything related to the skill
			StringBuffer expertise = new StringBuffer("");

			if (candidate.getSkill() != null) {
				Skill skill = skillService.getSkillsById(candidate.getSkill().getId());

				if (skill.isDotnet()) {
					expertise.append(".Net");
					if (skill.isJava() || skill.isFrontend()) {
						expertise.append(", ");
						if (skill.isJava()) {
							expertise.append("Java");
						}
						if (skill.isFrontend()) {
							expertise.append(", ");
							expertise.append("Front-End");
						}
					}
				} else if (skill.isJava()) {
					expertise.append("Java");
					if (skill.isFrontend()) {
						expertise.append(", ");
						expertise.append("Front-End");
					}
				} else if (skill.isFrontend()) {
					expertise.append("Front-End");
				}
			}

			// ApplicationProcess Logic
			String applicationStatus = "";
			if (candidate.getApplicationProcess().getId() != 0) {
				ApplicationProcess applicationProcess = applicationProcessService.getApplicationProcessById(candidate.getApplicationProcess().getId());

				if (applicationProcess.getToBeInvitedForFirstConversation()) {
					applicationStatus = "Eerste interview";
				} else if (applicationProcess.getToBeInvitedForTechnicalConversation()) {
					applicationStatus = "Technisch interview";
				} else if (applicationProcess.getIsRecruited()) {
					applicationStatus = "In dienst";
				} else {
					applicationStatus = "(Nog) niet uitgenodigd";
				}
			}

			CandidateSearchResolver candidateSearchResolver = new CandidateSearchResolver(candidate, expertise.toString(), applicationStatus);
			candidateResolverList.add(candidateSearchResolver);
		}

		return candidateResolverList;
	}
}
