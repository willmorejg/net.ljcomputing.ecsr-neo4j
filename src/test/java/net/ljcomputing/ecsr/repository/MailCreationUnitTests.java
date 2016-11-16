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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.ecsr.configuration.EcsrNeo4JConfiguration;
import net.ljcomputing.ecsr.domain.contact.MailContact;
import net.ljcomputing.ecsr.domain.contact.PersonalityContactImpl;
import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.domain.person.Team;
import net.ljcomputing.ecsr.service.contact.MailService;
import net.ljcomputing.ecsr.service.contact.OrganizationMailService;
import net.ljcomputing.ecsr.service.contact.PersonMailService;
import net.ljcomputing.ecsr.service.contact.TeamMailService;

/**
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EcsrNeo4JConfiguration.class)
@WebAppConfiguration
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MailCreationUnitTests extends AbstractContactCreationUnitTests {

  /** The Constant LOGGER. */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(MailCreationUnitTests.class);

  /** The mail service. */
  @Autowired
  private transient MailService mailSrv;

  /** The person mail service. */
  @Autowired
  private transient PersonMailService personMailService;

  /** The team mail service. */
  @Autowired
  private transient TeamMailService teamMailService;

  /** The org mail service. */
  @Autowired
  private transient OrganizationMailService orgMailService;

  /**
   * Test 01.
   */
  @Test
  @Rollback(false)
  public void test01() {
    final MailContact mail = new MailContact();
    
    mail.setAddress1("123 Salem Road");
    mail.setCity("York");
    mail.setState("PA");
    mail.setZipCode("17401");
    
    mailSrv.save(mail);
  }

  /**
   * Test 02.
   */
  @Test
  @Rollback(false)
  public void test02() {
    final Iterable<Person> people = personRepos.findAll();

    for (final Person person : people) {
      final Iterable<MailContact> mails = mailSrv.findAll();

      for (final MailContact mail : mails) {
        final PersonalityContactImpl<Person, MailContact> personContact = 
            new PersonalityContactImpl<Person, MailContact>(); //NOPMD
        personContact.setPersonality(person);
        personContact.setContact(mail);

        personMailService.save(personContact);
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
      final Iterable<MailContact> mails = mailSrv.findAll();

      for (final MailContact mail : mails) {
        final PersonalityContactImpl<Team, MailContact> personContact = 
            new PersonalityContactImpl<Team, MailContact>(); //NOPMD
        personContact.setPersonality(team);
        personContact.setContact(mail);

        teamMailService.save(personContact);
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
      final Iterable<MailContact> mails = mailSrv.findAll();

      for (final MailContact mail : mails) {
        final PersonalityContactImpl<Organization, MailContact> personContact = 
            new PersonalityContactImpl<Organization, MailContact>(); //NOPMD
        personContact.setPersonality(org);
        personContact.setContact(mail);

        orgMailService.save(personContact);
      }
    }
  }
}
