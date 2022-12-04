package com.challenge.countries.controller;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import com.challenge.countries.dto.RoutingResponseDto;
import com.challenge.countries.service.RoutingService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class RoutingControllerTest {

  @Mock
  private RoutingService routingService;

  @InjectMocks
  private RoutingController routingController;

  @Test
  void givenCorrectCountries_whenGetRouteByOriginAndDestination_thenResponseIsReturnedOk() {
    // GIVEN
    final String originCountry = "CZE";
    final String destinationCountry = "SVK";
    final List<String> route = asList(originCountry, destinationCountry);
    final RoutingResponseDto mockResponseDto = new RoutingResponseDto(route);

    when(routingService.getShortestRoute(originCountry, destinationCountry)).thenReturn(mockResponseDto);

    // WHEN
    ResponseEntity<RoutingResponseDto> response = routingController.getRouteByOriginAndDestination(originCountry, destinationCountry);

    // THEN
    RoutingResponseDto responseDto = response.getBody();
    assertNotNull(responseDto);
    assertNotNull(responseDto.getRoute());
    assertEquals(route, responseDto.getRoute());
  }
}