package com.challenge.countries.service;

import static com.challenge.countries.util.TestUtils.getMockCountriesGraph;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.challenge.countries.dto.RoutingResponseDto;
import com.challenge.countries.exception.RouteNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RoutingServiceTest {

  @Mock
  private ValidationService validationService;

  @Mock
  private CountryService countryService;

  @InjectMocks
  private RoutingService routingService;

  @Test
  void givenGraphLoadedCorrectly_whenGetShortestRoute_thenShortestRouteReturned() {
    // GIVEN
    final String originCountry = "CZE";
    final String destinationCountry = "ITA";

    final RoutingResponseDto expectedRoutingResponseDto = new RoutingResponseDto();
    expectedRoutingResponseDto.setRoute(asList("CZE", "AUT", "ITA"));

    doNothing().when(validationService).checkCountryExists(anyString());
    when(countryService.getCountriesGraph()).thenReturn(getMockCountriesGraph());

    // WHEN
    RoutingResponseDto responseDto = routingService.getShortestRoute(originCountry, destinationCountry);

    // THEN
    assertNotNull(responseDto);
    assertEquals(expectedRoutingResponseDto.getRoute(), responseDto.getRoute());
  }

  @Test
  void givenNotLandRoutePossible_whenGetShortestRoute_thenShortestRouteReturned() {
    // GIVEN
    final String originCountry = "CZE";
    final String destinationCountry = "USA";

    doNothing().when(validationService).checkCountryExists(anyString());
    when(countryService.getCountriesGraph()).thenReturn(getMockCountriesGraph());

    // WHEN
    RouteNotFoundException exception = assertThrows(RouteNotFoundException.class,
        () -> routingService.getShortestRoute(originCountry, destinationCountry));

    // THEN
    assertEquals(String.format("Could not find a land route between %s and %s", originCountry, destinationCountry), exception.getMessage());
  }
}