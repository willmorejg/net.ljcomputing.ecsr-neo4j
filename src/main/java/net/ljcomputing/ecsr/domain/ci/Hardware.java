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

package net.ljcomputing.ecsr.domain.ci;

import net.ljcomputing.ecsr.domain.Domain;
import net.ljcomputing.ecsr.domain.contact.MailContact;

/**
 * Hardware domain.
 * 
 * @author James G. Willmore
 *
 */
public class Hardware extends AbstractConfigurationItem implements Domain, ConfigurationItem {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -8983084987034620538L;
  
  /** The location (physical location of the item). */
  private MailContact location;

  /**
   * Gets the location.
   *
   * @return the location
   */
  public MailContact getLocation() {
    return location;
  }

  /**
   * Sets the location.
   *
   * @param location the new location
   */
  public void setLocation(final MailContact location) {
    this.location = location;
  }
}
