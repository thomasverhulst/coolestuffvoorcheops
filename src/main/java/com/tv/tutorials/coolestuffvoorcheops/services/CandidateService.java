package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.tutorials.coolestuffvoorcheops.model.Address;
import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.AddressRepository;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.CandidateRepository;

@Service
public class CandidateService implements ICandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Override
	public List<Candidate> getAllCandidates() {
		List<Candidate> list = new ArrayList<>();
		candidateRepository.findAll().forEach(e -> list.add(e));
		return list;
	}

	@Override
	public Candidate getCandidateById(int candidateId) {
		Candidate e = candidateRepository.findById(candidateId).get();
		return e;
	}

	@Override
	public Candidate addCandidate(Candidate candidate) {
		candidateRepository.save(candidate);
		return candidate;
	}

	@Override
	public void updateCandidate(Candidate candidate) {
		candidateRepository.save(candidate)	;
	}

	@Override
	public void deleteCandidate(int candidateId) {
		candidateRepository.delete(getCandidateById(candidateId));
	}

	public List<Candidate> findAllByNameLikeOrSirNameLike(String name, String sirName) {
		List<Candidate> list =  candidateRepository.findAllByNameLikeOrSirNameLike(name, sirName) ;			
		return list;
	}

	public void saveOrUpdateCandidate(int id, @Valid Candidate candidate, @Valid Address address,  @Valid int addressId) {
		Optional<Candidate> tmp = candidateRepository.findById(id);
		if (tmp.isPresent() ) {
			Candidate s =tmp.get();
			
			s= candidate;
			s.setId(id);
			
			// update address
			System.out.println("addressidttt = "+addressId);
			Optional<Address> tmpAddress = addressRepository.findById(addressId);
			if (tmpAddress.isPresent() ) {
				Address s2 =tmpAddress.get();
				s2= address;
				s2.setId(addressId);
				addressRepository.save(s2);
			}
			//
			
			candidateRepository.save(s);
			System.out.println("het updaten is gelukt");
		}
		else {
			candidateRepository.save(candidate);
		}
		
	}

}
