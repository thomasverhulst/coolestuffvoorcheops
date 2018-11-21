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
import javax.validation.constraints.Size;

import com.cheops.candidatemanager.enums.ConversationType;
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
  @Size(max = 45, message = "{conversationpartner.size}")
	private String conversationPartner;

	@Enumerated(EnumType.STRING)
	@Column(name = "conversationtype")
	private ConversationType conversationType;

	public Meeting() {
	}

	public Meeting(Date meetingDate, String conversationPartner, ConversationType conversationType) {
		this.meetingDate = meetingDate;
		this.conversationPartner = conversationPartner;
		this.conversationType = conversationType;
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

}
