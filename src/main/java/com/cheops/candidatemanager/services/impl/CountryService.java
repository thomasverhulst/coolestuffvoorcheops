package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.exceptions.CountryNotFoundException;
import com.cheops.candidatemanager.models.Country;
import com.cheops.candidatemanager.services.ICountryService;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class CountryService implements ICountryService {

  @Override
  public List<Country> getAllCountries() {
    List<Country> countries = new ArrayList<>();

    // Map ISO countries to custom country object
    String[] countryCodes = Locale.getISOCountries();
    for (String countryCode : countryCodes){
      Locale locale = new Locale("", countryCode);
      String code = locale.getCountry();
      String name = locale.getDisplayCountry(LocaleContextHolder.getLocale());
      countries.add(new Country(code, name));
    }

    Collections.sort(countries);

    return countries;
  }

  @Override
  public String getCountryCodeByName(String country) throws CountryNotFoundException {
    String found = null;

    String[] countryCodes = Locale.getISOCountries();
    for (String countryCode : countryCodes){
      Locale locale = new Locale("", countryCode);
      if(locale.getDisplayCountry(LocaleContextHolder.getLocale()).equals(country)) {
        found = locale.getCountry();
      }
    }

    if (found == null) {
      throw new CountryNotFoundException("This country does not exist: " + country);
    }

    return found;
  }
}
