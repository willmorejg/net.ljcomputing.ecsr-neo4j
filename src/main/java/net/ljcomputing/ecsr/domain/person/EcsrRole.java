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

import net.ljcomputing.ecsr.domain.AbstractDomain;
import net.ljcomputing.ecsr.domain.Domain;

/**
 * ECSR application role.
 * 
 * @author James G. Willmore
 *
 */
public class EcsrRole extends AbstractDomain implements Domain {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -576797329045015166L;
  
  /** The role name. */
  private String roleName;
  
  /**
   * Gets the role name.
   *
   * @return the role name
   */
  public String getRoleName() {
    return roleName;
  }
  
  /**
   * Sets the role name.
   *
   * @param roleName the new role name
   */
  public void setRoleName(final String roleName) {
    this.roleName = roleName;
  }
}
