package net.ljcomputing.ecsr.security.token;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import net.ljcomputing.ecsr.exception.JwtExpiredTokenException;

/**
 * Raw access JWT token.
 * 
 * @author James G. Willmore
 */
public class RawAccessJwtToken {
  
  /** The header prefix. */
  private static final String HEADER_PREFIX = "Bearer ";

  /** The token. */
  private final transient String token;

  /**
   * Instantiates a new raw access jwt token.
   *
   * @param token the token
   */
  public RawAccessJwtToken(final String token) {
    this.token = token;
  }

  /**
   * Parses and validates JWT Token signature.
   *
   * @param signingKey the signing key
   * @return the jws
   * @throws BadCredentialsException the bad credentials exception
   * @throws JwtExpiredTokenException the jwt expired token exception
   */
  public Jws<Claims> parseClaims(final String signingKey) {
    try {
      return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(this.token); // NOPMD
    } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException
        | SignatureException ex) {
      throw new BadCredentialsException("Invalid JWT token: ", ex);
    } catch (ExpiredJwtException expiredEx) {
      throw new JwtExpiredTokenException("JWT Token expired", expiredEx);
    }
  }
  
  /**
   * Extract header.
   *
   * @param header the header
   * @return the string
   */
  public static String extractHeader(final String header) {
    if (StringUtils.isBlank(header)) {
      throw new AuthenticationServiceException("Authorization header cannot be blank!");
    }

    if (header.length() < HEADER_PREFIX.length()) {
      throw new AuthenticationServiceException("Invalid authorization header size.");
    }

    return header.substring(HEADER_PREFIX.length(), header.length());
  }
}
