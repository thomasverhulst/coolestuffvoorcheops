package com.cheops.candidatemanager.models;

import com.cheops.candidatemanager.enums.Gender;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "candidate_fe")
public class Candidate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idcandidate")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")
  @NotBlank(message = "{name.empty}")
  @Size(max = 45, message = "{name.size}")
	private String name;

	@Column(name = "last_name")
	@Size(max = 45, message = "{last_name.size}")
	private String lastName;

	@Column(name = "email")
  @Size(max = 45, message = "{email.size}")
  @Email(message = "{email.invalid}")
	private String email;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dob")
	private Date dateOfBirth;

	@Column(name = "phonenumber")
  @Size(max = 45, message = "{phonenumber.size}")
	private String phonenumber;

	@Column(name = "cellphonenumber")
  @Size(max = 45, message = "{cellphone.size}")
  private String cellphonenumber;

	@Column(name = "cvlink")
	private String cvLink;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@OneToOne(cascade = CascadeType.ALL)
  @Valid
	private SalaryPackage currentSalaryPackage;

  @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "candidate_proposedsalarypackage", joinColumns = @JoinColumn(name = "candidate_id"), inverseJoinColumns = @JoinColumn(name = "proposedsalarypackage_id"))
  private List<SalaryPackage> proposedSalaryPackages;

  @OneToOne(cascade = CascadeType.ALL)
  @Valid
  private Skill skill;

  @OneToOne(cascade=CascadeType.ALL)
  @Valid
  private Address address;

  @OneToOne(cascade = CascadeType.ALL)
  private ApplicationProcess applicationProcess;

  @Column(name = "contactChannel")
  @Size(max = 45, message = "{contactchannel.size}")
  private String contactChannel;

  @Column(name = "isaddedtimestamp")
  private Timestamp isAddedTimeStamp;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "candidate_workhistory", joinColumns = @JoinColumn(name = "candidate_id"), inverseJoinColumns = @JoinColumn(name = "workhistory_id"))
	private List<WorkHistory> workHistory;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "candidate_meeting", joinColumns = @JoinColumn(name = "candidate_id"), inverseJoinColumns = @JoinColumn(name = "meeting_id"))
	private List<Meeting> meeting;

	@Transient
	private MultipartFile file;

	public Candidate(){
	}

  public Candidate(@NotBlank(message = "{name.empty}") @Size(max = 45, message = "{name.size}") String name, @Size(max = 45, message = "{last_name.size}") String lastName, @Size(max = 45, message = "{email.size}") @Email(message = "{email.invalid}") String email, Date dateOfBirth, @Size(max = 45, message = "{phonenumber.size}") String phonenumber, @Size(max = 45, message = "{cellphone.size}") String cellphonenumber, String cvLink, Gender gender, @Valid SalaryPackage currentSalaryPackage, List<SalaryPackage> proposedSalaryPackages, @Valid Skill skill, @Valid Address address, ApplicationProcess applicationProcess, @Size(max = 45, message = "{contactchannel.size}") String contactChannel, Timestamp isAddedTimeStamp, List<WorkHistory> workHistory, List<Meeting> meeting) {
    this.name = name;
    this.lastName = lastName;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.phonenumber = phonenumber;
    this.cellphonenumber = cellphonenumber;
    this.cvLink = cvLink;
    this.gender = gender;
    this.currentSalaryPackage = currentSalaryPackage;
    this.proposedSalaryPackages = proposedSalaryPackages;
    this.skill = skill;
    this.address = address;
    this.applicationProcess = applicationProcess;
    this.contactChannel = contactChannel;
    this.isAddedTimeStamp = isAddedTimeStamp;
    this.workHistory = workHistory;
    this.meeting = meeting;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getCellphonenumber() {
		return cellphonenumber;
	}

	public void setCellphonenumber(String cellphonenumber) {
		this.cellphonenumber = cellphonenumber;
	}

	public String getCvLink() {
		return cvLink;
	}

	public void setCvLink(String cvLink) {
		this.cvLink = cvLink;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public SalaryPackage getCurrentSalaryPackage() {
		return currentSalaryPackage;
	}

	public void setCurrentSalaryPackage(SalaryPackage currentSalaryPackage) {
		this.currentSalaryPackage = currentSalaryPackage;
	}

  public List<SalaryPackage> getProposedSalaryPackages() {
    return proposedSalaryPackages;
  }

  public void setProposedSalaryPackages(List<SalaryPackage> proposedSalaryPackages) {
    this.proposedSalaryPackages = proposedSalaryPackages;
  }

  public Skill getSkill() {
    return skill;
  }

  public void setSkill(Skill skill) {
    this.skill = skill;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getContactChannel() {
    return contactChannel;
  }

  public void setContactChannel(String contactChannel) {
    this.contactChannel = contactChannel;
  }

  public Timestamp getIsAddedTimeStamp() {
    return isAddedTimeStamp;
  }

  public void setIsAddedTimeStamp(Timestamp isAddedTimeStamp) {
    this.isAddedTimeStamp = isAddedTimeStamp;
  }

  public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

  public List<WorkHistory> getWorkHistory() {
    return workHistory;
  }

  public void setWorkHistory(List<WorkHistory> workHistory) {
    this.workHistory = workHistory;
  }

  public List<Meeting> getMeeting() {
    return meeting;
  }

  public void setMeeting(List<Meeting> meeting) {
    this.meeting = meeting;
  }

  public Date getCvDate() {
	  Date cvDate = null;

    if (cvLink != null && !cvLink.isEmpty()) {
      try {
        DateFormat format = new SimpleDateFormat("ddMMyy-hhmmss");
        Date date = format.parse(cvLink.substring(0, cvLink.indexOf("_")));
        cvDate = date;
      } catch (ParseException e) {
        e.printStackTrace();
      }
    }

    return cvDate;
  }

  public void addProposalSalaryPackage(SalaryPackage salaryPackage) {
	  if (this.proposedSalaryPackages == null) this.proposedSalaryPackages = new ArrayList<>();
	  this.proposedSalaryPackages.add(salaryPackage);
  }

  public void removeProposalSalaryPackage(int index) {
	  if (this.proposedSalaryPackages != null) this.proposedSalaryPackages.remove(index);
  }

}
