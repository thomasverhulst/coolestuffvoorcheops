package com.tv.tutorials.coolestuffvoorcheops.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="skills")
public class Skills {

	@Id
	@Column(name="idskills")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="technicalknowlegde")
	private String technicalKnowlegde; 
	
	@Column(name="experience")
	private int experience; 
	
	@Column(name="preferredlocation")
	private String preferredLocation; 
	
	@Column(name="preferredtechnologies")
	private String preferredTechnologies; 
	
	@Column(name="dotnet")
	private boolean dotnet;
	
	@Column(name="java")
	private boolean java;
	
	@Column(name="frontend")
	private boolean frontend;

	public Skills() {
		
	}
	
	public Skills(String technicalKnowlegde, int experience, String preferredLocation, String preferredTechnologies,
			boolean dotnet, boolean java, boolean frontend) {
		super();
		this.technicalKnowlegde = technicalKnowlegde;
		this.experience = experience;
		this.preferredLocation = preferredLocation;
		this.preferredTechnologies = preferredTechnologies;
		this.dotnet = dotnet;
		this.java = java;
		this.frontend = frontend;
	}

	public String getTechnicalKnowlegde() {
		return technicalKnowlegde;
	}

	public void setTechnicalKnowlegde(String technicalKnowlegde) {
		this.technicalKnowlegde = technicalKnowlegde;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getPreferredLocation() {
		return preferredLocation;
	}

	public void setPreferredLocation(String preferredLocation) {
		this.preferredLocation = preferredLocation;
	}

	public String getPreferredTechnologies() {
		return preferredTechnologies;
	}

	public void setPreferredTechnologies(String preferredTechnologies) {
		this.preferredTechnologies = preferredTechnologies;
	}

	public boolean isDotnet() {
		return dotnet;
	}

	public void setDotnet(boolean dotnet) {
		this.dotnet = dotnet;
	}

	public boolean isJava() {
		return java;
	}

	public void setJava(boolean java) {
		this.java = java;
	}

	public boolean isFrontend() {
		return frontend;
	}

	public void setFrontend(boolean frontend) {
		this.frontend = frontend;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	 public int compareTo(Skills skills) {
	    return this.getId().compareTo(skills.getId());
	 } 
// toegevoegd in de hoopd at de update zou werken, niet het geval
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + (dotnet ? 1231 : 1237);
//		result = prime * result + experience;
//		result = prime * result + (frontend ? 1231 : 1237);
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + (java ? 1231 : 1237);
//		result = prime * result + ((preferredLocation == null) ? 0 : preferredLocation.hashCode());
//		result = prime * result + ((preferredTechnologies == null) ? 0 : preferredTechnologies.hashCode());
//		result = prime * result + ((technicalKnowlegde == null) ? 0 : technicalKnowlegde.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Skills other = (Skills) obj;
//		if (dotnet != other.dotnet)
//			return false;
//		if (experience != other.experience)
//			return false;
//		if (frontend != other.frontend)
//			return false;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (java != other.java)
//			return false;
//		if (preferredLocation == null) {
//			if (other.preferredLocation != null)
//				return false;
//		} else if (!preferredLocation.equals(other.preferredLocation))
//			return false;
//		if (preferredTechnologies == null) {
//			if (other.preferredTechnologies != null)
//				return false;
//		} else if (!preferredTechnologies.equals(other.preferredTechnologies))
//			return false;
//		if (technicalKnowlegde == null) {
//			if (other.technicalKnowlegde != null)
//				return false;
//		} else if (!technicalKnowlegde.equals(other.technicalKnowlegde))
//			return false;
//		return true;
//	}
//	
	
}
