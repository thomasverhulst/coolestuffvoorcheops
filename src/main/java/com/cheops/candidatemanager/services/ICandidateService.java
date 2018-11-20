package com.cheops.candidatemanager.services;

import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.models.CandidateSearchResolver;

import java.util.Collection;
import java.util.List;

public interface ICandidateService {

	Candidate getCandidateById(int candidateId);

	Candidate addCandidate(Candidate candidate);

	void saveCandidate(Candidate candidate);

	void deleteCandidate(Candidate candidate);

  List<CandidateSearchResolver> getAllCandidates();

  Collection<Candidate> getLast5Recruited();

  Collection<Candidate> getLastMonthsRecruits();

  Collection<Candidate> getUpcomingMonthsFirstScreenings();

  Collection<Candidate> getUpcomingMonthsTechnicalScreenings();

  Collection<Candidate> get3LatestAddedCandidates();

  List<CandidateSearchResolver> getAllExEmployees();

  List<CandidateSearchResolver> getAllNotRecruitedCandidates();

  List<CandidateSearchResolver> findAllPreferredlocationContaining(List<String> cities);

  List<CandidateSearchResolver> findAllByExperienceLessThan(int i);

  List<CandidateSearchResolver> findAllByExperienceGreaterThanAndExperienceLessThan(int i, int j);

  List<CandidateSearchResolver> findAllByExperienceGreaterThan(int i);

  Collection<CandidateSearchResolver> findAllBySkillsId(List<Integer> skillsIds);

  List<CandidateSearchResolver> findAllByIdIn(List<Integer> candidateIdList2);

  List<Candidate> findAllById();

  List<CandidateSearchResolver> findAllByNameLikeOrLastNameLike(String name, String lastName);

  List<CandidateSearchResolver> findAllDotnet();

  List<CandidateSearchResolver> findAllJava();

  List<CandidateSearchResolver> findAllFrontend();

  List<CandidateSearchResolver> findAllRecruitedIn(List<Integer> applicationProcessId);

  List<CandidateSearchResolver> fillExpertiseAndStatus(List<Candidate> candidates);

}
