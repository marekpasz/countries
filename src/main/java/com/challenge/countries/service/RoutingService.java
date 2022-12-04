package com.challenge.countries.service;

import com.challenge.countries.dto.RoutingResponseDto;
import com.challenge.countries.exception.RouteNotFoundException;
import java.util.Objects;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jgrapht.GraphPath;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.springframework.stereotype.Service;

/**
 * Service provides operations regarding routing from one country to another.
 */
@Service
public class RoutingService {
  private static final Logger logger = LogManager.getLogger();

  private final CountryService countryService;
  private final ValidationService validationService;

  @Inject
  public RoutingService(CountryService countryService, ValidationService validationService) {
    this.countryService = countryService;
    this.validationService = validationService;
  }

  /**
   * Retrieves the shortest route between origin and destination.
   *
   * @param origin origin country code (3 letter code - cca3)
   * @param destination destination country code  (3 letter code - cca3)
   * @return shortest land route between specified countries as list of country codes
   */
  public RoutingResponseDto getShortestRoute(String origin, String destination) {
    logger.info("Finding the shortest route between {} and {}", origin, destination);

    validationService.checkCountryExists(origin);
    validationService.checkCountryExists(destination);

    GraphPath<String, DefaultEdge> shortestPath = getDijkstraShortestPath(origin, destination);
    if (Objects.isNull(shortestPath)) {
      throw new RouteNotFoundException(origin, destination);
    }

    logger.info("The shortest route is: {}", shortestPath.getVertexList());
    return new RoutingResponseDto(shortestPath.getVertexList());
  }

  /**
   * Finds the shortest path between the origin and destination.
   * Dijkstra algorithm is used as the graph is un-oriented and un-weighted with single source vertex.
   */
  private GraphPath<String, DefaultEdge> getDijkstraShortestPath(String origin, String destination) {
    UndirectedGraph<String, DefaultEdge> countriesGraph = countryService.getCountriesGraph();
    DijkstraShortestPath<String, DefaultEdge> dijkstraShortestPath = new DijkstraShortestPath<>(countriesGraph);
    return dijkstraShortestPath.getPath(origin, destination);
  }
}
