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

package net.ljcomputing.ecsr.domain.contact;

import net.ljcomputing.ecsr.domain.Domain;

/**
 * Email contact domain.
 * 
 * @author James G. Willmore
 *
 */
public class EmailContact extends AbstractContactInformation implements ContactInformation, Domain {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 728449578016294694L;

  /** The local part. */
  private String localPart;

  /** The domain. */
  private String domain;

  /**
   * Gets the local part.
   *
   * @return the local part
   */
  public String getLocalPart() {
    return localPart;
  }

  /**
   * Sets the local part.
   *
   * @param localPart the new local part
   */
  public void setLocalPart(final String localPart) {
    this.localPart = localPart;
  }

  /**
   * Gets the domain.
   *
   * @return the domain
   */
  public String getDomain() {
    return domain;
  }

  /**
   * Sets the domain.
   *
   * @param domain the new domain
   */
  public void setDomain(final String domain) {
    this.domain = domain;
  }
}
