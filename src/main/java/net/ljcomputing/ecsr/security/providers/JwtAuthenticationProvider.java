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

package net.ljcomputing.ecsr.security.providers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import net.ljcomputing.ecsr.security.service.JwtTokenService;

/**
 * JWT authentication provider.
 * 
 * @author James G. Willmore
 *
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
  
  /** The token. */
  @Autowired
  private JwtTokenService token;

  /**
   * @see org.springframework.security.authentication.AuthenticationProvider
   *    #authenticate(org.springframework.security.core.Authentication)
   */
  @Override
  public Authentication authenticate(final Authentication authentication)
      throws AuthenticationException {
    final String tokenString = token.create(authentication);
    return token.getTokenAuthentication(tokenString);
  }

  /**
   * @see org.springframework.security.authentication.AuthenticationProvider
   *    #supports(java.lang.Class)
   */
  @Override
  public boolean supports(final Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication); // NOPMD
  }

}
