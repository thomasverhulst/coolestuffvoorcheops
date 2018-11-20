package com.cheops.candidatemanager.models;

import javax.persistence.*;

@Entity
@Table(name = "skill_technology")
public class SkillTechnology {

  @Id
  @Column(name = "idskilltechnology")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @ManyToOne
  @JoinColumn(name = "skill_id") //, insertable = false, updatable = false)
  private Skill skill;

  @ManyToOne(cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinColumn(name = "technology_id")
  private Technology technology;

  @Column(name = "preferred")
  private boolean preferred;

  @Column(name = "years")
  private double years;

  public SkillTechnology(){
  }

  public SkillTechnology(Skill skill, Technology technology, boolean preferred, double years) {
    this.skill = skill;
    this.technology = technology;
    this.preferred = preferred;
    this.years = years;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Skill getSkill() {
    return skill;
  }

  public void setSkill(Skill skill) {
    this.skill = skill;
  }

  public Technology getTechnology() {
    return technology;
  }

  public void setTechnology(Technology technology) {
    this.technology = technology;
  }

  public boolean isPreferred() {
    return preferred;
  }

  public void setPreferred(boolean preferred) {
    this.preferred = preferred;
  }

  public double getYears() {
    return years;
  }

  public void setYears(double years) {
    this.years = years;
  }

}
