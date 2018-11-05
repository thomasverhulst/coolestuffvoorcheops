package com.cheops.candidatemanager.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "applicationprocess")
public class ApplicationProcess {

	@Id
	@Column(name = "idapplicationprocess")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "applicationdate")
	private Date applicationDate;

	@Column(name = "tobeinvitedforfirstconversation")
	private boolean toBeInvitedForFirstConversation;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "invitationdate")
	private Date invitationDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "firstconversationdate")
	private Date firstConversationDate;

	@Column(name = "staffnamefirstconversation")
	private String staffNameFirstConversation;

	@Column(name = "feedbackfirstconversation")
	private String feedbackFirstConversation;
	
	@Column(name = "tobeinvitedfortechnicalconversation")
	private boolean toBeInvitedForTechnicalConversation;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "technicalconversationdate")
	private Date technicalConversationDate;

	@Column(name = "staffnametechnicalconversation")
	private String staffNameTechnicalConversation;

	@Column(name = "feedbacktechnicalconversation")
	private String feedbackTechnicalConversation;

	@Column(name = "tobesendfinancialproposal")
	private Boolean toBeSendFinancialProposal;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "financialproposaldate")
	private Date financialProposalDate;

	@Column(name = "feedbackfinancialproposal")
	private String feedbackFinancialProposal;

	@Column(name = "isrecruited")
	private boolean isRecruited;
	
	@Column(name = "isrecruitedtimestamp")
	private Timestamp isRecruitedTimeStamp;
	
	@Column(name = "isexemployee")
	private boolean isExEmployee;
	
	@Column(name = "isexemployeetimestamp")
	private Timestamp isExEmployeeTimeStamp;

	@Transient
	private MultipartFile file;
	
	@Column(name = "feedbackfilename")
	private String feedbackFileName;
	
	@Column(name = "notrecruited")
	private String notRecruited;
	
	public ApplicationProcess() {

	}

	
	public ApplicationProcess(Integer id, Date applicationDate, boolean toBeInvitedForFirstConversation,
			Date invitationDate, Date firstConversationDate, String staffNameFirstConversation,
			String feedbackFirstConversation, boolean toBeInvitedForTechnicalConversation,
			Date technicalConversationDate, String staffNameTechnicalConversation, String feedbackTechnicalConversation,
			Boolean toBeSendFinancialProposal, Date financialProposalDate, String feedbackFinancialProposal,
			boolean isRecruited, Timestamp isRecruitedTimeStamp, boolean isExEmployee, Timestamp isExEmployeeTimeStamp,
			MultipartFile file, String feedbackFileName, String notRecruited) {
		super();
		this.id = id;
		this.applicationDate = applicationDate;
		this.toBeInvitedForFirstConversation = toBeInvitedForFirstConversation;
		this.invitationDate = invitationDate;
		this.firstConversationDate = firstConversationDate;
		this.staffNameFirstConversation = staffNameFirstConversation;
		this.feedbackFirstConversation = feedbackFirstConversation;
		this.toBeInvitedForTechnicalConversation = toBeInvitedForTechnicalConversation;
		this.technicalConversationDate = technicalConversationDate;
		this.staffNameTechnicalConversation = staffNameTechnicalConversation;
		this.feedbackTechnicalConversation = feedbackTechnicalConversation;
		this.toBeSendFinancialProposal = toBeSendFinancialProposal;
		this.financialProposalDate = financialProposalDate;
		this.feedbackFinancialProposal = feedbackFinancialProposal;
		this.isRecruited = isRecruited;
		this.isRecruitedTimeStamp = isRecruitedTimeStamp;
		this.isExEmployee = isExEmployee;
		this.isExEmployeeTimeStamp = isExEmployeeTimeStamp;
		this.file = file;
		this.feedbackFileName = feedbackFileName;
		this.notRecruited = notRecruited;
	}




	@Override
	public boolean equals(Object obj) {

		try {
			ApplicationProcess applicationProcess = (ApplicationProcess) obj;
			return id.equals(applicationProcess.getId());
		} catch (Exception e) {
			return false;
		}

	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Boolean getToBeInvitedForFirstConversation() {
		return toBeInvitedForFirstConversation;
	}

	public void setToBeInvitedForFirstConversation(Boolean toBeInvitedForFirstConversation) {
		this.toBeInvitedForFirstConversation = toBeInvitedForFirstConversation;
	}

	public Date getInvitationDate() {
		return invitationDate;
	}

	public void setInvitationDate(Date invitationDate) {
		this.invitationDate = invitationDate;
	}

	public Date getFirstConversationDate() {
		return firstConversationDate;
	}

	public void setFirstConversationDate(Date firstConversationDate) {
		this.firstConversationDate = firstConversationDate;
	}

	public String getStaffNameFirstConversation() {
		return staffNameFirstConversation;
	}

	public void setStaffNameFirstConversation(String staffNameFirstConversation) {
		this.staffNameFirstConversation = staffNameFirstConversation;
	}

	public String getFeedbackFirstConversation() {
		return feedbackFirstConversation;
	}

	public void setFeedbackFirstConversation(String feedbackFirstConversation) {
		this.feedbackFirstConversation = feedbackFirstConversation;
	}

	public Boolean getToBeInvitedForTechnicalConversation() {
		return toBeInvitedForTechnicalConversation;
	}

	public void setToBeInvitedForTechnicalConversation(Boolean toBeInvitedForTechnicalConversation) {
		this.toBeInvitedForTechnicalConversation = toBeInvitedForTechnicalConversation;
	}

	public Date getTechnicalConversationDate() {
		return technicalConversationDate;
	}

	public void setTechnicalConversationDate(Date technicalConversationDate) {
		this.technicalConversationDate = technicalConversationDate;
	}

	public String getStaffNameTechnicalConversation() {
		return staffNameTechnicalConversation;
	}

	public void setStaffNameTechnicalConversation(String staffNameTechnicalConversation) {
		this.staffNameTechnicalConversation = staffNameTechnicalConversation;
	}

	public String getFeedbackTechnicalConversation() {
		return feedbackTechnicalConversation;
	}

	public void setFeedbackTechnicalConversation(String feedbackTechnicalConversation) {
		this.feedbackTechnicalConversation = feedbackTechnicalConversation;
	}

	public Boolean getToBeSendFinancialProposal() {
		return toBeSendFinancialProposal;
	}

	public void setToBeSendFinancialProposal(Boolean toBeSendFinancialProposal) {
		this.toBeSendFinancialProposal = toBeSendFinancialProposal;
	}

	public Date getFinancialProposalDate() {
		return financialProposalDate;
	}

	public void setFinancialProposalDate(Date financialProposalDate) {
		this.financialProposalDate = financialProposalDate;
	}

	public String getFeedbackFinancialProposal() {
		return feedbackFinancialProposal;
	}

	public void setFeedbackFinancialProposal(String feedbackFinancialProposal) {
		this.feedbackFinancialProposal = feedbackFinancialProposal;
	}

	public Boolean getIsRecruited() {
		return isRecruited;
	}

	public void setIsRecruited(Boolean isRecruited) {
		this.isRecruited = isRecruited;
	}
	
	

	public boolean getIsExEmployee() {
		return isExEmployee;
	}


	public void setIsExEmployee(boolean isExEmployee) {
		this.isExEmployee = isExEmployee;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotRecruited() {
		return notRecruited;
	}

	public void setNotRecruited(String notRecruited) {
		this.notRecruited = notRecruited;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFeedbackFileName() {
		return feedbackFileName;
	}

	public void setFeedbackFileName(String feedbackFileName) {
		this.feedbackFileName = feedbackFileName;
	}

	public Timestamp getIsRecruitedTimeStamp() {
		return isRecruitedTimeStamp;
	}

	public void setIsRecruitedTimeStamp(Timestamp isRecruitedTimeStamp) {
		this.isRecruitedTimeStamp = isRecruitedTimeStamp;
	}

	public void setToBeInvitedForFirstConversation(boolean toBeInvitedForFirstConversation) {
		this.toBeInvitedForFirstConversation = toBeInvitedForFirstConversation;
	}

	public void setToBeInvitedForTechnicalConversation(boolean toBeInvitedForTechnicalConversation) {
		this.toBeInvitedForTechnicalConversation = toBeInvitedForTechnicalConversation;
	}

	public void setRecruited(boolean isRecruited) {
		this.isRecruited = isRecruited;
	}

	public Timestamp getIsExEmployeeTimeStamp() {
		return isExEmployeeTimeStamp;
	}

	public void setIsExEmployeeTimeStamp(Timestamp isExEmployeeTimeStamp) {
		this.isExEmployeeTimeStamp = isExEmployeeTimeStamp;
	}
	
}
