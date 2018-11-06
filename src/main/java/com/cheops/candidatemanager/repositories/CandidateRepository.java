package com.cheops.candidatemanager.repositories;

import java.util.List;

import com.cheops.candidatemanager.models.Candidate;

import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate, Integer> {
	
	// findAllByFirstNameLikeOrLastNameLike

	List<Candidate> findAllByNameLikeOrSirNameLike(String name, String sirName);
	// List<Candidate> findAllByApplicationProcessIdGreaterThan(int i) ;//w
	// findallByapplicationProcessId(<0)
	// List<Candidate> findByAgeIn(Collection<Age> ages)


	List<Candidate> findAllBySkillsIdIn(List<Integer> skillsIds);

	List<Candidate> findAllByApplicationProcessIdIn(List<Integer> applicationProcessIds);

	Candidate findByapplicationProcessId(Integer id);

	// @overload
	// ArrayList<Candidate> findAllByApplicationProcessIdIn(List<ApplicationProcess>

	List<Candidate> findFirst3ByOrderByIdDesc();

}

	
