package com.cheops.candidatemanager.enums;

public enum Gender {
  M("Male"),
  F("Female"),
  UNKNOWN("Unknown");

  private final String name;

  Gender(String name) {
    this.name = name;
  }

  public String getName() {
    return this.name;
  }
}
