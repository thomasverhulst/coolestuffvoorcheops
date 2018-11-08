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

	@Column(name = "streetname")
	private String streetname;

	@Column(name = "housenumber")
	private String housenumber;

	@Column(name = "bus")
	private String bus;

	@Column(name = "postalcode")
	private String postalcode;

	@Column(name = "countrycode")
	private String countrycode;

	@Column(name = "municipality")
	private String municipality;

	// ??
	@JoinColumn(name = "idaddress")
	@OneToOne
	private NewCandidate newCandidate;
	// ??

	public Address() {
	}

	public Address(String streetname, String housenumber, String bus, String postalcode, String countrycode, String municipality) {
		super();
		this.streetname = streetname;
		this.housenumber = housenumber;
		this.bus = bus;
		this.postalcode = postalcode;
		this.countrycode = countrycode;
		this.municipality = municipality;
	}

	public String getStreetname() {
		return streetname;
	}

	public void setStreetname(String streetname) {
		this.streetname = streetname;
	}

	public String getHousenumber() {
		return housenumber;
	}

	public void setHousenumber(String housenumber) {
		this.housenumber = housenumber;
	}

	public String getBus() {
		return bus;
	}

	public void setBus(String bus) {
		this.bus = bus;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCountrycode() {
		return countrycode;
	}

	public void setCountrycode(String countrycode) {
		this.countrycode = countrycode;
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

	// ??
	public NewCandidate getNewCandidate() {
		return newCandidate;
	}

	public void setNewCandidate(NewCandidate newCandidate) {
		this.newCandidate = newCandidate;
	}
	// ??

}
