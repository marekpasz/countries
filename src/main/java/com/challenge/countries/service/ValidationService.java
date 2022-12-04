package com.challenge.countries.service;

import com.challenge.countries.exception.CountryNotFoundException;
import java.util.Objects;
import javax.inject.Inject;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Service;

/**
 * Service used for different types of validation.
 */
@Service
public class ValidationService {
  private final CountryService countryService;

  @Inject
  public ValidationService(CountryService countryService) {
    this.countryService = countryService;
  }

  /**
   * Validates whether country exists in countries graph.
   *
   * @param country country code
   * @throws CountryNotFoundException when country is not found
   */
  public void checkCountryExists(String country) {
    UndirectedGraph<String, DefaultEdge> countriesGraph = countryService.getCountriesGraph();
    if (Objects.isNull(countriesGraph) || !countriesGraph.containsVertex(country)) {
      throw new CountryNotFoundException(country);
    }
  }
}
