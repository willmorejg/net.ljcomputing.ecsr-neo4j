/**
           Copyright 2015, James G. Willmore

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */

package net.ljcomputing.ecsr.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler for controllers.
 * 
 * @author James G. Willmore
 *
 */
@ControllerAdvice
public class GlobalExceptionController {

  /** The Constant logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);

  /** The timestamp format. */
  private static final DateTimeFormatter FORMAT = DateTimeFormatter
      .ofPattern("MMM d, yyyy h:m:s a");

  /**
   * Gets the current timestamp.
   *
   * @return the current timestamp
   */
  private static String getCurrentTimestamp() {
    return FORMAT.format(LocalDateTime.now());
  }
  
  /**
   * Gets the request path.
   *
   * @param req the req
   * @return the request path
   */
  private static String getRequestPath(final HttpServletRequest req) {
    final StringBuffer reqPath = req.getRequestURL();
    return reqPath.toString(); // NOPMD
  }

  /**
   * Gets the default error info.
   *
   * @param req the req
   * @param exception the exception
   * @return the default error info
   */
  private static ErrorInfo getDefaultErrorInfo(final HttpServletRequest req,
      final Exception exception) {
    return getErrorInfo(req, exception, HttpStatus.BAD_REQUEST);
  }

  /**
   * Gets the error info.
   *
   * @param req the req
   * @param exception the exception
   * @param status the status
   * @return the error info
   */
  private static ErrorInfo getErrorInfo(final HttpServletRequest req, final Exception exception,
      final HttpStatus status) {
    return new ErrorInfo(getCurrentTimestamp(), status, getRequestPath(req), exception);
  }

  /**
   * Handle all exceptions.
   *
   * @param req
   *            the req
   * @param exception
   *            the exception
   * @return the error info
   */
  @Order(Ordered.LOWEST_PRECEDENCE)
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorInfo handleAllExceptions(final HttpServletRequest req,
      final Exception exception) {
    LOGGER.error("An error occured during the processing of {}:", // NOPMD 
        getRequestPath(req), 
        exception);
    return getDefaultErrorInfo(req, exception);
  }

  /**
   * Handle all null pointer exceptions.
   *
   * @param req the req
   * @param exception the exception
   * @return the error info
   */
  @Order(Ordered.HIGHEST_PRECEDENCE)
  @ExceptionHandler(NullPointerException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorInfo handleAllNullPointerExceptions(final HttpServletRequest req,
      final Exception exception) {
    LOGGER.error("The data sent for processing had errors {}:", // NOPMD
        getRequestPath(req),
        exception);
    return getDefaultErrorInfo(req, new Exception("An invalid value was sent or requested."));
  }

  /**
   * Handle all data integrity violation exceptions.
   *
   * @param req
   *            the req
   * @param exception
   *            the exception
   * @return the error info
   */
  @Order(Ordered.HIGHEST_PRECEDENCE)
  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public @ResponseBody ErrorInfo handleAllDataIntegrityViolationExceptions(
      final HttpServletRequest req, final Exception exception) {
    LOGGER.error("The data sent for processing had errors {}:", // NOPMD 
        getRequestPath(req),
        exception);

    ErrorInfo errorInfo = getDefaultErrorInfo(req, exception);
    final String message = exception.getMessage();
    
    if (message.contains("Unique property")) {
      errorInfo = getErrorInfo(req, 
          new Exception("The saved value already exists."), HttpStatus.CONFLICT);
    }

    return errorInfo;
  }

  /**
   * Handle all cypher exceptions.
   *
   * @param req the req
   * @param exception the exception
   * @return the error info
   */
  @Order(Ordered.HIGHEST_PRECEDENCE)
  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody ErrorInfo handleAllCypherExceptions(final HttpServletRequest req,
      final Exception exception) {
    LOGGER.error("The data sent for processing had errors {}:", // NOPMD
        getRequestPath(req),
        exception);

    ErrorInfo errorInfo = getDefaultErrorInfo(req, exception);
    final String message = exception.getMessage();

    if (message.contains("Neo.ClientError.Schema.ConstraintValidationFailed")) {
      LOGGER.info("Neo.ClientError.Schema.ConstraintValidationFailed: {}",
          getRequestPath(req));
      errorInfo = getErrorInfo(req, 
          new Exception("The modified data has dependences."), HttpStatus.CONFLICT);
    }

    return errorInfo;
  }
}
