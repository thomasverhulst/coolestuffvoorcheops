package com.cheops.candidatemanager.models;

import javax.persistence.*;

@Entity
@Table(name = "technology")
public class Technology {

  @Id
  @Column(name = "idtechnology")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "name")
  private String name;

  public Technology() {
  }

  public Technology(String name) {
    super();
    this.name = name;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
