package com.cheops.candidatemanager.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "skill")
public class Skill implements Serializable {

	@Id
	@Column(name = "idskill")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

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

  @OneToMany(mappedBy = "skill", orphanRemoval = true, cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
  private List<SkillTechnology> technologies;

  @Column(name = "extra")
  @Size(max = 200, message = "{skillextra.size}")
  private String extra;

  @Transient
  List<String> locationNames = Arrays.asList("Buenos Aires", "Córdoba", "La Plata");

	public Skill() {
	}

  public Skill(boolean dotnet, boolean java, boolean frontend, double experience, String preferredLocation, List<SkillTechnology> technologies, @Size(max = 200, message = "{skillextra.size}") String extra) {
    this.dotnet = dotnet;
    this.java = java;
    this.frontend = frontend;
    this.experience = experience;
    this.preferredLocation = preferredLocation;
    this.technologies = technologies;
    this.extra = extra;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
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

  public List<SkillTechnology> getTechnologies() {
    return technologies;
  }

  public void setTechnologies(List<SkillTechnology> technologies) {
    this.technologies = technologies;
  }

  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

  public List<String> getLocationNames() {
    return locationNames;
  }

  public void setLocationNames(List<String> locationNames) {
    this.locationNames = locationNames;
  }

  public void addSkillTechnology(SkillTechnology skillTechnology) {
	  if (this.technologies == null) this.technologies = new ArrayList<>();
	  if (skillTechnology.getTechnology() == null) skillTechnology.setTechnology(new Technology());
    skillTechnology.setSkill(this);
    this.technologies.add(skillTechnology);
  }

  public void removeSkillTechnology(int index) {
    if (this.technologies != null) this.technologies.remove(index);
  }

}
