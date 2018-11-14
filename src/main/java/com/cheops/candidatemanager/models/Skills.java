package com.cheops.candidatemanager.models;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "skills")
public class Skills {

	@Id
	@Column(name = "idskills")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "dotnet")
	private boolean dotnet;

	@Column(name = "java")
	private boolean java;

	@Column(name = "frontend")
	private boolean frontend;

  @Column(name = "experience")
  private double experience;

  @Column(name = "preferredlocation")
  private String preferredLocation;

  @OneToMany
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinTable(name = "skills_technology", joinColumns = @JoinColumn(name = "skills_id"), inverseJoinColumns = @JoinColumn(name = "technology_id"))
  private List<Technology> preferedTechnologies  = new ArrayList<>();;

  @Column(name = "extra")
  private String extra;

	public Skills() {
	}

  public Skills(boolean dotnet, boolean java, boolean frontend, double experience, String preferredLocation, List<Technology> preferedTechnologies, String extra) {
	  super();
    this.dotnet = dotnet;
    this.java = java;
    this.frontend = frontend;
    this.experience = experience;
    this.preferredLocation = preferredLocation;
    this.preferedTechnologies = preferedTechnologies;
    this.extra = extra;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public double getExperience() {
    return experience;
  }

  public void setExperience(double experience) {
    this.experience = experience;
  }

  public String getPreferredLocation() {
    return preferredLocation;
  }

  public void setPreferredLocation(String preferredLocation) {
    this.preferredLocation = preferredLocation;
  }

  public List<Technology> getPreferedTechnologies() {
    return preferedTechnologies;
  }

  public void setPreferedTechnologies(List<Technology> preferedTechnologies) {
    this.preferedTechnologies = preferedTechnologies;
  }

  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

  public int compareTo(Skills skills) {
		return this.getId().compareTo(skills.getId());
	}

}
