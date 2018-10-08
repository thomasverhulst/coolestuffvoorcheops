package com.tv.tutorials.coolestuffvoorcheops.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.models.Candidate;

public interface CandidateRepository extends CrudRepository<Candidate, Integer> {
	// List<Address> findByStreetNameAndPostalCode(String streetName, String
	// postalCode);
	// List<Candidate> findAllByFirstNameLikeOrLastNameLike(String name, String
	// sirName);
	// findAllByFirstNameLikeOrLastNameLike

	List<Candidate> findAllByNameLikeOrSirNameLike(String name, String sirName);
	// List<Candidate> findAllByApplicationProcessIdGreaterThan(int i) ;//w
	// findallByapplicationProcessId(<0)
	// List<Candidate> findByAgeIn(Collection<Age> ages)
	// List<ApplicationProcess> finAlldByIsRecrutedGreaterThan(int i);

	// Iterable<Candidate> findAllByskillsIdIn(List<Integer> skillsId);

	List<Candidate> findAllBySkillsIdIn(List<Integer> skillsIds);

	List<Candidate> findAllByApplicationProcessIdIn(List<Integer> applicationProcessIds);

	Candidate findByapplicationProcessId(Integer id);

	// @overload
	// ArrayList<Candidate> findAllByApplicationProcessIdIn(List<ApplicationProcess>
	// applicationProcessIdsRecruited);

	// Iterable<Candidate> findAllBySkillsIdIn(List<Integer> skillsIds);
	// Page<Candidate> findAll(Pageable pageRequest);

}
