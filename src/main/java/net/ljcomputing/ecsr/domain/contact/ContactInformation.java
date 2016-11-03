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
import net.ljcomputing.ecsr.domain.OrderType;

/**
 * Interface shared by all types of contact information.
 * 
 * @author James G. Willmore
 *
 */
public interface ContactInformation extends Domain {

  /**
   * Gets the alias.
   *
   * @return the alias
   */
  String getAlias();

  /**
   * Sets the alias.
   *
   * @param alias the new alias
   */
  void setAlias(String alias);

  /**
   * Gets the comments.
   *
   * @return the comments
   */
  String getComments();

  /**
   * Sets the comments.
   *
   * @param comments the new comments
   */
  void setComments(String comments);

  /**
   * Gets the contact ownership.
   *
   * @return the contact ownership
   */
  ContactOwnership getContactOwnership();

  /**
   * Sets the contact ownership.
   *
   * @param contactOwnership the new contact ownership
   */
  void setContactOwnership(ContactOwnership contactOwnership);

  /**
   * Gets the contact order type.
   *
   * @return the contact order type
   */
  OrderType getContactOrderType();

  /**
   * Sets the contact order type.
   *
   * @param contactOrderType the new contact order type
   */
  void setContactOrderType(OrderType contactOrderType);
}
