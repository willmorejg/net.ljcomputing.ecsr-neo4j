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
import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.domain.person.Team;
import net.ljcomputing.ecsr.repository.contact.EmailRepository;
import net.ljcomputing.ecsr.service.contact.OrganizationEmailService;
import net.ljcomputing.ecsr.service.contact.PersonEmailService;
import net.ljcomputing.ecsr.service.contact.TeamEmailService;

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

  /** The person email contact service. */
  @Autowired
  private transient PersonEmailService personEmailSrv;

  /** The team email contact service. */
  @Autowired
  private transient TeamEmailService teamEmailSrv;

  /** The organization email contact service. */
  @Autowired
  private transient OrganizationEmailService orgEmailSrv;

  /**
   * Test 01.
   */
  @Test
  @Rollback(false)
  public void test01() {
    final EmailContact email = new EmailContact();

    email.setLocalPart("localPart");
    email.setDomain("localhost");
    email.setAlias("localhost email");

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
      final Iterable<EmailContact> emails = 
          emailRepos.findByAlias("localhost email");

      for (final EmailContact email : emails) {
        personEmailSrv.addContact(person, email);
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
        teamEmailSrv.addContact(team, email);
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
        orgEmailSrv.addContact(org, email);
      }
    }
  }
}
