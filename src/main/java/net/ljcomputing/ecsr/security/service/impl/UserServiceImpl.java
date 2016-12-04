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

package net.ljcomputing.ecsr.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.ecsr.domain.person.EcsrRole;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.domain.person.User;
import net.ljcomputing.ecsr.domain.person.UserPassword;
import net.ljcomputing.ecsr.domain.person.UserRoles;
import net.ljcomputing.ecsr.repository.person.EcsrRoleRepository;
import net.ljcomputing.ecsr.repository.person.PersonRepository;
import net.ljcomputing.ecsr.repository.person.UserPasswordRepository;
import net.ljcomputing.ecsr.repository.person.UserRepository;
import net.ljcomputing.ecsr.repository.person.UserRolesRepository;
import net.ljcomputing.ecsr.security.service.UserService;

/**
 * Security user service implementation.
 *
 * @author James G. Willmore
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
  
  /** The password encoder. */
  @Autowired
  private PasswordEncoder passwordEncoder;
  
  /** The user repository. */
  @Autowired
  private UserRepository userRepos;
  
  /** The user password repository. */
  @Autowired
  private UserPasswordRepository userPassRepos;
  
  /** The user roles repository. */
  @Autowired
  private UserRolesRepository userRolesRepos;
  
  /** The ECSR role repository. */
  @Autowired
  private EcsrRoleRepository ecsrRoleRepos;
  
  /** The ECSR person repository. */
  @Autowired
  private PersonRepository personRepos;

  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #getByUsername(java.lang.String)
   */
  @Override
  public User getByUsername(final String username) {
    return userRepos.findByUsername(username);
  }
  
  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #getPerson(net.ljcomputing.ecsr.domain.person.User)
   */
  @Override
  public Person getPerson(final User user) {
    final String username = user.getUsername();
    return personRepos.locateByUsername(username);
  }
  
  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #saveUser(net.ljcomputing.ecsr.domain.person.User)
   */
  @Override
  public User saveUser(final User user) {
    return userRepos.save(user);
  }

  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #getPassword(net.ljcomputing.ecsr.domain.person.User)
   */
  @Override
  public String getPassword(final User user) {
    final String username = user.getUsername();
    final UserPassword result = userPassRepos.findByUsername(username);
    
    if (result != null) {
      return result.getPassword(); // NOPMD
    }
    
    throw new BadCredentialsException("Authentication Failed. Username or Password not valid.");
  }
  
  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #savePassword(net.ljcomputing.ecsr.domain.person.UserPassword)
   */
  @Override
  public UserPassword savePassword(final UserPassword password) {
    password.setPassword(createPassword(password.getPassword()));
    return userPassRepos.save(password);
  }

  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #changePassword(net.ljcomputing.ecsr.domain.person.User, java.lang.String)
   */
  @Override
  public UserPassword changePassword(final User user, final String newPassword) {
    final String username = user.getUsername();
    final UserPassword userPassword = userPassRepos.findByUsername(username);
    userPassword.setPassword(createPassword(newPassword)); // NOPMD
    return userPassRepos.save(userPassword);
  }
  
  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #matchPassword(net.ljcomputing.ecsr.domain.person.User, java.lang.String)
   */
  @Override
  public boolean matchPassword(final User user, final String password) {
    final String userPassword = getPassword(user);
    return passwordEncoder.matches(password, userPassword);
  }
  
  /**
   * Creates the password.
   *
   * @param password the password
   * @return the string
   */
  private String createPassword(final String password) {
    return passwordEncoder.encode(password);
  }
  
  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #getUserRoles(net.ljcomputing.ecsr.domain.person.User)
   */
  @Override
  public List<EcsrRole> getUserRoles(final User user) {
    final String username = user.getUsername();
    return (List<EcsrRole>) ecsrRoleRepos.findByUsername(username);
  }
  
  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #saveUserRoles(net.ljcomputing.ecsr.domain.person.UserRoles)
   */
  @Override
  public UserRoles saveUserRoles(final UserRoles roles) {
    return userRolesRepos.save(roles);
  }
  
  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #findAllEcsrRoles()
   */
  @Override
  public List<EcsrRole> findAllEcsrRoles() {
    return (List<EcsrRole>) ecsrRoleRepos.findAll();
  }

  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #saveRole(net.ljcomputing.ecsr.domain.person.EcsrRole)
   */
  @Override
  public EcsrRole saveRole(final EcsrRole role) {
    return ecsrRoleRepos.save(role);
  }
  
  /**
   * @see net.ljcomputing.ecsr.security.service.UserService
   *    #deleteRole(net.ljcomputing.ecsr.domain.person.EcsrRole)
   */
  @Override
  public void deleteRole(final EcsrRole role) {
    ecsrRoleRepos.delete(role);
  }
}
