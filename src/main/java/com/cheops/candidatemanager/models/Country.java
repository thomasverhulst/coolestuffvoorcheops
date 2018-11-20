package com.cheops.candidatemanager.models;

public class Country implements Comparable<Country> {

  private String code;
  private String name;

  public Country() {}

  public Country(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  @Override
  public int compareTo(Country o) {
    return this.name.compareTo(o.getName());
  }

  @Override
  public String toString() {
    return name;
  }

}
