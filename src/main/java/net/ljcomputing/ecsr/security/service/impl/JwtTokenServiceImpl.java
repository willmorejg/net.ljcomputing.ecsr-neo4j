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

package net.ljcomputing.ecsr.security.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import net.ljcomputing.ecsr.configuration.WebSecurityConfiguration;
import net.ljcomputing.ecsr.security.service.JwtTokenService;

/**
 * JWT token.
 * 
 * @author James G. Willmore
 *
 */
@Service
public final class JwtTokenServiceImpl implements JwtTokenService {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenServiceImpl.class);

  /** The Constant AUDIT. */
  private static final Logger AUDIT = 
      LoggerFactory.getLogger(WebSecurityConfiguration.AUDIT_LOGGER);

  /** The zone id - default is the system default zone id. */
  private static final ZoneId ZONE_ID = ZoneId.systemDefault();

  /** The local date and time now. */
  private LocalDateTime ldtNow; // NOPMD

  /** The refresh token exp time. */
  @Autowired
  @Qualifier("refreshTokenExpTime")
  private Integer refreshTokenExpTime; // NOPMD

  /** The token expiration time. */
  @Autowired
  @Qualifier("tokenExpirationTime")
  private Integer tokenExpirationTime; // NOPMD

  /** The token issuer. */
  @Autowired
  @Qualifier("tokenIssuer")
  private String tokenIssuer; // NOPMD

  /** The token signing key. */
  @Autowired
  @Qualifier("tokenSigningKey")
  private String tokenSigningKey; // NOPMD

  /**
   * Local date and time now.
   *
   * @return the local date time
   */
  @PostConstruct
  private void localNow() { // NOPMD
    ldtNow = LocalDateTime.now();
  }

  /**
   * The date now.
   *
   * @return the date
   */
  private Date now() {
    final ZonedDateTime zdt = ldtNow.atZone(ZONE_ID); // NOPMD
    final Instant instant = zdt.toInstant(); // NOPMD
    return Date.from(instant); // NOPMD
  }

  /**
   * Calculate the new expiration date from now.
   *
   * @return the date
   */
  private Date expirationDate() {
    final LocalDateTime expirationLdt = ldtNow.plusMinutes(tokenExpirationTime); // NOPMD
    final ZonedDateTime zdt = expirationLdt.atZone(ZONE_ID); // NOPMD
    final Instant instant = zdt.toInstant(); // NOPMD
    return Date.from(instant); // NOPMD

  }

  /**
   * Calculate the new refresh date.
   *
   * @return the date
   */
  @SuppressWarnings("unused")
  private Date refreshDate() {
    final LocalDateTime refreshLdt = ldtNow.plusMinutes(refreshTokenExpTime); // NOPMD
    final ZonedDateTime zdt = refreshLdt.atZone(ZONE_ID); // NOPMD
    final Instant instant = zdt.toInstant(); // NOPMD
    return Date.from(instant); // NOPMD
  }

  /**
   * @see net.ljcomputing.ecsr.security.service.impl.JwtTokenService
   *    #create(org.springframework.security.core.Authentication)
   */
  @Override
  public String create(final Authentication authentication) {
    if (authentication == null) {
      LOGGER.error("NO TOKEN");
      throw new BadCredentialsException("No authentication provided.");
    }

    final String authorities = authentication.getAuthorities().stream() // NOPMD
        .map(authority -> authority.getAuthority()).collect(Collectors.joining(","));

    final String result = Jwts.builder() // NOPMD
        .setSubject(authentication.getName()).setIssuer(tokenIssuer).setIssuedAt(now())
        .setExpiration(expirationDate())
        .claim(WebSecurityConfiguration.AUTHORITIES_KEY, authorities)
        .signWith(SignatureAlgorithm.HS512, tokenSigningKey).compact();

    if (!isValid(result)) {
      throw new BadCredentialsException("Token is invalid");
    }

    return result;
  }

  /**
   * @see net.ljcomputing.ecsr.security.service.impl.JwtTokenService
   *    #getTokenFromRequest(javax.servlet.http.HttpServletRequest)
   */
  @Override
  public String getTokenFromRequest(final HttpServletRequest request) {
    final String bearerToken = request.getHeader(WebSecurityConfiguration.AUTHORIZATION_HEADER);
    String result = null; // NOPMD

    if (StringUtils.isNotBlank(bearerToken)
        && bearerToken.startsWith(WebSecurityConfiguration.BEARER_HEADER)) { // NOPMD
      result = bearerToken.substring( // NOPMD
          WebSecurityConfiguration.BEARER_LENGTH, bearerToken.length()); // NOPMD
    }

    return result;
  }

  /**
   * @see net.ljcomputing.ecsr.security.service.impl.JwtTokenService
   *    #getTokenAuthentication(java.lang.String)
   */
  @Override
  public Authentication getTokenAuthentication(final String token) {
    if (token == null) {
      throw new BadCredentialsException("Token provided was invalid. NOT");
    }
    
    try {
      final Claims claims = Jwts.parser().setSigningKey(tokenSigningKey) // NOPMD
          .parseClaimsJws(token).getBody();

      final Collection<? extends GrantedAuthority> authorities = Arrays
          .asList(
              claims.get(WebSecurityConfiguration.AUTHORITIES_KEY) // NOPMD
                .toString().split(",")).stream() // NOPMD
          .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());

      final String subject = claims.getSubject(); // NOPMD
      final User principal = new User(subject, "", authorities);

      return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
        | SignatureException | IllegalArgumentException | NullPointerException exception) {
      throw new BadCredentialsException("Token provided was invalid.");
    }
  }

  /**
   * @see net.ljcomputing.ecsr.security.service.impl.JwtTokenService
   *    #validate(javax.servlet.http.HttpServletRequest)
   */
  @Override
  public Set<String> validate(final HttpServletRequest request) {
    return validate(getTokenFromRequest(request));
  }

  /**
   * @see net.ljcomputing.ecsr.security.service.impl.JwtTokenService
   *    #validate(java.lang.String)
   */
  @Override
  public Set<String> validate(final String token) {
    final Set<String> result = new HashSet<String>(); // NOPMD

    if (token == null) {
      result.add("No token found with request."); // NOPMD
      AUDIT.error("No token found with request.");
    }

    if (!result.isEmpty()) { // NOPMD
      Claims claims = null; // NOPMD

      try {
        final Jws<Claims> jws = Jwts.parser().setSigningKey(tokenSigningKey) // NOPMD
            .parseClaimsJws(token);
        claims = jws.getBody(); // NOPMD
      } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException
          | SignatureException | IllegalArgumentException exception) { // NOPMD
        result.add("Signature is invalid."); // NOPMD
        AUDIT.error("Signature is invalid.");
      }
      if (claims != null) {
        final String subject = claims.getSubject(); // NOPMD

        if (StringUtils.isBlank(subject)) { // NOPMD
          result.add("Username is invalid."); // NOPMD
          AUDIT.error("Username is invalid.");
        }

        if (claims.get(WebSecurityConfiguration.AUTHORITIES_KEY) == null) { // NOPMD
          result.add("Invalid authorities."); // NOPMD
          AUDIT.error("Invalid authorities.");
        } else {
          final String authorities = claims.get( // NOPMD
              WebSecurityConfiguration.AUTHORITIES_KEY).toString(); // NOPMD

          if (StringUtils.isBlank(authorities)) { // NOPMD
            result.add("No authorities found."); // NOPMD
            AUDIT.error("No authorities found.");
          }
        }
      }
    }

    return result;
  }

  /**
   * @see net.ljcomputing.ecsr.security.service.impl.JwtTokenService
   *    #isValid(javax.servlet.http.HttpServletRequest)
   */
  @Override
  public boolean isValid(final HttpServletRequest request) {
    return isValid(getTokenFromRequest(request));
  }

  /**
   * @see net.ljcomputing.ecsr.security.service.impl.JwtTokenService
   *    #isValid(java.lang.String)
   */
  @Override
  public boolean isValid(final String token) {
    return validate(token).isEmpty(); // NOPMD
  }
}
