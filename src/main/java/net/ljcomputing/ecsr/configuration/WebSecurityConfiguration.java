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

package net.ljcomputing.ecsr.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import net.ljcomputing.ecsr.security.RestAuthenticationEntryPoint;
import net.ljcomputing.ecsr.security.providers.AjaxAuthenticationProvider;

/**
 * Web security configuration.
 * 
 * @author James G. Willmore
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  public static final String JWT_TOKEN_HEADER_PARAM = "X-Authorization";
  public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/api/auth/login";
  public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/api/**";
  public static final String TOKEN_REFRESH_ENTRY_POINT = "/api/auth/token";

  /** The authentication entry point. */
  @Autowired
  private RestAuthenticationEntryPoint authenticationEntryPoint;
  
  /** The success handler. */
  @Autowired
  private AuthenticationSuccessHandler successHandler;
  
  /** The failure handler. */
  @Autowired
  private AuthenticationFailureHandler failureHandler;
  
  /** The ajax authentication provider. */
  @Autowired
  private AjaxAuthenticationProvider ajaxAuthenticationProvider;

  /** The jwt authentication provider. */
//  @Autowired
//  private JwtAuthenticationProvider jwtAuthenticationProvider;
}
