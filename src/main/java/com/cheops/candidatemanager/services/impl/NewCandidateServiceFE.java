package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.models.*;
import com.cheops.candidatemanager.repositories.NewCandidateFERepository;
import com.cheops.candidatemanager.repositories.NewCandidateRepository;
import com.cheops.candidatemanager.services.ICandidateServiceFE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class NewCandidateServiceFE implements ICandidateServiceFE {

  @Autowired
  private NewCandidateFERepository newCandidateFERepository;

	public String uploadDirectory = System.getProperty("user.dir") + "/uploads";

	@Override
	public NewCandidateFE addCandidate(NewCandidateFE candidate) {
		newCandidateFERepository.save(candidate);
		return candidate;
	}

//	public NewCandidate addNewCandidate(NewCandidate newCandidate) {
//		newCandidateRepository.save(newCandidate);
//		return newCandidate;
//	}
//
//	public void updateNewCandidate(NewCandidate newCandidate) {
//		// TODO Auto-generated method stub
//		newCandidateRepository.save(newCandidate);
//	}
//
//	public void saveOrUpdateNewCandidate(int id, @Valid NewCandidate candidate, @Valid Address address,
//			@Valid int addressId) {
//
//			Optional<NewCandidate> tmp = newCandidateRepository.findById(id);
////			if (tmp.isPresent()) {
////				Candidate s = tmp.get();
////
////				s = candidate;
////				s.setId(id);
////
////				// update address
////				Optional<Address> tmpAddress = addressRepository.findById(addressId);
////				if (tmpAddress.isPresent()) {
////					Address s2 = tmpAddress.get();
////					s2 = address;
////					s2.setId(addressId);
////					addressRepository.save(s2);
////				}
////				//
////
////				newCandidateRepository.save(s);
////			} else {
//				newCandidateRepository.save(candidate);
//			//}
//
//	}

}
