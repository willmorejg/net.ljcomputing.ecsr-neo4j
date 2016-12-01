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
import net.ljcomputing.ecsr.domain.person.User;
import net.ljcomputing.ecsr.security.service.JwtTokenService;
import net.ljcomputing.ecsr.security.service.UserService;

/**
 * @author James G. Willmore
 *
 */
@RestController
public class LoginController {
  
  private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private JwtTokenService tokenService;
  
  @RequestMapping("/auth/login")
  public ResponseEntity<JsonAuthToken> login(@RequestBody LoginInformation body, HttpServletResponse  response) {
    LOGGER.debug("  =====>>>>> JSON: {}", body);
    JsonAuthToken jsonAuthToken = new JsonAuthToken();
    final User user = userService.getByUsername(body.username);
    if (user != null) {
      if (userService.matchPassword(user, body.password)) {
        final Set<SimpleGrantedAuthority> authorities = new HashSet<SimpleGrantedAuthority>();
        final List<EcsrRole> roles = userService.getUserRoles(user);
        
        for (final EcsrRole role : roles) {
          final SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRoleName());
          authorities.add(authority);
        }
        
        UsernamePasswordAuthenticationToken authToken = 
            new UsernamePasswordAuthenticationToken(body.username, "", authorities);
        
        jsonAuthToken.token = tokenService.create(authToken);
        
        ResponseEntity<JsonAuthToken> responseEntity = new ResponseEntity<JsonAuthToken>(jsonAuthToken, HttpStatus.OK);
        response.addHeader(WebSecurityConfiguration.AUTHORIZATION_HEADER,
            WebSecurityConfiguration.BEARER_HEADER + tokenService.create(authToken));
        SecurityContextHolder.getContext().setAuthentication(authToken);
        return responseEntity;
      }
    }
    jsonAuthToken.token = "Invalid credentials";
    return new ResponseEntity<JsonAuthToken>(jsonAuthToken, HttpStatus.UNAUTHORIZED);
  }
  
  class LoginInformation implements Serializable {
    public String username;
    public String password;
  }
  
  class JsonAuthToken implements Serializable {
    public String token;
  }
}
