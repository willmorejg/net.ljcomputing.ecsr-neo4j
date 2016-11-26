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

import io.jsonwebtoken.Claims;

/**
 * Access JWT Token.
 *
 * @author James G. Willmore
 */
public class AccessJwtToken {

  /** The raw token. */
  private final String rawToken; // NOPMD

  /** The claims. */
  private final transient Claims claims;

  /**
   * Instantiates a new access JWT token.
   *
   * @param token the token
   * @param claims the claims
   */
  protected AccessJwtToken(final String token, final Claims claims) {
    this.rawToken = token;
    this.claims = claims;
  }

  /**
   * Gets the token.
   *
   * @return the token
   */
  public String getToken() {
    return this.rawToken;
  }

  /**
   * Gets the claims.
   *
   * @return the claims
   */
  public Claims getClaims() {
    return claims;
  }

}
