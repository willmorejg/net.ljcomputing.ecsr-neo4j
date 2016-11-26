package net.ljcomputing.ecsr.security.handler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.ljcomputing.ecsr.security.model.UserContext;

/**
 * AJAX-aware authentication success handler.
 */
@Component
public class AjaxAwareAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

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
   * On authentication success.
   *
   * @param request the request
   * @param response the response
   * @param authentication the authentication
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws ServletException the servlet exception
   * @see org.springframework.security.web.authentication.
   *    AuthenticationSuccessHandler
   *        #onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, 
   *            javax.servlet.http.HttpServletResponse, 
   *                org.springframework.security.core.Authentication)
   */
  @Override
  public void onAuthenticationSuccess(final HttpServletRequest request,
      final HttpServletResponse response, final Authentication authentication)
      throws IOException, ServletException {
    final UserContext userContext = (UserContext) authentication.getPrincipal();
    final Map<String, String> tokenMap = getTokenMap(userContext);
    final Gson gson = new Gson();

    response.setStatus(HttpStatus.OK.value()); // NOPMD
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    gson.toJson(tokenMap, response.getWriter());

    clearAuthenticationAttributes(request);
  }

  /**
   * Clear authentication attributes.
   *
   * @param request the request
   */
  public final void clearAuthenticationAttributes(final HttpServletRequest request) {
    HttpSession session = request.getSession(false); // NOPMD

    if (session != null) {
      session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION); // NOPMD
    }
  }

  /**
   * Gets the token map.
   *
   * @param userContext the user context
   * @return the token map
   */
  private Map<String, String> getTokenMap(final UserContext userContext) {
    final Map<String, String> tokenMap = new ConcurrentHashMap<String, String>();
    final ZoneId zoneId = ZoneId.systemDefault();
    final LocalDateTime ldt = LocalDateTime.now();
    final LocalDateTime refreshLdt = ldt.plusMinutes(refreshTokenExpTime);
    final LocalDateTime expirationLdt = ldt.plusMinutes(tokenExpirationTime);
    final Date dateNow = Date.from(ldt.atZone(zoneId).toInstant());
    final Date refreshDate = Date.from(refreshLdt.atZone(zoneId).toInstant());
    final Date expirationDate = Date.from(expirationLdt.atZone(zoneId).toInstant());

    if (StringUtils.isBlank(userContext.getUsername())) {
      throw new IllegalArgumentException("Cannot create JWT Token without username");
    }

    if (userContext.getAuthorities() == null || userContext.getAuthorities().isEmpty()) {
      throw new IllegalArgumentException("User doesn't have any privileges");
    }

    final Claims claims = Jwts.claims().setSubject(userContext.getUsername()); // NOPMD
    claims.put("scopes",
        userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

    final String accessToken = Jwts.builder().setClaims(claims).setIssuer(tokenIssuer)
        .setIssuedAt(dateNow).setExpiration(expirationDate)
        .signWith(SignatureAlgorithm.HS512, tokenSigningKey).compact();

    tokenMap.put("token", accessToken);

    final String refreshToken = Jwts.builder().setClaims(claims).setIssuer(tokenIssuer)
        .setId(UUID.randomUUID().toString()).setIssuedAt(dateNow).setExpiration(refreshDate)
        .signWith(SignatureAlgorithm.HS512, tokenSigningKey).compact();

    tokenMap.put("refreshToken", refreshToken);

    return tokenMap;
  }
}
