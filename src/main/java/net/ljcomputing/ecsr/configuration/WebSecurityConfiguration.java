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

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import net.ljcomputing.ecsr.security.RestAuthenticationEntryPoint;
import net.ljcomputing.ecsr.security.SkipPathRequestMatcher;
import net.ljcomputing.ecsr.security.filter.AjaxLoginProcessingFilter;
import net.ljcomputing.ecsr.security.filter.JwtTokenAuthenticationProcessingFilter;
import net.ljcomputing.ecsr.security.providers.AjaxAuthenticationProvider;
import net.ljcomputing.ecsr.security.providers.JwtAuthenticationProvider;

/**
 * Web security configuration.
 * 
 * @author James G. Willmore
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
  
  /** The JWT token header param. */
  public static final String JWT_TOKEN_HEADER = "X-Authorization";
  
  /** The Constant FORM_BASED_LOGIN_ENTRY_POINT. */
  public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/api/auth/login";
  
  /** The Constant TOKEN_BASED_AUTH_ENTRY_POINT. */
  public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
  
  /** The Constant TOKEN_REFRESH_ENTRY_POINT. */
  public static final String TOKEN_REFRESH_ENTRY_POINT = "/auth/token";

  /** The authentication manager. */
  @Autowired
  private AuthenticationManager authenticationManager;

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

  /** The JWT authentication provider. */
  @Autowired
  private JwtAuthenticationProvider jwtAuthenticationProvider;

  /**
   * Builds the ajax login processing filter.
   *
   * @return the ajax login processing filter
   * @throws Exception the exception
   */
  @Bean
  public AjaxLoginProcessingFilter buildAjaxLoginProcessingFilter() 
      throws Exception {
    AjaxLoginProcessingFilter filter = new AjaxLoginProcessingFilter(FORM_BASED_LOGIN_ENTRY_POINT,
        successHandler, failureHandler);
    filter.setAuthenticationManager(this.authenticationManager);
    return filter;
  }

  /**
   * Builds the jwt token authentication processing filter.
   *
   * @return the jwt token authentication processing filter
   * @throws Exception the exception
   */
  @Bean
  protected JwtTokenAuthenticationProcessingFilter buildJwtTokenAuthenticationProcessingFilter()
      throws Exception {
    List<String> pathsToSkip = Arrays.asList(TOKEN_REFRESH_ENTRY_POINT,
        FORM_BASED_LOGIN_ENTRY_POINT);
    SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip,
        TOKEN_BASED_AUTH_ENTRY_POINT);
    JwtTokenAuthenticationProcessingFilter filter = new JwtTokenAuthenticationProcessingFilter(
        failureHandler, matcher);
    filter.setAuthenticationManager(this.authenticationManager);
    return filter;
  }

  /**
   * @see org.springframework.security.config.annotation.web.configuration
   *    .WebSecurityConfigurerAdapter
   *        #authenticationManagerBean()
   */
  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * @see org.springframework.security.config.annotation.web.configuration
   *    .WebSecurityConfigurerAdapter
   *        #configure(org.springframework.security.config.annotation.authentication
   *            .builders.AuthenticationManagerBuilder)
   */
  @Override
  protected void configure(final AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(ajaxAuthenticationProvider);
    auth.authenticationProvider(jwtAuthenticationProvider);
  }

  /**
   * @see org.springframework.security.config.annotation.web.configuration
   *    .WebSecurityConfigurerAdapter
   *        #configure(
   *            org.springframework.security.config.annotation.web.builders
   *                .HttpSecurity)
   */
  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable().exceptionHandling() // NOPMD
        .authenticationEntryPoint(this.authenticationEntryPoint)
        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and().authorizeRequests().antMatchers(FORM_BASED_LOGIN_ENTRY_POINT).permitAll()
        .antMatchers(TOKEN_REFRESH_ENTRY_POINT).permitAll().and().authorizeRequests()
        .antMatchers(TOKEN_BASED_AUTH_ENTRY_POINT).authenticated().and()
        .addFilterBefore(buildAjaxLoginProcessingFilter(),
            UsernamePasswordAuthenticationFilter.class)
        .addFilterBefore(buildJwtTokenAuthenticationProcessingFilter(),
            UsernamePasswordAuthenticationFilter.class);
  }
}
