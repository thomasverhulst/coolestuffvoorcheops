package com.cheops.candidatemanager.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "meeting")
public class Meeting {

	@Id
	@Column(name = "idmeeting")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "candidatenr")
	private int candidateId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "meetingdate")
	private Date meetingDate;

	@Column(name = "conversationpartner")
	private String conversationPartner;

	@Enumerated(EnumType.STRING) // another possibility: EnumType.ORDINAL
	@Column(name = "conversationtype")
	private ConversationType conversationType;

	public Meeting() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public String getConversationPartner() {
		return conversationPartner;
	}

	public void setConversationPartner(String conversationPartner) {
		this.conversationPartner = conversationPartner;
	}

	public ConversationType getConversationType2() {
		return conversationType;
	}

	public void setConversationType2(ConversationType conversationType) {
		this.conversationType = conversationType;
	}

//	public static enum ConversationType {
//		// FIRST, SECOND
//		// poging van
//		// https://stackoverflow.com/questions/29515366/how-to-display-all-possible-enum-values-in-a-dropdown-list-using-spring-and-thym
//		FIRST("First conversation"), SECOND("Technical conversation");
//
//		public final String displayName;
//
//		ConversationType(String displayName) {
//			this.displayName = displayName;
//		}
//
//		public String getConversationType() {
//			return displayName;
//		}
//
//	}

}
