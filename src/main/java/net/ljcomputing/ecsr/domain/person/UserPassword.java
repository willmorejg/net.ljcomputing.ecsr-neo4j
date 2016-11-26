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
 * ECSR user password.
 * 
 * @author James G. Willmore
 *
 */
public class UserPassword extends AbstractDomain implements Domain {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -6269260883748769879L;

  /** The password. */
  private String password;
  
  /** The user. */
  private User user;
  
  /**
   * Gets the password.
   *
   * @return the password
   */
  public String getPassword() {
    return password;
  }
  
  /**
   * Sets the password.
   *
   * @param password the new password
   */
  public void setPassword(final String password) {
    this.password = password;
  }
  
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
}
