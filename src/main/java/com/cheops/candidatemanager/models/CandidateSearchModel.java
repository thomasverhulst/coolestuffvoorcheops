package com.cheops.candidatemanager.models;

public class CandidateSearchModel {

	private String id;
	private String link;

  public CandidateSearchModel() {
  }

	public CandidateSearchModel(String id, String link) {
		this.id = id;
		this.link = link;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
