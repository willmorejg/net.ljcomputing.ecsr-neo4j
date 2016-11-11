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

package net.ljcomputing.ecsr.service.person.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.repository.person.PersonRepository;
import net.ljcomputing.ecsr.service.person.PersonService;

/**
 * Person service implementation.
 * 
 * @author James G. Willmore
 *
 */
@Service
@Transactional
public class PersonServiceImpl extends AbstractPersonalityServiceImpl<Person, PersonRepository>
    implements PersonService {


  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl
   *    #setRepository(net.ljcomputing.ecsr.repository.DomainRepository)
   */
  @Autowired
  @Override
  public void setRepository(final PersonRepository repository) {
    this.repository = repository;
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.PersonService
   *    #locateByName(java.lang.String, java.lang.String)
   */
  @Override
  public List<Person> locateByName(final String firstName, final String lastName) {
    return (List<Person>) getRepository().locateByFirstLast(firstName, lastName); //NOPMD
  }
}
