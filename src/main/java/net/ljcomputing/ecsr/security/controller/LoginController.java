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

package net.ljcomputing.ecsr.security.controller;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.ljcomputing.ecsr.configuration.WebSecurityConfiguration;
import net.ljcomputing.ecsr.domain.person.EcsrRole;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.domain.person.User;
import net.ljcomputing.ecsr.security.service.JwtTokenService;
import net.ljcomputing.ecsr.security.service.UserService;

/**
 * Login controller.
 * 
 * @author James G. Willmore
 *
 */
@RestController
public class LoginController {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
  
  /** The audit logger. */
  private static final Logger AUDIT = 
      LoggerFactory.getLogger(WebSecurityConfiguration.AUDIT_LOGGER);

  /** The user service. */
  @Autowired
  private UserService userService;

  /** The token service. */
  @Autowired
  private JwtTokenService tokenService;
  
  /** The response entity. */
  private ResponseEntity<JsonAuthToken> responseEntity;

  /**
   * Login.
   *
   * @param body the body
   * @param response the response
   * @return the response entity
   */
  @RequestMapping("/auth/login")
  public ResponseEntity<JsonAuthToken> login(@RequestBody final LoginInformation body,
      final HttpServletResponse response) {
    AUDIT.info("Attempting to authenticate {}", body.username);

    SecurityContextHolder.clearContext();

    final JsonAuthToken jsonAuthToken = new JsonAuthToken();
    final User user = userService.getByUsername(body.username);
    
    LOGGER.debug("user: {}", user);

    if (user != null) {
      final Person person = userService.getPerson(user);
      
      if (userService.matchPassword(user, body.password)) {
        final Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
        final List<EcsrRole> roles = userService.getUserRoles(user);

        for (final EcsrRole role : roles) {
          final SimpleGrantedAuthority authority = 
              new SimpleGrantedAuthority(role.getRoleName()); // NOPMD
          
          authorities.add(authority);
        }

        final UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(body.username, "", authorities);

        jsonAuthToken.token = tokenService.create(authToken);

        responseEntity = new ResponseEntity<JsonAuthToken>(
            jsonAuthToken, HttpStatus.OK);

        response.addHeader(WebSecurityConfiguration.AUTHORIZATION_HEADER,
            WebSecurityConfiguration.BEARER_HEADER + tokenService.create(authToken));

        SecurityContextHolder.getContext().setAuthentication(authToken); // NOPMD
        
        AUDIT.info("Authenticated {}", authToken.getName());
      }
    } 
    
    if (user == null) {
      jsonAuthToken.token = "Invalid credentials";
      SecurityContextHolder.clearContext();
      AUDIT.warn("FAILED to authenticate {}", body.username);
      responseEntity = new ResponseEntity<JsonAuthToken>(jsonAuthToken, HttpStatus.UNAUTHORIZED);
    }

    return responseEntity;
  }

  /**
   * Login information.
   */
  private class LoginInformation implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3608083617083832735L;

    /** The username. */
    public String username; // NOPMD

    /** The password. */
    public String password; // NOPMD
  }

  /**
   * JSON authentication and authorization token.
   */
  private class JsonAuthToken implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7283674154081294487L;

    /** The token. */
    @SuppressWarnings("unused")
    public String token; // NOPMD
  }
}
