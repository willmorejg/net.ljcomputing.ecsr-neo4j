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

package net.ljcomputing.ecsr.security.token;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import net.ljcomputing.ecsr.security.model.UserContext;

/**
 * @author James G. Willmore
 *
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 8958142543257569797L;

  /** The raw access token. */
  private RawAccessJwtToken rawAccessToken; // NOPMD

  /** The user context. */
  private UserContext userContext; // NOPMD

  /**
   * Instantiates a new JWT authentication token.
   *
   * @param unsafeToken the unsafe token
   */
  public JwtAuthenticationToken(final RawAccessJwtToken unsafeToken) {
    super(null);
    this.rawAccessToken = unsafeToken;
  }

  /**
   * Instantiates a new JWT authentication token.
   *
   * @param userContext the user context
   * @param authorities the authorities
   */
  public JwtAuthenticationToken(final UserContext userContext,
      final Collection<? extends GrantedAuthority> authorities) {
    super(authorities);
    this.userContext = userContext;
  }

  /**
   * @see org.springframework.security.core.Authentication#getCredentials()
   */
  @Override
  public Object getCredentials() {
    return rawAccessToken;
  }

  /**
   * @see org.springframework.security.core.Authentication#getPrincipal()
   */
  @Override
  public Object getPrincipal() {
    return userContext;
  }
}
