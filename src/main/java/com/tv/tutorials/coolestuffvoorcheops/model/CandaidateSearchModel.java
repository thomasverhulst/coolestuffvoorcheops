package com.tv.tutorials.coolestuffvoorcheops.model;

public class CandaidateSearchModel {

	private String id; 
	
	private String link;

	public CandaidateSearchModel(String id, String link) {
		super();
		this.id = id;
		this.link = link;
	}
	
	public CandaidateSearchModel(){
		
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
