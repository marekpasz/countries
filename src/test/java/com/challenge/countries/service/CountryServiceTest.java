package com.challenge.countries.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

class CountryServiceTest {

  private final CountryService countryService = new CountryService();

  @BeforeEach
  void setUp() {
    ReflectionTestUtils.setField(countryService, "countriesJsonPath", "static/countries.json");
  }

  @Test
  void whenGetCountriesGraph_thenEmptyGraphReturned() {
    // WHEN
    UndirectedGraph<String, DefaultEdge> countriesGraph = countryService.getCountriesGraph();

    // THEN
    assertEquals(0, countriesGraph.vertexSet().size());
  }

  @Test
  void givenCountriesFileExist_whenInit_thenGraphPopulatedCorrectly() throws IOException {
    // WHEN
    countryService.init();

    // THEN
    UndirectedGraph<String, DefaultEdge> countriesGraph = countryService.getCountriesGraph();
    assertEquals(250, countriesGraph.vertexSet().size());
  }
}