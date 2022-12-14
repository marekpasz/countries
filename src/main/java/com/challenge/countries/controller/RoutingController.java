package com.challenge.countries.controller;

import com.challenge.countries.dto.RoutingResponseDto;
import com.challenge.countries.service.RoutingService;
import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API Controller for finding a route.
 */
@RestController
@Validated
public class RoutingController {

  private final RoutingService routingService;

  @Inject
  public RoutingController(RoutingService routingService) {
    this.routingService = routingService;
  }

  /**
   * Finds the shortest land route between specified countries (source and destination).
   * If land route is not possible, returns HTTP status 400 (Bad Request).
   *
   * @param origin origin country (3 letter code - cca3)
   * @param destination destination country (3 letter code - cca3)
   * @return shortest land route between specified countries
   */
  @GetMapping("/routing/{origin}/{destination}")
  public ResponseEntity<RoutingResponseDto> getRouteByOriginAndDestination(
      @PathVariable("origin") @NotBlank @Size(min = 3, max = 3) String origin,
      @PathVariable("destination") @NotBlank @Size(min = 3, max = 3) String destination) {
    return ResponseEntity.ok(routingService.getShortestRoute(origin, destination));
  }
}