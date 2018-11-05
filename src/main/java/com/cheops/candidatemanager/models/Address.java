package com.cheops.candidatemanager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@Column(name = "idaddress")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	//@Id
	@JoinColumn(name = "idaddress")
	@OneToOne
    private NewCandidate newCandidate; 

	@Column(name = "streetname")
	private String streetName;

	@Column(name = "dwellingnumber")
	private String dwellingNumber;

	@Column(name = "bus")
	private String bus;

	@Column(name = "postalcode")
	private String postalCode;

	@Column(name = "land")
	private String land;

	@Column(name = "municipality")
	private String municipality;

	public Address() {
	}

	public Address(String streetName, String dwellingNumber, String bus, String postalCode, String land,
			String municipality) {
		super();
		this.streetName = streetName;
		this.dwellingNumber = dwellingNumber;
		this.bus = bus;
		this.postalCode = postalCode;
		this.land = land;
		this.municipality = municipality;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getDwellingNumber() {
		return dwellingNumber;
	}

	public void setDwellingNumber(String dwellingNumber) {
		this.dwellingNumber = dwellingNumber;
	}

	public String getBus() {
		return bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NewCandidate getNewCandidate() {
		return newCandidate;
	}

	public void setNewCandidate(NewCandidate newCandidate) {
		this.newCandidate = newCandidate;
	}
	

}
