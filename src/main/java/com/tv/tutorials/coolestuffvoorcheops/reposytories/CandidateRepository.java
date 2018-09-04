package com.tv.tutorials.coolestuffvoorcheops.reposytories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;

public interface CandidateRepository  extends CrudRepository<Candidate, Integer>  {
	//List<Address> findByStreetNameAndPostalCode(String streetName, String postalCode);
	//List<Candidate> findAllByFirstNameLikeOrLastNameLike(String name, String sirName);
	//findAllByFirstNameLikeOrLastNameLike

	List<Candidate> findAllByNameLikeOrSirNameLike(String name, String sirName);
}
