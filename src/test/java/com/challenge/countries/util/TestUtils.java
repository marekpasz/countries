package com.challenge.countries.util;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class TestUtils {

  public static UndirectedGraph<String, DefaultEdge> getMockCountriesGraph() {
    final String czechCountryCode = "CZE";
    final String austriaCountryCode = "AUT";
    final String italyCountryCode = "ITA";
    final String usaCountryCode = "USA";

    UndirectedGraph<String, DefaultEdge> mockGraph = new SimpleGraph<>(DefaultEdge.class);
    mockGraph.addVertex(czechCountryCode);
    mockGraph.addVertex(italyCountryCode);
    mockGraph.addVertex(austriaCountryCode);
    mockGraph.addVertex(usaCountryCode);
    mockGraph.addEdge(czechCountryCode, austriaCountryCode);
    mockGraph.addEdge(austriaCountryCode, italyCountryCode);
    return mockGraph;
  }
}
