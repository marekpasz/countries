package com.challenge.countries.exception;

/**
 * This exception is thrown when a route cannot be found between two countries.
 */
public class CountryNotFoundException extends RuntimeException {

  public CountryNotFoundException(String countryCode) {
    super(String.format("Could not find a country with code %s", countryCode));
  }
}
