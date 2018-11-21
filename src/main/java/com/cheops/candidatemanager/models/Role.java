package com.cheops.candidatemanager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable {

	@Id
	@Column(name = "idrole")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "role")
	private String role;

	public Role() {
	}

	public Role(Integer id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
