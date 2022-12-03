package com.challenge.countries.dto;

import java.util.List;

public class RoutingResponseDto {

  private List<String> route;

  public RoutingResponseDto() {}

  public RoutingResponseDto(List<String> route) {
    this.route = route;
  }

  public List<String> getRoute() {
    return route;
  }

  public void setRoute(List<String> route) {
    this.route = route;
  }
}
