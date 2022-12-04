package com.challenge.countries.service;

import static com.challenge.countries.util.TestUtils.getMockCountriesGraph;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.challenge.countries.exception.CountryNotFoundException;
import com.challenge.countries.model.Country;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidationServiceTest {

  @Mock
  private CountryService countryService;

  @InjectMocks
  private ValidationService validationService;

  @Test
  void givenNotExistingCountry_whenCheckCountryExists_thenCountryNotFoundIsThrown() {
    // GIVEN
    final String countryCode = "AAA";
    when(countryService.getCountriesGraph()).thenReturn(getMockCountriesGraph());

    // WHEN
    CountryNotFoundException exception = assertThrows(CountryNotFoundException.class,
        () -> validationService.checkCountryExists(countryCode));

    // THEN
    assertEquals(String.format("Could not find a country with code %s", countryCode), exception.getMessage());
  }

  @Test
  void givenCountriesGraphIsEmpty_whenCheckCountryExists_thenCountryNotFoundIsThrown() {
    // GIVEN
    final String countryCode = "CZE";
    when(countryService.getCountriesGraph()).thenReturn(null);

    // WHEN
    CountryNotFoundException exception = assertThrows(CountryNotFoundException.class,
        () -> validationService.checkCountryExists(countryCode));

    // THEN
    assertEquals(String.format("Could not find a country with code %s", countryCode), exception.getMessage());
  }

  @Test
  void givenExistingCountry_whenCheckCountryExists_thenNoExceptionThrown() {
    // GIVEN
    final String countryCode = "CZE";
    when(countryService.getCountriesGraph()).thenReturn(getMockCountriesGraph());

    // WHEN, THEN
    assertDoesNotThrow(() -> validationService.checkCountryExists(countryCode));
  }
}