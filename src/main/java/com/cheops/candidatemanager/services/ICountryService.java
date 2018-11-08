package com.cheops.candidatemanager.services;

import com.cheops.candidatemanager.models.Country;

import java.util.List;

public interface ICountryService {

  List<Country> getAllCountries();

  String getCountryCodeByName(String country);

}
