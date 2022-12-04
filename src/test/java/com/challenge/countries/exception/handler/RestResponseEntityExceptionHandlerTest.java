package com.challenge.countries.exception.handler;

import static org.junit.jupiter.api.Assertions.*;

import com.challenge.countries.exception.CountryNotFoundException;
import com.challenge.countries.exception.RouteNotFoundException;
import java.io.IOException;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;

class RestResponseEntityExceptionHandlerTest {

  private final RestResponseEntityExceptionHandler exceptionHandler = new RestResponseEntityExceptionHandler();

  @Test
  void whenConstraintViolationException_thenHttpServletResponseIsChanged() throws IOException {
    final int expectedBadRequestStatus = 400;

    MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
    mockHttpServletResponse.setStatus(500);
    mockHttpServletResponse.setCommitted(false);

    exceptionHandler.constraintViolationException(mockHttpServletResponse);
    assertEquals(expectedBadRequestStatus, mockHttpServletResponse.getStatus());
    assertTrue(mockHttpServletResponse.isCommitted());
  }

  @Test
  void whenRouteNotFoundException_thenCorrectResponseWithErrorMessageIsReturned() {
    final String originCountry = "USA";
    final String destinationCountry = "CZE";

    ResponseEntity<Map<String, String>> responseEntity =
        exceptionHandler.customException(new RouteNotFoundException(originCountry, destinationCountry));

    Map<String, String> responseEntityMap = responseEntity.getBody();

    assertNotNull(responseEntityMap);
    assertNotNull(responseEntityMap.get("message"));
    assertEquals(String.format("Could not find a land route between %s and %s", originCountry, destinationCountry),
        responseEntityMap.get("message"));
  }

  @Test
  void whenCountryNotFoundException_thenCorrectResponseWithErrorMessageIsReturned() {
    final String countryCode = "AAA";

    ResponseEntity<Map<String, String>> responseEntity =
        exceptionHandler.customException(new CountryNotFoundException(countryCode));

    Map<String, String> responseEntityMap = responseEntity.getBody();

    assertNotNull(responseEntityMap);
    assertNotNull(responseEntityMap.get("message"));
    assertEquals(String.format("Could not find a country with code %s", countryCode), responseEntityMap.get("message"));
  }
}