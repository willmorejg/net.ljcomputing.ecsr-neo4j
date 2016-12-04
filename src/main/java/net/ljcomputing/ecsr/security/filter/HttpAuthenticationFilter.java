/**
           Copyright 2016, James G. Willmore

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

package net.ljcomputing.ecsr.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import net.ljcomputing.ecsr.configuration.WebSecurityConfiguration;

/**
 * @author James G. Willmore
 *
 */
public class HttpAuthenticationFilter extends RequestHeaderAuthenticationFilter {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(HttpAuthenticationFilter.class);

  /** The Constant AUDIT. */
  private static final Logger AUDIT = 
      LoggerFactory.getLogger(WebSecurityConfiguration.AUDIT_LOGGER);

  /**
   * Instantiates a new http authentication filter.
   */
  public HttpAuthenticationFilter() {
    this.setExceptionIfHeaderMissing(false);
    this.setPrincipalRequestHeader(WebSecurityConfiguration.AUTHORIZATION_HEADER);
  }

  /**
   * @see org.springframework.security.web.authentication.preauth.
   *    AbstractPreAuthenticatedProcessingFilter
   *        #setAuthenticationManager(
   *            org.springframework.security.authentication.AuthenticationManager)
   */
  @Override
  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    super.setAuthenticationManager(authenticationManager);
  }

  /**
   * @see org.springframework.security.web.authentication.preauth.
   *    AbstractPreAuthenticatedProcessingFilter
   *        #doFilter(javax.servlet.ServletRequest, 
   *            javax.servlet.ServletResponse, javax.servlet.FilterChain)
   */
  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    LOGGER.debug("    =====>>>> Doing it baby!");
    final HttpServletRequest req = (HttpServletRequest) request;
    final HttpServletResponse res = (HttpServletResponse) response;
    LOGGER.debug("token: {}", req.getHeader(WebSecurityConfiguration.AUTHORIZATION_HEADER));
    super.doFilter(request, response, chain);
  }

  /**
   * @see org.springframework.security.web.authentication.preauth.
   *    AbstractPreAuthenticatedProcessingFilter
   *        #successfulAuthentication(javax.servlet.http.HttpServletRequest, 
   *            javax.servlet.http.HttpServletResponse, 
   *                org.springframework.security.core.Authentication)
   */
  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      Authentication authResult) throws IOException, ServletException {
    LOGGER.debug("successfulAuthentication");
    super.successfulAuthentication(request, response, authResult);
  }

  /**
   * @see org.springframework.security.web.authentication.preauth.
   *    AbstractPreAuthenticatedProcessingFilter
   *        #unsuccessfulAuthentication(javax.servlet.http.HttpServletRequest, 
   *            javax.servlet.http.HttpServletResponse, 
   *                org.springframework.security.core.AuthenticationException)
   */
  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request,
      HttpServletResponse response, AuthenticationException failed)
      throws IOException, ServletException {
    LOGGER.debug("unsuccessfulAuthentication");
    super.unsuccessfulAuthentication(request, response, failed);
  }
}
