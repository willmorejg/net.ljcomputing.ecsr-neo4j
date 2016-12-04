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
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

import net.ljcomputing.ecsr.security.entrypoint.AuthEntryPoint;
import net.ljcomputing.ecsr.security.filter.AuthFilter;
import net.ljcomputing.ecsr.security.providers.JwtAuthenticationProvider;
import net.ljcomputing.ecsr.security.service.JwtTokenService;

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
  
  /** The audit logger. */
  public static final String AUDIT_LOGGER = "audit";
  
  /** The authorization header. */
  public static final String AUTHORIZATION_HEADER = "Authorization"; // NOPMD

  /** The authorization bearer header. */
  public static final String BEARER_HEADER = "Bearer ";

  /** The authorization bearer header length. */
  public static final int BEARER_LENGTH = BEARER_HEADER.length();

  /** The authorities key - the key to use when setting the JWT Claims. */
  public static final String AUTHORITIES_KEY = "authorities";
  
  /** The authentication and authorization entry point. */
  public static final String AUTH_ENTRY_POINT = "/auth/**";
  
  /** The Constant TOKEN_BASED_AUTH_ENTRY_POINT. */
  public static final String TOKEN_BASED_AUTH_ENTRY_POINT = "/**";
  
  /** The Constant TOKEN_REFRESH_ENTRY_POINT. */
  public static final String TOKEN_REFRESH_ENTRY_POINT = "/token";

  /** The JWT authentication provider. */
  @Autowired
  private JwtAuthenticationProvider jwtAuthenticationProvider;
  
  /** The jwt token service. */
  @Autowired
  private JwtTokenService jwtTokenService;

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
        .authenticationEntryPoint(new AuthEntryPoint())
        .and()
        .csrf().disable()
        .headers().cacheControl().and().frameOptions().disable()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .anonymous()
        .and()
        .servletApi()
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, Locations.FAV_ICO_LOCATION).permitAll()
        .antMatchers(HttpMethod.GET, Locations.INDEX_LOCATION).permitAll()
        .antMatchers(HttpMethod.GET, Locations.WEBJARS_LOCATION).permitAll()
        .antMatchers(HttpMethod.GET, Locations.CSS_LOCATION).permitAll()
        .antMatchers(HttpMethod.GET, Locations.JS_LOCATION).permitAll()
        .antMatchers(HttpMethod.GET, Locations.FONTS_LOCATION).permitAll()
        .antMatchers(HttpMethod.GET, Locations.JS_VIEWS_LOCATION).permitAll()
        .antMatchers(AUTH_ENTRY_POINT).permitAll()
        .antMatchers(Locations.ERROR_LOCATION).permitAll()
        .and()
        .authorizeRequests()
        .anyRequest().fullyAuthenticated()
        .and()
        .addFilterBefore(new AuthFilter(jwtTokenService), RequestHeaderAuthenticationFilter.class);
  }
  
  /**
   * The Locations (paths / URLs) singleton.
   */
  public static final class Locations {

    // paths / locations (URLs) that allow any GET request
    
    /** The favicon location. */
    public static final String FAV_ICO_LOCATION = "**/favicon.ico";
    
    /** The index.htm page - the single page application. */
    public static final String INDEX_LOCATION = "/index.htm";
    
    /** The webjars location - JAR-packaged JavaScript. */
    public static final String WEBJARS_LOCATION = "/webjars/**";
    
    /** The CSS files location. */
    public static final String CSS_LOCATION = "/css/**";
    
    /** The application-specific JavaScript files location. */
    public static final String JS_LOCATION = "/js/**";
    
    /** The font files location. */
    public static final String FONTS_LOCATION = "/fonts/**";
    
    /** The AngularJS views location. */
    public static final String JS_VIEWS_LOCATION = "/views/**";
    
    // other permitted paths / locations (URLs)
    
    /** The location of the error servlet */
    public static final String ERROR_LOCATION = "/error";

    /**
     * Instantiates a new locations.
     */
    private Locations() {
      // no-op
    }
  }
}
