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

package net.ljcomputing.ecsr.security.model;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;

/**
 * User context.
 *
 * @author James G. Willmore
 */
public final class UserContext {

  /** The username. */
  private final String username;

  /** The authorities. */
  private final List<GrantedAuthority> authorities;

  /**
   * Instantiates a new user context.
   *
   * @param username the username
   * @param authorities the authorities
   */
  private UserContext(final String username, final List<GrantedAuthority> authorities) {
    this.username = username;
    this.authorities = authorities;
  }

  /**
   * Creates the user context.
   *
   * @param username the username
   * @param authorities the authorities
   * @return the user context
   */
  public static UserContext create(final String username,
      final List<GrantedAuthority> authorities) {
    if (StringUtils.isBlank(username)) {
      throw new IllegalArgumentException("Username is blank: " + username);
    }
    
    return new UserContext(username, authorities);
  }

  /**
   * Gets the username.
   *
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets the authorities.
   *
   * @return the authorities
   */
  public List<GrantedAuthority> getAuthorities() {
    return authorities;
  }

}
