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
import net.ljcomputing.ecsr.domain.contact.EmailContact;
import net.ljcomputing.ecsr.domain.contact.PersonalityContactImpl;
import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.domain.person.Team;
import net.ljcomputing.ecsr.repository.contact.EmailRepository;
import net.ljcomputing.ecsr.repository.contact.PersonalityContactRepository;

/**
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EcsrNeo4JConfiguration.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EmailCreationUnitTests extends AbstractContactCreationUnitTests {

  /** The Constant LOGGER. */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailCreationUnitTests.class);

  /** The email repos. */
  @Autowired
  private transient EmailRepository emailRepos;

  /** The person email repos. */
  @Autowired
  private 
      transient 
      PersonalityContactRepository
      <PersonalityContactImpl<Person, EmailContact>, Person, EmailContact> personEmailRepos;

  /** The team email repos. */
  @Autowired
  private 
      transient 
      PersonalityContactRepository
      <PersonalityContactImpl<Team, EmailContact>, Team, EmailContact> teamEmailRepos;

  /** The org email repos. */
  @Autowired
  private 
      transient 
      PersonalityContactRepository
      <PersonalityContactImpl<Organization, EmailContact>, Organization, EmailContact> 
      orgEmailRepos;

  /**
   * Test 01.
   */
  @Test
  @Rollback(false)
  public void test01() {
    final EmailContact email = new EmailContact();

    email.setLocalPart("localPart");
    email.setDomain("localhost");

    emailRepos.save(email);
  }

  /**
   * Test 02.
   */
  @Test
  @Rollback(false)
  public void test02() {
    final Iterable<Person> people = personRepos.findAll();

    for (final Person person : people) {
      final Iterable<EmailContact> emails = emailRepos.findAll();

      for (final EmailContact email : emails) {
        final PersonalityContactImpl<Person, EmailContact> personContact = 
            new PersonalityContactImpl<Person, EmailContact>(); //NOPMD
        personContact.setPersonality(person);
        personContact.setContact(email);

        personEmailRepos.save(personContact);
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
      final Iterable<EmailContact> emails = emailRepos.findAll();

      for (final EmailContact email : emails) {
        final PersonalityContactImpl<Team, EmailContact> personContact = 
            new PersonalityContactImpl<Team, EmailContact>(); //NOPMD
        personContact.setPersonality(team);
        personContact.setContact(email);

        teamEmailRepos.save(personContact);
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
      final Iterable<EmailContact> emails = emailRepos.findAll();

      for (final EmailContact email : emails) {
        final PersonalityContactImpl<Organization, EmailContact> personContact = 
            new PersonalityContactImpl<Organization, EmailContact>(); //NOPMD
        personContact.setPersonality(org);
        personContact.setContact(email);

        orgEmailRepos.save(personContact);
      }
    }
  }
}
