package com.challenge.countries.service;

import com.challenge.countries.model.Country;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

/**
 * Service to hold the countries data such as all country codes and graph of countries that border with at least one other.
 */
@Service
public class CountryService {

  @Value("${countriesJson.path}")
  private String countriesJsonPath;

  private final UndirectedGraph<String, DefaultEdge> countriesGraph = new SimpleGraph<>(DefaultEdge.class);

  @PostConstruct
  public void init() throws IOException {
    List<Country> allCountries = getAllCountries();

    createAndPopulateCountriesGraph(allCountries);
  }

  private List<Country> getAllCountries() throws IOException {
    File countriesJsonFile = new ClassPathResource(countriesJsonPath).getFile();

    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(countriesJsonFile, new TypeReference<>() {});
  }

  private void createAndPopulateCountriesGraph(List<Country> allCountries) {
    // adding vertices
    allCountries.forEach(country -> countriesGraph.addVertex(country.getCca3()));

    // adding edges only for countries with borders
    allCountries.stream().filter(country -> !country.getBorders().isEmpty())
        .forEach(filteredCountry -> filteredCountry.getBorders()
            .forEach(border -> countriesGraph.addEdge(filteredCountry.getCca3(), border)));
  }

  public UndirectedGraph<String, DefaultEdge> getCountriesGraph() {
    return countriesGraph;
  }
}
