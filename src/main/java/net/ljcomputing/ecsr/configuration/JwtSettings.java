package net.ljcomputing.ecsr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * JWT settings.
 */
@Configuration
public class JwtSettings {

  /**
   * Refresh token expiration time.
   *
   * @return the integer
   */
  @Bean
  public Integer refreshTokenExpTime() {
    return 60;
  }

  /**
   * Token expiration time.
   *
   * @return the integer
   */
  @Bean
  public Integer tokenExpirationTime() {
    return 15;
  }

  /**
   * Token issuer.
   *
   * @return the string
   */
  @Bean
  public String tokenIssuer() {
    return "http://localhost";
  }

  /**
   * Token signing key.
   *
   * @return the string
   */
  @Bean
  public String tokenSigningKey() {
    return "cmFuZG9tX3NlY3JldF9rZXk=";
  }
}
