package com.cheops.candidatemanager.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
@Entity
//@IdClass(NewCandidate.class)
@Table(name = "candidate")
public class NewCandidate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idcandidate")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "name")
	private String name;

	@Column(name = "sirname")
	private String sirName;

	@Column(name = "email")
	private String email;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthdate")
	private Date birthdate;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "celphonenumber")
	private String celphoneNumber;

	@Column(name = "cvlink")
	private String cvLink;

	@Column(name = "gender")
	private String gender;
	
	@Transient
	private MultipartFile file;

	@Column(name = "contactChannel")
	private String contactChannel;
	
	@Column(name = "isaddedtimestamp")
	private Timestamp isAddedTimeStamp;
	
	//@Id
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "candidate_currentsallarypackage", joinColumns = @JoinColumn(name = "candidate_id"), inverseJoinColumns = @JoinColumn(name = "currentsallarypackage_id"))
	//@Column(name = "currentsallarypackageId")
	private List<SalaryPackage> currentSallaryPackage;

	//@Id
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "candidate_proposedsallarypackage", joinColumns = @JoinColumn(name = "candidate_id"), inverseJoinColumns = @JoinColumn(name = "proposedsallarypackage_id"))
	//@Column(name = "proposedsallarypackageId")
	private List<SalaryPackage> proposedSallaryPackage;

	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "candidate_workhistory", joinColumns = @JoinColumn(name = "candidate_id"), inverseJoinColumns = @JoinColumn(name = "workhistory_id"))
	//@Column(name = "currentsallarypackageId")
	private List<WorkHistory> workHistory;
	
	//@Id
	@OneToOne(cascade = CascadeType.ALL)
	private Skills skills;

	//@Id
	@OneToOne( cascade = CascadeType.ALL)
	private Address address;

	//@Id
	@OneToOne(cascade = CascadeType.ALL)
	private ApplicationProcess applicationProcess;

	// old colums, still needed for compatibillity candidate and newcandidate
	@Column(name = "currentsallarypackageId")
	private int currentSallaryPackageId;

	@Column(name = "proposedsallarypackageId")
	private int proposedSallaryPackageId;

	@Column(name = "skillsId")
	private int skillsId;

	@Column(name = "addressId")
	private int addressId;

	@Column(name = "applicationprocessId")
	private int applicationProcessId;

	public NewCandidate(){
		
	}
	
	public NewCandidate(Integer id, String name, String sirName, String email, Date birthdate, String phoneNumber,
			String celphoneNumber, String cvLink, String gender, MultipartFile file, String contactChannel,
			  Skills skills, Address address,
			ApplicationProcess applicationProcess) {
		super();
		this.id = id;
		this.name = name;
		this.sirName = sirName;
		this.email = email;
		this.birthdate = birthdate;
		this.phoneNumber = phoneNumber;
		this.celphoneNumber = celphoneNumber;
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

	public String getSirName() {
		return sirName;
	}

	public void setSirName(String sirName) {
		this.sirName = sirName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCelphoneNumber() {
		return celphoneNumber;
	}

	public void setCelphoneNumber(String celphoneNumber) {
		this.celphoneNumber = celphoneNumber;
	}

	public String getCvLink() {
		return cvLink;
	}

	public void setCvLink(String cvLink) {
		this.cvLink = cvLink;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getContactChannel() {
		return contactChannel;
	}

	public void setContactChannel(String contactChannel) {
		this.contactChannel = contactChannel;
	}

	public List<SalaryPackage> getCurrentSallaryPackage() {
		return currentSallaryPackage;
	}

	public List<SalaryPackage> getProposedSallaryPackage() {
		return proposedSallaryPackage;
	}

	public void setProposedSallaryPackage(List<SalaryPackage> proposedSallaryPackage) {
		this.proposedSallaryPackage = proposedSallaryPackage;
	}

	public void setCurrentSallaryPackage(List<SalaryPackage> currentSallaryPackage) {
		this.currentSallaryPackage = currentSallaryPackage;
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

	public ApplicationProcess getApplicationProcess() {
		return applicationProcess;
	}

	public void setApplicationProcess(ApplicationProcess applicationProcess) {
		this.applicationProcess = applicationProcess;
	}

	public int getCurrentSallaryPackageId() {
		return currentSallaryPackageId;
	}

	public void setCurrentSallaryPackageId(int currentSallaryPackageId) {
		this.currentSallaryPackageId = currentSallaryPackageId;
	}

	public int getProposedSallaryPackageId() {
		return proposedSallaryPackageId;
	}

	public void setProposedSallaryPackageId(int proposedSallaryPackageId) {
		this.proposedSallaryPackageId = proposedSallaryPackageId;
	}

	public int getSkillsId() {
		return skillsId;
	}

	public void setSkillsId(int skillsId) {
		this.skillsId = skillsId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getApplicationProcessId() {
		return applicationProcessId;
	}

	public void setApplicationProcessId(int applicationProcessId) {
		this.applicationProcessId = applicationProcessId;
	}

	public Timestamp getIsAddedTimeStamp() {
		return isAddedTimeStamp;
	}

	public void setIsAddedTimeStamp(Timestamp isAddedTimeStamp) {
		this.isAddedTimeStamp = isAddedTimeStamp;
	}

	public List<WorkHistory> getWorkHistory() {
		return workHistory;
	}

	public void setWorkHistory(List<WorkHistory> workHistory) {
		this.workHistory = workHistory;
	}
	
}
