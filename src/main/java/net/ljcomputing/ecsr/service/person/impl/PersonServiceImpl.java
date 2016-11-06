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
public class PersonServiceImpl extends PersonalityServiceImpl<Person, PersonRepository> implements PersonService {

  /**
   * @see net.ljcomputing.ecsr.service.person.PersonService#locateByName(java.lang.String, java.lang.String)
   */
  @Override
  public List<Person> locateByName(String firstName, String lastName) {
    return (List<Person>) repository.locateByFirstLast(firstName, lastName);
  }
}
