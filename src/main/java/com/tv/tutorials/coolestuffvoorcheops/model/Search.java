package com.tv.tutorials.coolestuffvoorcheops.model;

import javax.persistence.Entity;
import javax.persistence.Id;

//import org.springframework.data.annotation.Id;

@Entity
public class Search {

	@Id
	private Integer id;
	private boolean employed;
	private boolean frontender;
	private boolean java;
	private boolean dotnet;
	private int experience;
	
	public Search() {
		
	}

	public Search(boolean employed, boolean frontender, boolean java, boolean dotnet, int experience) {
		super();
		this.employed = employed;
		this.frontender = frontender;
		this.java = java;
		this.dotnet = dotnet;
		this.experience = experience;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isEmployed() {
		return employed;
	}

	public void setEmployed(boolean employed) {
		this.employed = employed;
	}

	public boolean isFrontender() {
		return frontender;
	}

	public void setFrontender(boolean frontender) {
		this.frontender = frontender;
	}

	public boolean isJava() {
		return java;
	}

	public void setJava(boolean java) {
		this.java = java;
	}

	public boolean isDotnet() {
		return dotnet;
	}

	public void setDotnet(boolean dotnet) {
		this.dotnet = dotnet;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}
	
}
