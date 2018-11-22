package com.cheops.candidatemanager.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

  @Id
  @Column(name = "idcontact")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "contactdate")
  private Date contactDate;

  @Column(name = "contactChannel")
  @Size(max = 45, message = "{contactchannel.size}")
  private String contactChannel;

  public Contact() {
  }

  public Contact(Date contactDate, @Size(max = 45, message = "{contactchannel.size}") String contactChannel) {
    this.contactDate = contactDate;
    this.contactChannel = contactChannel;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getContactDate() {
    return contactDate;
  }

  public void setContactDate(Date contactDate) {
    this.contactDate = contactDate;
  }

  public String getContactChannel() {
    return contactChannel;
  }

  public void setContactChannel(String contactChannel) {
    this.contactChannel = contactChannel;
  }

}
