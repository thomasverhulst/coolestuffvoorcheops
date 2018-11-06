package com.cheops.candidatemanager.models;

import com.cheops.candidatemanager.enums.Gender;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "candidate_fe")
public class NewCandidateFE implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idcandidate")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

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
	private String phonenumber;

	@Column(name = "cellphonenumber")
	private String cellphonenumber;

	@Column(name = "cvlink")
	private String cvLink;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "candidate_currentsallarypackage", joinColumns = @JoinColumn(name = "candidate_id"), inverseJoinColumns = @JoinColumn(name = "currentsalarypackage_id"))
  private List<SalaryPackage> currentSalaryPackage;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "candidate_proposedsalarypackage", joinColumns = @JoinColumn(name = "candidate_id"), inverseJoinColumns = @JoinColumn(name = "proposedsalarypackage_id"))
  private List<SalaryPackage> proposedSalaryPackage;

  @OneToOne(cascade = CascadeType.ALL)
  private Skills skills;

  @OneToOne( cascade = CascadeType.ALL)
  private Address address;

  @OneToOne(cascade = CascadeType.ALL)
  private ApplicationProcess applicationProcess;

  @Column(name = "contactChannel")
  private String contactChannel;

  @Column(name = "isaddedtimestamp")
  private Timestamp isAddedTimeStamp;

	@Transient
	private MultipartFile file;

	public NewCandidateFE(){
	}

	public NewCandidateFE(Integer id, String name, String lastName, String email, Date dateOfBirth, String phonenumber,
                        String cellphonenumber, String cvLink, Gender gender, MultipartFile file, String contactChannel,
                        Skills skills, Address address, ApplicationProcess applicationProcess) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.phonenumber = phonenumber;
		this.cellphonenumber = cellphonenumber;
		this.cvLink = cvLink;
		this.gender = gender;
		this.file = file;
		this.contactChannel = contactChannel;
		this.skills = skills;
		this.address = address;
		this.applicationProcess = applicationProcess;
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

  public List<SalaryPackage> getCurrentSalaryPackage() {
    return currentSalaryPackage;
  }

  public void setcurrentSalaryPackage(List<SalaryPackage> currentSalaryPackage) {
    this.currentSalaryPackage = currentSalaryPackage;
  }

  public List<SalaryPackage> getproposedSalaryPackage() {
    return proposedSalaryPackage;
  }

  public void setproposedSalaryPackage(List<SalaryPackage> proposedSalaryPackage) {
    this.proposedSalaryPackage = proposedSalaryPackage;
  }

  public Skills getSkills() {
    return skills;
  }

  public void setSkills(Skills skills) {
    this.skills = skills;
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

}
