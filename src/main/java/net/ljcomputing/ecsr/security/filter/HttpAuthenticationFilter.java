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

/**
 * @author James G. Willmore
 *
 */
public class HttpAuthenticationFilter {// extends RequestHeaderAuthenticationFilter {
//
//  /** The Constant LOGGER. */
//  private static final Logger LOGGER = LoggerFactory.getLogger(HttpAuthenticationFilter.class);
//  
//  public HttpAuthenticationFilter() {
//    this.setExceptionIfHeaderMissing(false);
//    this.setPrincipalRequestHeader(WebSecurityConfiguration.AUTHORIZATION_HEADER);
//  }
//
//  @Override
//  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
//    super.setAuthenticationManager(authenticationManager);
//  }
//
//  @Override
//  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//      throws IOException, ServletException {
//    LOGGER.debug("    =====>>>> Doing it baby!");
//    final HttpServletRequest req = (HttpServletRequest) request;
//    final HttpServletResponse res = (HttpServletResponse) response;
//    LOGGER.debug("token: {}", req.getHeader(WebSecurityConfiguration.AUTHORIZATION_HEADER));
//    super.doFilter(request, response, chain);
//  }
//
//  @Override
//  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
//      Authentication authResult) throws IOException, ServletException {
//    LOGGER.debug("successfulAuthentication");
//    super.successfulAuthentication(request, response, authResult);
//  }
//
//  @Override
//  protected void unsuccessfulAuthentication(HttpServletRequest request,
//      HttpServletResponse response, AuthenticationException failed)
//      throws IOException, ServletException {
//    LOGGER.debug("unsuccessfulAuthentication");
//    super.unsuccessfulAuthentication(request, response, failed);
//  }
}
