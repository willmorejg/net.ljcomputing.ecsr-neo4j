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
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import net.ljcomputing.ecsr.security.filter.AuthFilter;
import net.ljcomputing.ecsr.security.providers.JwtAuthenticationProvider;
import net.ljcomputing.ecsr.security.service.JwtTokenService;
import net.ljcomputing.ecsr.security.service.UserService;

/**
 * Web security configuration.
 * 
 * @author James G. Willmore
 *
 */
@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalAuthentication
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  /** The authorization header. */
  public static final String AUTHORIZATION_HEADER = "Authorization"; // NOPMD

  /** The authorization bearer header. */
  public static final String BEARER_HEADER = "Bearer ";

  /** The authorization bearer header length. */
  public static final int BEARER_LENGTH = BEARER_HEADER.length();

  /** The authorities key - the key to use when setting the JWT Claims. */
  public static final String AUTHORITIES_KEY = "authorities";
  
  /** The Constant REALM. */
  public static final String REALM = "ECSR";
  
  /** The Constant FORM_BASED_LOGIN_ENTRY_POINT. */
  public static final String FORM_BASED_LOGIN_ENTRY_POINT = "/login";
  
  /** The Constant TOKEN_BASED_AUTH_ENTRY_POINT. */
  public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
  
  /** The Constant TOKEN_REFRESH_ENTRY_POINT. */
  public static final String TOKEN_REFRESH_ENTRY_POINT = "/token";
  
  /** The Constant WEBJARS_LOCATION. */
  public static final String WEBJARS_LOCATION = "/webjars/**";
  
  /** The Constant CSS_LOCATION. */
  public static final String CSS_LOCATION = "/css/**";
  
  /** The Constant JS_LOCATION. */
  public static final String JS_LOCATION = "/js/**";
  
  /** The Constant JS_LOCATION. */
  public static final String FONTS_LOCATION = "/fonts/**";
  
  /** The Constant JS_LOCATION. */
  public static final String JS_VIEWS_LOCATION = "/views/**";
  
  /** The Constant FAV_ICO_LOCATION. */
  public static final String FAV_ICO_LOCATION = "**/favicon.ico";
  
  /** The Constant INDEX_LOCATION. */
  public static final String INDEX_LOCATION = "/index.htm";

  /** The JWT authentication provider. */
  @Autowired
  private JwtAuthenticationProvider jwtAuthenticationProvider;
  
  /** The jwt token service. */
  @Autowired
  private JwtTokenService jwtTokenService;
  
  /** The user service. */
  @Autowired
  private UserService userService;

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
  public void configure(final AuthenticationManagerBuilder auth) {
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
    http
        .exceptionHandling()
        .and()
        .csrf().disable()
        .headers().cacheControl().and().frameOptions().disable()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//        .and()
//        .httpBasic()
//        .realmName(REALM)
//        .authenticationEntryPoint(new HttpAuthenticationEntryPoint())
        .and()
        .anonymous()
        .and()
        .servletApi()
        .and()
        .authorizeRequests()
        .antMatchers("**/favicon.ico").permitAll()
        .antMatchers("/index.htm").permitAll()
        .antMatchers("/webjars/**").permitAll()
        .antMatchers("/css/**").permitAll()
        .antMatchers("/js/**").permitAll()
        .antMatchers("/fonts/**").permitAll()
        .antMatchers("/views/**").permitAll()
        .antMatchers("/auth/**").permitAll()
        .antMatchers("/error").permitAll()
        .and()
        .authorizeRequests()
        .anyRequest().fullyAuthenticated()
        .and()
        .addFilterBefore(new AuthFilter(jwtTokenService, userService), RequestHeaderAuthenticationFilter.class);
  }
}
