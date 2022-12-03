package com.challenge.countries.exception;

/**
 * This exception is thrown when a route cannot be found between two countries.
 */
public class RouteNotFoundException extends RuntimeException {

  public RouteNotFoundException(String origin, String destination) {
    super(String.format("Could not find a land route between %s and %s", origin, destination));
  }
}
