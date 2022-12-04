package com.challenge.countries.exception.handler;

import com.challenge.countries.exception.CountryNotFoundException;
import com.challenge.countries.exception.RouteNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Exception handler for Path variables validation.
   *
   * @param response HTTP response
   * @throws IOException InputOutput Exception
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public void constraintViolationException(HttpServletResponse response) throws IOException {
    response.sendError(HttpStatus.BAD_REQUEST.value());
  }

  /**
   * Exception handler for exceptions as route not found and country not found.
   *
   * @param runtimeException parent of the custom exceptions
   * @return Bad Request ResponseEntity with exception message
   */
  @ResponseBody
  @ExceptionHandler({RouteNotFoundException.class, CountryNotFoundException.class})
  ResponseEntity<Map<String, String>> customException(RuntimeException runtimeException) {
    return ResponseEntity.badRequest().body(Collections.singletonMap("message", runtimeException.getMessage()));
  }
}
