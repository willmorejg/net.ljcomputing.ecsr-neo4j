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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import net.ljcomputing.ecsr.security.service.JwtTokenService;
import net.ljcomputing.ecsr.security.service.UserService;

/**
 * @author James G. Willmore
 *
 */
public class AuthFilter extends OncePerRequestFilter {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
  private final JwtTokenService jwtTokenService;
  private final UserService userService;
  
  public AuthFilter(final JwtTokenService jwtTokenService, final UserService userService) {
    this.jwtTokenService = jwtTokenService;
    this.userService = userService;
  }

  /* (non-Javadoc)
   * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    if (jwtTokenService.isValid(request)) {
      final String jwtRequestToken = jwtTokenService.getTokenFromRequest(request);
      Authentication auth = jwtTokenService.getTokenAuthentication(jwtRequestToken);
      SecurityContextHolder.getContext().setAuthentication(auth);
      LOGGER.debug("Authenticated: {}", auth.getPrincipal());
    }
    
    filterChain.doFilter(request, response);
  }

}
