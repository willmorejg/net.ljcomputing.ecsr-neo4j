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

import net.ljcomputing.ecsr.domain.AbstractDomain;
import net.ljcomputing.ecsr.domain.Domain;
import net.ljcomputing.ecsr.domain.OrderType;

/**
 * Abstract implementation of contact information entity.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractContactInformation extends AbstractDomain
    implements ContactInformation, Domain {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -5304332033688756080L;

  /** The name. */
  private String alias;

  /** The description. */
  private String comments;

  /** The contact ownership. */
  private ContactOwnership contactOwnership;

  /** The contact order type. */
  private OrderType contactOrderType;

  /**
   * @see net.ljcomputing.ecsr.domain.contact.ContactInformation#getAlias()
   */
  @Override
  public String getAlias() {
    return alias;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.contact.ContactInformation
   *    #setAlias(java.lang.String)
   */
  @Override
  public void setAlias(final String alias) {
    this.alias = alias;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.contact.ContactInformation#getComments()
   */
  @Override
  public String getComments() {
    return comments;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.contact.ContactInformation
   *    #setComments(java.lang.String)
   */
  @Override
  public void setComments(final String comments) {
    this.comments = comments;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.contact
   *    .ContactInformation#getContactOwnership()
   */
  @Override
  public ContactOwnership getContactOwnership() {
    return contactOwnership;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.contact.ContactInformation
   *    #setContactOwnership(net.ljcomputing.ecsr.domain.contact.ContactOwnership)
   */
  @Override
  public void setContactOwnership(final ContactOwnership contactOwnership) {
    this.contactOwnership = contactOwnership;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.contact.ContactInformation
   *    #getContactOrderType()
   */
  @Override
  public OrderType getContactOrderType() {
    return contactOrderType;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.contact.ContactInformation
   *    #setContactOrderType(net.ljcomputing.ecsr.domain.OrderType)
   */
  @Override
  public void setContactOrderType(final OrderType contactOrderType) {
    this.contactOrderType = contactOrderType;
  }
}
