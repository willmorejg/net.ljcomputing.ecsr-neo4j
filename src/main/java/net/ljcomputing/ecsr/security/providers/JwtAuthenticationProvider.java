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

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import net.ljcomputing.ecsr.security.model.UserContext;
import net.ljcomputing.ecsr.security.token.JwtAuthenticationToken;
import net.ljcomputing.ecsr.security.token.RawAccessJwtToken;

/**
 * JWT authentication provider.
 * 
 * @author James G. Willmore
 *
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

  /** The token signing key. */
  @Autowired
  @Value("#{tokenSigningKey}")
  private String tokenSigningKey;

  /**
   * @see org.springframework.security.authentication.AuthenticationProvider
   *    #authenticate(org.springframework.security.core.Authentication)
   */
  @Override
  public Authentication authenticate(final Authentication authentication)
      throws AuthenticationException {
    final RawAccessJwtToken rawAccessToken = (RawAccessJwtToken) authentication.getCredentials();
    final Jws<Claims> jwsClaims = rawAccessToken.parseClaims(tokenSigningKey); // NOPMD
    final String subject = jwsClaims.getBody().getSubject(); // NOPMD
    @SuppressWarnings("unchecked")
    final List<String> scopes = jwsClaims.getBody().get("scopes", List.class); // NOPMD
    final List<GrantedAuthority> authorities = scopes.stream()
        .map(authority -> new SimpleGrantedAuthority(authority)).collect(Collectors.toList());

    final UserContext context = UserContext.create(subject, authorities);

    return new JwtAuthenticationToken(context, context.getAuthorities()); // NOPMD
  }

  /**
   * @see org.springframework.security.authentication.AuthenticationProvider
   *    #supports(java.lang.Class)
   */
  @Override
  public boolean supports(final Class<?> authentication) {
    return (JwtAuthenticationToken.class.isAssignableFrom(authentication)); // NOPMD
  }

}
