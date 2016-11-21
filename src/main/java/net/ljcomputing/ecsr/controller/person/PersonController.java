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

package net.ljcomputing.ecsr.controller.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.exception.NoRecordsFoundException;
import net.ljcomputing.ecsr.service.person.PersonService;

/**
 * Person controller.
 * 
 * @author James G. Willmore
 *
 */
@RestController
@RequestMapping("/people")
public class PersonController {

  /** The person service. */
  @Autowired
  private transient PersonService personSrv;

  /**
   * All people.
   *
   * @return the response entity
   * @throws NoRecordsFoundException the no records found exception
   */
  @RequestMapping(method = RequestMethod.GET, value = "/findAll", produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE,
      MediaType.APPLICATION_XML_VALUE })
  public List<Person> allPeople() {
    return personSrv.findAll();
  }

  /**
   * Person by uuid.
   *
   * @param uuid the uuid
   * @return the person
   */
  @RequestMapping(method = RequestMethod.GET, value = "/findByUuid/{uuid}", produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE,
      MediaType.APPLICATION_XML_VALUE })
  public Person personByUuid(@PathVariable("uuid") final String uuid) {
    return personSrv.findByUuid(uuid);
  }

  /**
   * Save.
   *
   * @param person the person
   * @return the person
   */
  @RequestMapping(method = RequestMethod.POST, value = "/save", produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE,
      MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
          MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
  public Person save(@RequestBody final Person person) {
    return personSrv.save(person);
  }

  /**
   * Delete by uuid.
   *
   * @param uuid the uuid
   */
  @RequestMapping(method = RequestMethod.DELETE, value = "/deleteByUuid/{uuid}")
  public void deleteByUuid(@PathVariable("uuid") final String uuid) {
    personSrv.deleteByUuid(uuid);
  }
}
