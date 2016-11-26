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

package net.ljcomputing.ecsr.security.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.ljcomputing.ecsr.configuration.WebSecurityConfiguration;
import net.ljcomputing.ecsr.security.model.UserContext;
import net.ljcomputing.ecsr.security.token.JwtAuthenticationToken;
import net.ljcomputing.ecsr.security.token.RawAccessJwtToken;

/**
 * @author James G. Willmore
 *
 */
public class RefreshTokenController {

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
   * Refresh token.
   *
   * @param request the request
   * @param response the response
   * @return the access jwt token
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws ServletException the servlet exception
   */
  @RequestMapping(value = WebSecurityConfiguration.TOKEN_REFRESH_ENTRY_POINT, 
      method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
  public @ResponseBody JwtAuthenticationToken refreshToken(final HttpServletRequest request,
      final HttpServletResponse response) throws IOException, ServletException {
    final ZoneId zoneId = ZoneId.systemDefault();
    final LocalDateTime ldt = LocalDateTime.now();
    final LocalDateTime refreshLdt = ldt.plusMinutes(refreshTokenExpTime);
    final LocalDateTime expirationLdt = ldt.plusMinutes(tokenExpirationTime);
    final Date dateNow = Date.from(ldt.atZone(zoneId).toInstant());
    final Date refreshDate = Date.from(refreshLdt.atZone(zoneId).toInstant());
    final Date expirationDate = Date.from(expirationLdt.atZone(zoneId).toInstant());
    final String header = RawAccessJwtToken
        .extractHeader(request.getHeader(WebSecurityConfiguration.JWT_TOKEN_HEADER));
    final RawAccessJwtToken token = new RawAccessJwtToken(header);
    final Jws<Claims> jwsClaims = token.parseClaims(tokenSigningKey); // NOPMD
    final String subject = jwsClaims.getBody().getSubject(); // NOPMD
    @SuppressWarnings("unchecked")
    final List<String> scopes = jwsClaims.getBody().get("scopes", List.class); // NOPMD
    final List<GrantedAuthority> authorities = scopes.stream()
        .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());

    final UserContext context = UserContext.create(subject, authorities);

    final String accessToken = Jwts.builder().setClaims((Claims) jwsClaims.getBody().get("scopes", List.class)).setIssuer(tokenIssuer)
        .setIssuedAt(dateNow).setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS512, tokenSigningKey).compact();

    return new JwtAuthenticationToken(context, context.getAuthorities()); // NOPMD
  }
}
