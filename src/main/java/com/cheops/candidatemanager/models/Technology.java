package com.cheops.candidatemanager.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "technology")
public class Technology {

  @Id
  @Column(name = "idtechnology")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "name")
  private String name;

//  @OneToMany(mappedBy = "technology", cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//  private List<SkillTechnology> technologies;

  public Technology() {
  }

//  public Technology(String name, List<SkillTechnology> technologies) {
  public Technology(String name) {
    super();
    this.name = name;
//    this.technologies = technologies;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

//  public List<SkillTechnology> getTechnologies() {
//    return technologies;
//  }
//
//  public void setTechnologies(List<SkillTechnology> technologies) {
//    this.technologies = technologies;
//  }

}
