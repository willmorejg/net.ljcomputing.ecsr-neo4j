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

package net.ljcomputing.ecsr.service.contact.impl;

import net.ljcomputing.ecsr.domain.contact.ContactInformation;
import net.ljcomputing.ecsr.domain.contact.PersonalityContact;
import net.ljcomputing.ecsr.domain.contact.PersonalityContactImpl;
import net.ljcomputing.ecsr.domain.person.Personality;
import net.ljcomputing.ecsr.repository.contact.PersonalityContactRepository;
import net.ljcomputing.ecsr.service.contact.PersonalityContactService;
import net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl;

/**
 * Implementation of a personality contact services.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractPersonalityContactServiceImpl
    <E extends PersonalityContact<T, S>, T extends Personality, 
    S extends ContactInformation, R extends PersonalityContactRepository<E>>
    extends AbstractDomainServiceImpl<E, R> implements PersonalityContactService<E, T, S, R> {

  /**
   * @see net.ljcomputing.ecsr.service.contact.PersonalityContactService
   *    #addContact(
   *        net.ljcomputing.ecsr.domain.person.Personality, 
   *        net.ljcomputing.ecsr.domain.contact.ContactInformation)
   */
  @Override
  public E addContact(final T personality, final S contact) {
    @SuppressWarnings("unchecked")
    final E relationship = (E) new PersonalityContactImpl<T, S>();
    relationship.setPersonality(personality);
    relationship.setContact(contact);
    return getRepository().save(relationship); //NOPMD
  }

  /**
   * @see net.ljcomputing.ecsr.service.contact.PersonalityContactService
   *    #removeContact(
   *        net.ljcomputing.ecsr.domain.person.Personality, 
   *        net.ljcomputing.ecsr.domain.contact.ContactInformation)
   */
  @Override
  public void removeContact(final T personality, final S contact) {
    removeContact(personality.getUuid(), contact.getUuid());
  }

  /**
   * @see net.ljcomputing.ecsr.service.contact.PersonalityContactService
   *    #removeContact(java.lang.String, java.lang.String)
   */
  @Override
  public void removeContact(final String personalityUuid, final String contactUuid) {
    final E relationship = ((PersonalityContactRepository<E>) getRepository()) //NOPMD
        .findPersonalityContact(personalityUuid, contactUuid);
    getRepository().delete(relationship); //NOPMD
  }
}
