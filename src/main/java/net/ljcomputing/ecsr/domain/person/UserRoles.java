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

package net.ljcomputing.ecsr.domain.person;

import java.util.ArrayList;
import java.util.List;

import net.ljcomputing.ecsr.domain.AbstractDomain;
import net.ljcomputing.ecsr.domain.Domain;

/**
 * ECSR user roles.
 * 
 * @author James G. Willmore
 *
 */
public class UserRoles extends AbstractDomain implements Domain {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 3553461161585342115L;
  
  /** The user. */
  private User user;
  
  /** The roles. */
  private List<EcsrRole> roles;
  
  /**
   * Gets the user.
   *
   * @return the user
   */
  public User getUser() {
    return user;
  }
  
  /**
   * Sets the user.
   *
   * @param user the new user
   */
  public void setUser(final User user) {
    this.user = user;
  }

  /**
   * Gets the roles.
   *
   * @return the roles
   */
  public List<EcsrRole> getRoles() {
    return roles;
  }

  /**
   * Sets the roles.
   *
   * @param roles the new roles
   */
  public void setRoles(final List<EcsrRole> roles) {
    this.roles = roles;
  }

  /**
   * Adds the role.
   *
   * @param role the role
   */
  public void addRole(final EcsrRole role) {
    if (roles == null) {
      setRoles(new ArrayList<EcsrRole>());
    }
    
    roles.add(role);
  }

  /**
   * Removes the role.
   *
   * @param role the role
   */
  public void removeRole(final EcsrRole role) {
    this.roles.remove(role);
  }
}
