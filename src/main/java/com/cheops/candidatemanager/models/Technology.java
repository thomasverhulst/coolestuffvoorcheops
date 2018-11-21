package com.cheops.candidatemanager.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "technology")
public class Technology implements Serializable {

  @Id
  @Column(name = "idtechnology")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @Column(name = "name")
  @Size(max = 45, message = "{name.size}")
  private String name;

  public Technology() {
  }

  public Technology(@Size(max = 45, message = "{name.size}") String name) {
    this.name = name;
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

}
