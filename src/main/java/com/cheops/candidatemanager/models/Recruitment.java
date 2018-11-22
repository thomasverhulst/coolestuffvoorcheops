package com.cheops.candidatemanager.models;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recruitment")
public class Recruitment implements Serializable {

  @Id
  @Column(name = "idrecruitment")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "notrecruited")
  private boolean notRecruited;

  @Column(name = "notrecruitedfeedback")
  @Size(max = 400, message = "{feedback.size}")
  private String notRecruitedFeedback;

  @Column(name = "notrecruitedfeedbacklink")
  private String notRecruitedFeedbackLink;

  @Transient
  private MultipartFile file;

  @OneToMany(mappedBy = "recruitment", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<EmploymentPeriod> employmentPeriods;

  public Recruitment() {
  }

  public Recruitment(boolean notRecruited, @Size(max = 400, message = "{feedback.size}") String notRecruitedFeedback, String notRecruitedFeedbackLink, List<EmploymentPeriod> employmentPeriods) {
    this.notRecruited = notRecruited;
    this.notRecruitedFeedback = notRecruitedFeedback;
    this.notRecruitedFeedbackLink = notRecruitedFeedbackLink;
    this.employmentPeriods = employmentPeriods;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public boolean isNotRecruited() {
    return notRecruited;
  }

  public void setNotRecruited(boolean notRecruited) {
    this.notRecruited = notRecruited;
  }

  public String getNotRecruitedFeedback() {
    return notRecruitedFeedback;
  }

  public void setNotRecruitedFeedback(String notRecruitedFeedback) {
    this.notRecruitedFeedback = notRecruitedFeedback;
  }

  public String getNotRecruitedFeedbackLink() {
    return notRecruitedFeedbackLink;
  }

  public void setNotRecruitedFeedbackLink(String notRecruitedFeedbackLink) {
    this.notRecruitedFeedbackLink = notRecruitedFeedbackLink;
  }

  public MultipartFile getFile() {
    return file;
  }

  public void setFile(MultipartFile file) {
    this.file = file;
  }

  public List<EmploymentPeriod> getEmploymentPeriods() {
    return employmentPeriods;
  }

  public void setEmploymentPeriods(List<EmploymentPeriod> employmentPeriods) {
    this.employmentPeriods = employmentPeriods;
  }

  public void addEmploymentPeriod(EmploymentPeriod employmentPeriod) {
    if (this.getEmploymentPeriods() == null) this.employmentPeriods = new ArrayList<>();
    employmentPeriod.setRecruitment(this);
    this.employmentPeriods.add(employmentPeriod);
  }

  public void removeEmploymentPeriod(int index) {
    if (this.employmentPeriods != null) this.employmentPeriods.remove(index);
  }
}