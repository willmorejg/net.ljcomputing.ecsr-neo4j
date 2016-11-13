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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.ecsr.domain.contact.PersonalityContactImpl;
import net.ljcomputing.ecsr.domain.contact.PhoneContact;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.repository.contact.PersonalityContactRepository;
import net.ljcomputing.ecsr.service.contact.PersonPhoneService;

/**
 * @author James G. Willmore
 *
 */
@Service
@Transactional
public class PersonPhoneServiceImpl extends
    AbstractPersonalityContactServiceImpl
    <PersonalityContactImpl<Person, PhoneContact>, Person, 
    PhoneContact,
    PersonalityContactRepository<PersonalityContactImpl<Person, PhoneContact>>>
    implements PersonPhoneService {

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl
   *    #setRepository(net.ljcomputing.ecsr.repository.DomainRepository)
   */
  @Autowired
  @Override
  public void setRepository(
      final PersonalityContactRepository<PersonalityContactImpl<Person, PhoneContact>> repository) {
    this.repository = repository;
  }
}