package com.cheops.candidatemanager.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.cheops.candidatemanager.models.Address;
import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.models.CandidateSearchResolver;

public interface ICandidateService {

	List<CandidateSearchResolver> getAllCandidates();

	Candidate getCandidateById(int candidateId);

	Candidate addCandidate(Candidate candidate);

	void updateCandidate(Candidate candidate);

	void deleteCandidate(int candidateId);

	List<CandidateSearchResolver> findAllByNameLikeOrSirNameLike(String name, String sirName);

	List<CandidateSearchResolver> findAllDotnet();

	List<CandidateSearchResolver> findAllJava();

	List<CandidateSearchResolver> findAllRecruitedIn(List<Integer> applicationProcessId);

	List<CandidateSearchResolver> getAllCandidatesWithoutActiveApplicationProcess();

	List<CandidateSearchResolver> getAllCandidatesWithActiveApplicationProcess();

	List<CandidateSearchResolver> findAllRecruited();

	List<CandidateSearchResolver> findByExperienceGreaterThan(int experience, List<CandidateSearchResolver> candidates);

	List<CandidateSearchResolver> findAllFrontend();

	void saveOrUpdateCandidate(int id, @Valid Candidate candidate, @Valid Address address, @Valid int addressId);

	void downloadCv(String cvLink, HttpServletResponse response) throws IOException;

	
}
