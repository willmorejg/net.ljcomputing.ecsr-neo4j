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

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import net.ljcomputing.ecsr.domain.AbstractDomain;
import net.ljcomputing.ecsr.domain.Domain;
import net.ljcomputing.ecsr.domain.person.Personality;

/**
 * Implementation of a personality contact 
 * (ex. an email address associated with a person).
 *
 * @author James G. Willmore
 * @param <T> the personality (ex. the person owning the contact information)
 * @param <S> the contact (ex. a person's email address)
 */
@RelationshipEntity(type = "CONTACT")
public class PersonalityContactImpl<T extends Personality, S extends ContactInformation>
    extends AbstractDomain implements Domain, PersonalityContact<T, S> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 820407079306855842L;

  /** The personality. */
  @StartNode
  private T personality;

  /** The contact information. */
  @EndNode
  private S contact;

  /**
   * @see net.ljcomputing.ecsr.domain.contact.PersonalityContact#getPersonality()
   */
  @Override
  public T getPersonality() {
    return personality;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.contact.PersonalityContact
   *    #setPersonality(net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public void setPersonality(final T personality) {
    this.personality = personality;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.contact.PersonalityContact#getContact()
   */
  @Override
  public S getContact() {
    return contact;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.contact.PersonalityContact
   *    #setContact(net.ljcomputing.ecsr.domain.contact.ContactInformation)
   */
  @Override
  public void setContact(final S contact) {
    this.contact = contact;
  }
}
