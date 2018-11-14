//package com.cheops.candidatemanager.models;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.persistence.Transient;
//
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.web.multipart.MultipartFile;
//
//@Entity
//@Table(name = "candidate")
//public class Candidate implements Serializable {
//
//	/**
//	 *
//	 */
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Column(name = "idcandidate")
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Integer id;
//
//	@Column(name = "name")
//	private String name;
//
//	@Column(name = "sirname")
//	private String sirName;
//
//	@Column(name = "email")
//	private String email;
//
//	// This is "org.springframework.format.annotation.DateTimeFormat"
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
//	@Column(name = "birthdate")
//	private Date birthdate;
//
//	@Column(name = "phonenumber")
//	private String phoneNumber;
//
//	@Column(name = "celphonenumber")
//	private String celphoneNumber;
//
//	@Column(name = "cvlink")
//	private String cvLink;
//
//	@Column(name = "gender")
//	private String gender;
//
//	@Transient
//	private MultipartFile file;
//
//	@Column(name = "contactChannel")
//	private String contactChannel;
//
//	@Column(name = "isaddedtimestamp")
//	private Timestamp isAddedTimeStamp;
//	// ids
//	@Column(name = "currentsallarypackageId")
//	private int currentSallaryPackageId;
//
//	@Column(name = "proposedsallarypackageId")
//	private int proposedSallaryPackageId;
//
//	@Column(name = "skillsId")
//	private int skillsId;
//
//	@Column(name = "addressId")
//	private int addressId;
//
//	@Column(name = "applicationprocessId")
//	private int applicationProcessId;
//
//	public Candidate() {
//
//	}
//
//	public Candidate(String name, String sirName, String email, Date birthdate, String phoneNumber,
//			String celphoneNumber, String cvLink, String gender) {
//		super();
//		this.name = name;
//		this.sirName = sirName;
//		this.email = email;
//		this.birthdate = birthdate;
//		this.phoneNumber = phoneNumber;
//		this.celphoneNumber = celphoneNumber;
//		this.cvLink = cvLink;
//		this.gender = gender;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getSirName() {
//		return sirName;
//	}
//
//	public void setSirName(String sirName) {
//		this.sirName = sirName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public Date getBirthdate() {
//		return birthdate;
//	}
//
//	public void setBirthdate(Date birthday) {
//		this.birthdate = birthday;
//	}
//
//	public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public String getCelphoneNumber() {
//		return celphoneNumber;
//	}
//
//	public void setCelphoneNumber(String celphoneNumber) {
//		this.celphoneNumber = celphoneNumber;
//	}
//
//	public String getCvLink() {
//		return cvLink;
//	}
//
//	public void setCvLink(String cvLink) {
//		this.cvLink = cvLink;
//	}
//
//	public String getGender() {
//		return gender;
//	}
//
//	public void setGender(String sex) {
//		this.gender = sex;
//	}
//
//	public String getContactChannel() {
//		return contactChannel;
//	}
//
//	public void setContactChannel(String contactChannel) {
//		this.contactChannel = contactChannel;
//	}
//
//	public MultipartFile getFile() {
//		return file;
//	}
//
//	public void setFile(MultipartFile file) {
//		this.file = file;
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public Integer getAddressId() {
//		return addressId;
//	}
//
//	public int getCurrentSallaryPackageId() {
//		return currentSallaryPackageId;
//	}
//
//	public void setCurrentSallaryPackageId(int currentSallaryPackageId) {
//		this.currentSallaryPackageId = currentSallaryPackageId;
//	}
//
//	public int getProposedSallaryPackageId() {
//		return proposedSallaryPackageId;
//	}
//
//	public void setProposedSallaryPackageId(int proposedSallaryPackageId) {
//		this.proposedSallaryPackageId = proposedSallaryPackageId;
//	}
//
//	public int getSkillsId() {
//		return skillsId;
//	}
//
//	public void setSkillsId(int skillsId) {
//		this.skillsId = skillsId;
//	}
//
//	public int getApplicationProcessId() {
//		return applicationProcessId;
//	}
//
//	public void setApplicationProcessId(int applicationProcessId) {
//		this.applicationProcessId = applicationProcessId;
//	}
//
//	public void setAddressId(int addressId) {
//		this.addressId = addressId;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Timestamp getIsAddedTimeStamp() {
//		return isAddedTimeStamp;
//	}
//
//	public void setIsAddedTimeStamp(Timestamp isAddedTimeStamp) {
//		this.isAddedTimeStamp = isAddedTimeStamp;
//	}
//
//}
