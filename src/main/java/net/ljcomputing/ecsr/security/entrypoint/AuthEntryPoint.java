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

package net.ljcomputing.ecsr.security.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import net.ljcomputing.ecsr.configuration.WebSecurityConfiguration;

/**
 * Authentication entry point.
 * 
 * @author James G. Willmore
 *
 */
public class AuthEntryPoint implements AuthenticationEntryPoint {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthEntryPoint.class);

  /** The Constant AUDIT. */
  private static final Logger AUDIT = LoggerFactory
      .getLogger(WebSecurityConfiguration.AUDIT_LOGGER);

  /**
   * @see org.springframework.security.web.AuthenticationEntryPoint
   *    #commence(javax.servlet.http.HttpServletRequest, 
   *        javax.servlet.http.HttpServletResponse, 
   *            org.springframework.security.core.AuthenticationException)
   */
  @Override
  public void commence(final HttpServletRequest request, final HttpServletResponse response,
      final AuthenticationException exception) throws IOException, ServletException {
    LOGGER.debug("Unauthorized request.");
    AUDIT.info("Unauthorized request.");
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized.");
  }

}
