package com.tv.tutorials.coolestuffvoorcheops.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tv.tutorials.coolestuffvoorcheops.model.Candidate;
import com.tv.tutorials.coolestuffvoorcheops.reposytories.CandidateRepository;

@Service
public class CandidateService implements ICandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	
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
		candidateRepository.save(candidate);		
	}

	@Override
	public void deleteCandidate(int candidateId) {
		candidateRepository.delete(getCandidateById(candidateId));
	}

	public List<Candidate> findAllByNameLikeOrSirNameLike(String name, String sirName) {
		List<Candidate> list =  candidateRepository.findAllByNameLikeOrSirNameLike(name, sirName) ;			
		return list;
	}

	//public List<Candidate> findAllByNameLikeOrSirNameLike(String name, String sirName) {
		// TODO Auto-generated method stub
	//	return null;
	//}

}
