package net.ljcomputing.ecsr.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * JWT expired token exception.
 */
public class JwtExpiredTokenException extends AuthenticationException {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -6588808113946144026L;

  /**
   * Instantiates a new JWT expired token exception.
   *
   * @param message the message
   */
  public JwtExpiredTokenException(final String message) {
    super(message);
  }

  /**
   * Instantiates a new JWT expired token exception.
   *
   * @param message the message
   * @param thorwable the Throwable
   */
  public JwtExpiredTokenException(final String message, final Throwable thorwable) {
    super(message, thorwable);
  }
}
