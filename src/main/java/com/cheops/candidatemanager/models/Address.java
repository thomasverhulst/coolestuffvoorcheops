package com.cheops.candidatemanager.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "address")
public class Address implements Serializable {

	@Id
	@Column(name = "idaddress")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "streetname")
  @Size(max = 45, message = "{street.size}")
	private String streetname;

	@Column(name = "housenumber")
  @Size(max = 45, message = "{housenumber.size}")
  private String housenumber;

	@Column(name = "bus")
  @Size(max = 45, message = "{bus.size}")
  private String bus;

	@Column(name = "postalcode")
  @Size(max = 45, message = "{postalcode.size}")
  private String postalcode;

	@Column(name = "countrycode")
	private String countrycode;

	@Column(name = "municipality")
  @Size(max = 45, message = "{municipality.size}")
  private String municipality;

	public Address() {
  }

  public Address(@Size(max = 45, message = "{street.size}") String streetname, @Size(max = 45, message = "{housenumber.size}") String housenumber, @Size(max = 45, message = "{bus.size}") String bus, @Size(max = 45, message = "{postalcode.size}") String postalcode, String countrycode, @Size(max = 45, message = "{municipality.size}") String municipality) {
    this.streetname = streetname;
    this.housenumber = housenumber;
    this.bus = bus;
    this.postalcode = postalcode;
    this.countrycode = countrycode;
    this.municipality = municipality;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

}
