package com.challenge.countries.integration;

import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class RoutingControllerIntegrationTest {

  @Inject
  private MockMvc mockMvc;

  @Test
  void whenGetRouteByOriginAndDestination_thenShortestPathIsReturned() throws Exception {
    mockMvc.perform(get("/routing/CZE/ITA")
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect((jsonPath("$.route", hasSize(3))))
        .andExpect(jsonPath("$.route", containsInRelativeOrder("CZE", "AUT", "ITA")));
  }

  @Test
  void givenSameCountryOriginAndDestination_whenGetRouteByOriginAndDestination_thenReturnJustOneCountry() throws Exception {
    mockMvc.perform(get("/routing/CZE/CZE")
        .contentType(APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect((jsonPath("$.route", hasSize(1))))
        .andExpect(jsonPath("$.route").value("CZE"));
  }

  @Test
  void givenNoLandRouteBetweenCountries_whenGetRouteByOriginAndDestination_thenBadRequestReturned() throws Exception {
    mockMvc.perform(get("/routing/CZE/USA")
        .contentType(APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect((jsonPath("$.message").value("Could not find a land route between CZE and USA")));
  }

}
