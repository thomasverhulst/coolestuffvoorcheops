package com.cheops.candidatemanager.services.impl;

import com.cheops.candidatemanager.exceptions.CountryNotFoundException;
import com.cheops.candidatemanager.models.Country;
import com.cheops.candidatemanager.services.ICountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Service
public class CountryService implements ICountryService {

  private Locale locale = LocaleContextHolder.getLocale();

  @Autowired
  private MessageSource messageSource;

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

    if (!country.equals("")) {
      String[] countryCodes = Locale.getISOCountries();
      for (String countryCode : countryCodes){
        Locale locale = new Locale("", countryCode);
        if(locale.getDisplayCountry(LocaleContextHolder.getLocale()).equals(country)) {
          found = locale.getCountry();
        }
      }

      if (found == null) {
        throw new CountryNotFoundException(messageSource.getMessage("country.notFound", new Object[]{country}, locale));
      }
    }

    return found;
  }

  @Override
  public String getCountryByCode(String code) throws CountryNotFoundException {
    String found = null;

    if (!code.equals("")) {
      String[] countryCodes = Locale.getISOCountries();
      for (String countryCode : countryCodes) {
        Locale locale = new Locale("", countryCode);
        if(locale.getCountry().equals(code)) {
          found = locale.getDisplayCountry(LocaleContextHolder.getLocale());
        }
      }
    }

    return found;
  }
}
