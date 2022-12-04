package com.challenge.countries.service;

import com.challenge.countries.exception.CountryNotFoundException;
import java.util.Objects;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Service;

/**
 * Service used for different types of validation.
 */
@Service
public class ValidationService {
  private static final Logger logger = LogManager.getLogger();

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
      logger.error("Could not find country code: {}", country);
      throw new CountryNotFoundException(country);
    }
  }
}
