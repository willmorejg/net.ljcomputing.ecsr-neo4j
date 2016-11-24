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
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import net.ljcomputing.ecsr.domain.person.EcsrRole;
import net.ljcomputing.ecsr.domain.person.User;
import net.ljcomputing.ecsr.security.model.UserContext;
import net.ljcomputing.ecsr.security.service.UserService;

/**
 * @author James G. Willmore
 *
 */
public class AjaxAuthenticationProvider implements AuthenticationProvider {

  /** The encoder. */
  private final transient BCryptPasswordEncoder encoder;

  /** The user service. */
  private final transient UserService userService;

  /**
   * Instantiates a new ajax authentication provider.
   *
   * @param userService the user service
   * @param encoder the encoder
   */
  @Autowired
  public AjaxAuthenticationProvider(final UserService userService,
      final BCryptPasswordEncoder encoder) {
    this.userService = userService;
    this.encoder = encoder;
  }

  /**
   * @see org.springframework.security.authentication.AuthenticationProvider
   *    #authenticate(org.springframework.security.core.Authentication)
   */
  @Override
  public Authentication authenticate(final Authentication authentication)
      throws AuthenticationException {
    Assert.notNull(authentication, "No authentication data provided");

    final String username = (String) authentication.getPrincipal();
    final String password = (String) authentication.getCredentials();

    final User user = userService.getByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

    final String userPassword = userService.getPassword(user);

    if (!encoder.matches(password, userPassword)) {
      throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
    }

    final List<EcsrRole> roles = userService.getUserRoles(user);

    if (roles == null) {
      throw new InsufficientAuthenticationException("User has no roles assigned");
    }

    final List<GrantedAuthority> authorities = roles.stream()
        .map(authority -> new SimpleGrantedAuthority(authority.getRoleName()))
        .collect(Collectors.toList());

    final UserContext userContext = UserContext.create(user.getUsername(), authorities);

    return new UsernamePasswordAuthenticationToken(
        userContext, null, userContext.getAuthorities()); // NOPMD
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
