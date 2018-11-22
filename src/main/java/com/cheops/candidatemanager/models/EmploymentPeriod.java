package com.cheops.candidatemanager.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "employmentperiod")
public class EmploymentPeriod {

  @ManyToOne
  @JoinColumn(name = "recruitment_id")
  private Recruitment recruitment;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "startedate")
  private Date startDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Column(name = "enddate")
  private Date endDate;

  public EmploymentPeriod() {
  }

  public EmploymentPeriod(Date startDate, Date endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Recruitment getRecruitment() {
    return recruitment;
  }

  public void setRecruitment(Recruitment recruitment) {
    this.recruitment = recruitment;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

}
