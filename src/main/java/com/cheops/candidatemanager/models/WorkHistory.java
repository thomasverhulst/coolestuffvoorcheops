package com.cheops.candidatemanager.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "workhistory")
public class WorkHistory implements Serializable {

	@Id
	@Column(name = "idworkhistory")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "candidatenr")
	private int candidateId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "started")
	private Date started;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "stopped")
	private Date stopped;

	public WorkHistory() {
	}

	public WorkHistory( int candidateId, Timestamp started, Timestamp stopped) {
		this.candidateId = candidateId;
		this.started = started;
		this.stopped = stopped;
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

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getStopped() {
		return stopped;
	}

	public void setStopped(Date stopped) {
		this.stopped = stopped;
	}

}
