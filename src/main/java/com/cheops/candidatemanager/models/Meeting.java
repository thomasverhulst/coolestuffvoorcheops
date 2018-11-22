package com.cheops.candidatemanager.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

import com.cheops.candidatemanager.enums.ConversationType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "meeting")
public class Meeting implements Serializable {

	@Id
	@Column(name = "idmeeting")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

  @ManyToOne
  @JoinColumn(name = "candidate_id")
	private Candidate candidate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "meetingdate")
	private Date meetingDate;

	@Column(name = "conversationpartner")
  @Size(max = 45, message = "{conversationpartner.size}")
	private String conversationPartner;

	@Enumerated(EnumType.STRING)
	@Column(name = "conversationtype")
	private ConversationType conversationType;

  @Column(name = "feedback")
  @Size(max = 400, message = "{feedback.size}")
  private String feedback;

	public Meeting() {
	}

  public Meeting(Date meetingDate, @Size(max = 45, message = "{conversationpartner.size}") String conversationPartner, ConversationType conversationType, @Size(max = 400, message = "{feedback.size}") String feedback) {
    this.meetingDate = meetingDate;
    this.conversationPartner = conversationPartner;
    this.conversationType = conversationType;
    this.feedback = feedback;
  }

  public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

  public Candidate getCandidate() {
    return candidate;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
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

	public ConversationType getConversationType() {
		return conversationType;
	}

	public void setConversationType(ConversationType conversationType) {
		this.conversationType = conversationType;
	}

  public String getFeedback() {
    return feedback;
  }

  public void setFeedback(String feedback) {
    this.feedback = feedback;
  }
}
