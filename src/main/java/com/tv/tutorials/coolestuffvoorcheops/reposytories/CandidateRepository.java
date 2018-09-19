package com.tv.tutorials.coolestuffvoorcheops.reposytories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;

public interface CandidateRepository  extends CrudRepository<Candidate, Integer>  {
	//List<Address> findByStreetNameAndPostalCode(String streetName, String postalCode);
	//List<Candidate> findAllByFirstNameLikeOrLastNameLike(String name, String sirName);
	//findAllByFirstNameLikeOrLastNameLike

	List<Candidate> findAllByNameLikeOrSirNameLike(String name, String sirName);
	//List<Candidate>  findAllByApplicationProcessIdGreaterThan(int i) ;//w    findallByapplicationProcessId(<0)
	//List<Candidate> findByAgeIn(Collection<Age> ages)
	//List<ApplicationProcess> finAlldByIsRecrutedGreaterThan(int i);

	//Iterable<Candidate> findAllByskillsIdIn(List<Integer> skillsId);

	Iterable<Candidate> findAllBySkillsIdIn(List<Integer> skillsIds);

	Iterable<Candidate> findAllByApplicationProcessIdIn(List<Integer> applicationProcessIds);

	//Iterable<Candidate> findAllBySkillsIdIn(List<Integer> skillsIds);
	//Page<Candidate> findAll(Pageable pageRequest);
	  
	
}
