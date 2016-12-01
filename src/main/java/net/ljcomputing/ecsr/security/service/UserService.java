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

package net.ljcomputing.ecsr.security.service;

import java.util.List;

import net.ljcomputing.ecsr.domain.person.EcsrRole;
import net.ljcomputing.ecsr.domain.person.User;
import net.ljcomputing.ecsr.domain.person.UserPassword;
import net.ljcomputing.ecsr.domain.person.UserRoles;

/**
 * Security user service.
 * 
 * @author James G. Willmore
 *
 */
public interface UserService {
  
  /**
   * Gets the by username.
   *
   * @param username the username
   * @return the by username
   */
  User getByUsername(final String username);
  
  /**
   * Save (create / update) user.
   *
   * @param user the user
   * @return the user
   */
  User saveUser(final User user);
  
  /**
   * Gets the password associated with the user.
   *
   * @param user the user
   * @return the password
   */
  String getPassword(final User user);
  
  /**
   * Save user's password.
   *
   * @param password the password
   * @return the user password
   */
  UserPassword savePassword(final UserPassword password);
  
  /**
   * Change user's password.
   *
   * @param user the user
   * @param newPassword the new password
   * @return the user password
   */
  UserPassword changePassword(final User user, final String newPassword);
  
  /**
   * Match password.
   *
   * @param user the user
   * @param password the password
   * @return true, if successful
   */
  boolean matchPassword(final User user, final String password);
  
  /**
   * Gets the user's roles.
   *
   * @param user the user
   * @return the user roles
   */
  List<EcsrRole> getUserRoles(final User user);
  
  /**
   * Save user's roles.
   *
   * @param roles the roles
   * @return the user roles
   */
  UserRoles saveUserRoles(final UserRoles roles);
  
  /**
   * Saves (create / update) the role.
   *
   * @param role the role
   * @return the ecsr role
   */
  EcsrRole saveRole(final EcsrRole role);
  
  /**
   * Find all ECSR roles.
   *
   * @return the list
   */
  List<EcsrRole> findAllEcsrRoles();
  
  /**
   * Delete role.
   *
   * @param role the role
   */
  void deleteRole(final EcsrRole role);
}
