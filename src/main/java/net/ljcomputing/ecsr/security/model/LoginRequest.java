package net.ljcomputing.ecsr.security.model;

/**
 * AJAX-based authentication model.
 * 
 * @author James G. Willmore
 */
public class LoginRequest {

  /** The username. */
  private String username;

  /** The password. */
  private String password;

  /**
   * Gets the username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Sets the username.
   *
   * @param username the new username
   */
  public void setUsername(final String username) {
    this.username = username;
  }

  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * Sets the password.
   *
   * @param password the new password
   */
  public void setPassword(final String password) {
    this.password = password;
  }
}
