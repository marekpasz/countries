package com.challenge.countries.service;

import com.challenge.countries.exception.CountryNotFoundException;
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

  public void checkCountryExists(String country) {
    UndirectedGraph<String, DefaultEdge> countriesGraph = countryService.getCountriesGraph();
    if (!countriesGraph.containsVertex(country)) {
      throw new CountryNotFoundException(country);
    }
  }
}
