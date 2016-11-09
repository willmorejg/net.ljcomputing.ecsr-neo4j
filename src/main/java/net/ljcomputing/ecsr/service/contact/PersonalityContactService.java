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

package net.ljcomputing.ecsr.service.contact;

import net.ljcomputing.ecsr.domain.contact.ContactInformation;
import net.ljcomputing.ecsr.domain.contact.PersonalityContact;
import net.ljcomputing.ecsr.domain.person.Personality;
import net.ljcomputing.ecsr.service.DomainService;

/**
 * Interface shared by all personality contact services.
 * 
 * @author James G. Willmore
 *
 */
public interface PersonalityContactService
    <E extends PersonalityContact<T, S>, T extends Personality, S extends ContactInformation>
    extends DomainService<E> {
  
  /**
   * Adds the contact.
   *
   * @param personality the personality
   * @param contact the contact
   * @return the e
   */
  E addContact(T personality, S contact);
  
  /**
   * Removes the contact.
   *
   * @param personality the personality
   * @param contact the contact
   */
  void removeContact(T personality, S contact);
  
  /**
   * Removes the contact.
   *
   * @param personalityUuid the personality uuid
   * @param contactUuid the contact uuid
   */
  void removeContact(String personalityUuid, String contactUuid);
}
