package com.cheops.candidatemanager.models;

public class CandidateSearchResolver {

	private Candidate candidate;

	private String expertise;

	private String applicationStatus;

	public CandidateSearchResolver() {
		super();
	}

	public CandidateSearchResolver(Candidate candidate, String expertise, String applicationStatus) {
		super();
		this.candidate = candidate;
		this.expertise = expertise;
		this.applicationStatus = applicationStatus;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public String getExpertise() {
		return expertise;
	}

	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

}
