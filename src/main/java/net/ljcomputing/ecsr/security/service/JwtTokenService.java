package net.ljcomputing.ecsr.security.service;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface JwtTokenService {

  /**
   * Creates the token.
   *
   * @param authentication the authentication
   * @return the string
   */
  String create(Authentication authentication);

  /**
   * Gets the token from request.
   *
   * @param request the request
   * @return the token from request
   */
  String getTokenFromRequest(HttpServletRequest request);

  /**
   * Gets the token authentication.
   *
   * @param token the token
   * @return the token authentication
   */
  Authentication getTokenAuthentication(String token);

  /**
   * Validate the token found in the request.
   *
   * @param request the request
   * @return the sets the
   */
  Set<String> validate(HttpServletRequest request);

  /**
   * Validate the provided token.
   *
   * @param token the token
   * @return the sets the set of errors with the token - empty if the token is valid
   */
  Set<String> validate(String token);

  /**
   * Checks if the token is valid.
   *
   * @param request the request
   * @return true, if the token is valid
   */
  boolean isValid(HttpServletRequest request);

  /**
   * Checks if the token is valid.
   *
   * @param token the token
   * @return true, if the token is valid
   */
  boolean isValid(String token);

}