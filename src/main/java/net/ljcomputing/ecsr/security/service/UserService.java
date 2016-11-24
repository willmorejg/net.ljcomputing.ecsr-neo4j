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
import java.util.Optional;

import net.ljcomputing.ecsr.domain.person.EcsrRole;
import net.ljcomputing.ecsr.domain.person.User;

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
  Optional<User> getByUsername(final String username);
  
  /**
   * Gets the password associated with the user.
   *
   * @param user the user
   * @return the password
   */
  String getPassword(final User user);
  
  /**
   * Gets the user's roles.
   *
   * @param user the user
   * @return the user roles
   */
  List<EcsrRole> getUserRoles(final User user);
}
