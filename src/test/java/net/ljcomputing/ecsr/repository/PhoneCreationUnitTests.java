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

package net.ljcomputing.ecsr.repository;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.ecsr.configuration.EcsrNeo4JConfiguration;
import net.ljcomputing.ecsr.domain.contact.PersonalityContactImpl;
import net.ljcomputing.ecsr.domain.contact.PhoneContact;
import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.domain.person.Team;
import net.ljcomputing.ecsr.repository.contact.PersonalityContactRepository;
import net.ljcomputing.ecsr.repository.contact.PhoneRepository;

/**
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EcsrNeo4JConfiguration.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PhoneCreationUnitTests extends AbstractContactCreationUnitTests {

  /** The Constant LOGGER. */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(PhoneCreationUnitTests.class);

  /** The phone repos. */
  @Autowired
  private transient PhoneRepository phoneRepos;

  /** The person phone repos. */
  @Autowired
  private 
      transient 
      PersonalityContactRepository
      <PersonalityContactImpl<Person, PhoneContact>> personPhoneRepos;

  /** The team phone repos. */
  @Autowired
  private 
      transient 
      PersonalityContactRepository
      <PersonalityContactImpl<Team, PhoneContact>> teamPhoneRepos;

  /** The org phone repos. */
  @Autowired
  private 
      transient 
      PersonalityContactRepository
      <PersonalityContactImpl<Organization, PhoneContact>> 
      orgPhoneRepos;

  /**
   * Test 01.
   */
  @Test
  @Rollback(false)
  public void test01() {
    final PhoneContact phone = new PhoneContact();
    
    phone.setAreaCode("717");
    phone.setPrefix("555");
    phone.setNumber("5555");
    
    phoneRepos.save(phone);
  }

  /**
   * Test 02.
   */
  @Test
  @Rollback(false)
  public void test02() {
    final Iterable<Person> people = personRepos.findAll();

    for (final Person person : people) {
      final Iterable<PhoneContact> phones = phoneRepos.findAll();

      for (final PhoneContact phone : phones) {
        final PersonalityContactImpl<Person, PhoneContact> personContact = 
            new PersonalityContactImpl<Person, PhoneContact>(); //NOPMD
        personContact.setPersonality(person);
        personContact.setContact(phone);

        personPhoneRepos.save(personContact);
      }
    }
  }

  /**
   * Test 03.
   */
  @Test
  @Rollback(false)
  public void test03() {
    final Iterable<Team> teams = teamRepos.findAll();

    for (final Team team : teams) {
      final Iterable<PhoneContact> phones = phoneRepos.findAll();

      for (final PhoneContact phone : phones) {
        final PersonalityContactImpl<Team, PhoneContact> personContact = 
            new PersonalityContactImpl<Team, PhoneContact>(); //NOPMD
        personContact.setPersonality(team);
        personContact.setContact(phone);

        teamPhoneRepos.save(personContact);
      }
    }
  }

  /**
   * Test 04.
   */
  @Test
  @Rollback(false)
  public void test04() {
    final Iterable<Organization> orgs = organizationRepos.findAll();

    for (final Organization org : orgs) {
      final Iterable<PhoneContact> phones = phoneRepos.findAll();

      for (final PhoneContact phone : phones) {
        final PersonalityContactImpl<Organization, PhoneContact> personContact = 
            new PersonalityContactImpl<Organization, PhoneContact>(); //NOPMD
        personContact.setPersonality(org);
        personContact.setContact(phone);

        orgPhoneRepos.save(personContact);
      }
    }
  }
}
