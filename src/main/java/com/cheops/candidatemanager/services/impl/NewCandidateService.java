package com.cheops.candidatemanager.services.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cheops.candidatemanager.models.Address;
import com.cheops.candidatemanager.models.Candidate;
import com.cheops.candidatemanager.models.CandidateSearchResolver;
import com.cheops.candidatemanager.models.NewCandidate;
import com.cheops.candidatemanager.repositories.NewCandidateRepository;
import com.cheops.candidatemanager.services.ICandidateService;
@Service
public class NewCandidateService implements ICandidateService {

	public String uploadDirectory = System.getProperty("user.dir") + "/uploads";
	@Autowired
	private NewCandidateRepository newCandidateRepository;
	@Override
	public List<CandidateSearchResolver> getAllCandidates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Candidate getCandidateById(int candidateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Candidate addCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public NewCandidate addNewCandidate(NewCandidate newCandidate) {
		newCandidateRepository.save(newCandidate);
		return newCandidate;
	}
	@Override
	public void updateCandidate(Candidate candidate) {
		// TODO Auto-generated method stub
		
	}
	public void updateNewCandidate(NewCandidate newCandidate) {
		// TODO Auto-generated method stub
		newCandidateRepository.save(newCandidate);
	}

	@Override
	public void deleteCandidate(int candidateId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CandidateSearchResolver> findAllByNameLikeOrSirNameLike(String name, String sirName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CandidateSearchResolver> findAllDotnet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CandidateSearchResolver> findAllJava() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CandidateSearchResolver> findAllRecruitedIn(List<Integer> applicationProcessId) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<CandidateSearchResolver> getAllCandidatesWithActiveApplicationProcess() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CandidateSearchResolver> findAllRecruited() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CandidateSearchResolver> findByExperienceGreaterThan(int experience,
			List<CandidateSearchResolver> candidates) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CandidateSearchResolver> findAllFrontend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdateCandidate(int id, @Valid Candidate candidate, @Valid Address address,
			@Valid int addressId) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void saveOrUpdateNewCandidate(int id, @Valid NewCandidate candidate, @Valid Address address,
			@Valid int addressId) {
		
			Optional<NewCandidate> tmp = newCandidateRepository.findById(id);
//			if (tmp.isPresent()) {
//				Candidate s = tmp.get();
//
//				s = candidate;
//				s.setId(id);
//
//				// update address
//				Optional<Address> tmpAddress = addressRepository.findById(addressId);
//				if (tmpAddress.isPresent()) {
//					Address s2 = tmpAddress.get();
//					s2 = address;
//					s2.setId(addressId);
//					addressRepository.save(s2);
//				}
//				//
//
//				newCandidateRepository.save(s);
//			} else {
				newCandidateRepository.save(candidate);
			//}

		}
		
	

	@Override
	public void downloadCv(String cvLink, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CandidateSearchResolver> getAllCandidatesWithoutActiveApplicationProcess() {
		// TODO Auto-generated method stub
		return null;
	}

}
