package com.tv.tutorials.coolestuffvoorcheops.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

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
	private Boolean toBeInvitedForFirstConversation;

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
	private Boolean toBeInvitedForTechnicalConversation;

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
	private Boolean isRecruited;

	public ApplicationProcess() {

	}

	public ApplicationProcess(Date applicationDate, Boolean toBeInvitedForFirstConversation, Date invitationDate,
			Date firstConversationDate, String staffNameFirstConversation, String feedbackFirstConversation,
			Boolean toBeInvitedForTechnicalConversation, Date technicalConversationDate,
			String staffNameTechnicalConversation, String feedbackTechnicalConversation,
			Boolean toBeSendFinancialProposal, Date financialProposalDate, String feedbackFinancialProposal,
			Boolean isRecruited) {
		super();
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
