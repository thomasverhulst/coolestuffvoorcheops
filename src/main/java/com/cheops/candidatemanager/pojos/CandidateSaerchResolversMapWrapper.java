package com.cheops.candidatemanager.pojos;

import java.util.List;
import java.util.Map;

import com.cheops.candidatemanager.models.CandidateSearchResolver;

public class CandidateSaerchResolversMapWrapper {

	Map <String, List<CandidateSearchResolver>>candidateresolversListMap;

	public Map<String, List<CandidateSearchResolver>> getCandidateresolversListMap() {
		return candidateresolversListMap;
	}

	public void setCandidateresolversListMap(Map<String, List<CandidateSearchResolver>> candidateresolversListMap) {
		this.candidateresolversListMap = candidateresolversListMap;
	}
	
}
