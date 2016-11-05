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
import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.domain.person.OrganizationalMember;
import net.ljcomputing.ecsr.domain.person.OrganizationalTeam;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.domain.person.Team;
import net.ljcomputing.ecsr.domain.person.TeamMember;
import net.ljcomputing.ecsr.repository.person.OrganizationalMemberRepository;
import net.ljcomputing.ecsr.repository.person.OrganizationalTeamRepository;
import net.ljcomputing.ecsr.repository.person.TeamMemberRepository;
import net.ljcomputing.ecsr.service.PersonalityService;

/**
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EcsrNeo4JConfiguration.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonCreationUnitTests {

  /** The Constant LOGGER. */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(PersonCreationUnitTests.class);
  
  /** The person service. */
  @Autowired
  private transient PersonalityService<Person> personService;
  
  /** The team service. */
  @Autowired
  private transient PersonalityService<Team> teamService;

  /** The organization service. */
  @Autowired
  private transient PersonalityService<Organization> organizationService;

  /** The team member repository. */
  @Autowired
  private transient TeamMemberRepository teamMemberRepos;
  
  /** The org member repos. */
  @Autowired
  private transient OrganizationalMemberRepository orgMemberRepos;
  
  /** The org team repos. */
  @Autowired
  private transient OrganizationalTeamRepository orgTeamRepos;

  /**
   * Test 01.
   */
  @Test
  @Rollback(false)
  public void test01() {
    final String[] names = { "Jim", "Willmore", "Alice", "Conner", "Bob", "Smith", "Charlie",
        "Pace" };

    for (int n = 0; n < names.length; n++) {
      final Person person = new Person(); //NOPMD

      person.setFirstName(names[n]); //NOPMD

      n++;

      person.setLastName(names[n]); //NOPMD

      personService.save(person);
    }
  }

  /**
   * Test 02.
   */
  @Test
  @Rollback(false)
  public void test02() {
    final String[] names = { "A-Team", "B-Team", "C-Team" };

    for (final String name : names) {
      final Team team = new Team(); //NOPMD

      team.setName(name);

      teamService.save(team);
    }
  }

  /**
   * Test 03.
   */
  @Test
  @Rollback(false)
  public void test03() {
    final Iterable<Person> people = personService.findAll();
    final Iterable<Team> teams = teamService.findAll(); //NOPMD
    
    for (final Person person : people) {
      for (final Team team : teams) {
        final TeamMember teamMember = new TeamMember(); //NOPMD

        teamMember.setMember(person);
        teamMember.setMemberOf(team);

        teamMemberRepos.save(teamMember);
      }
    }
  }

  /**
   * Test 04.
   */
  @Test
  @Rollback(false)
  public void test04() {
    final String[] names = { "AlphaCorp", "BravoCorp", "CharlieCorp" };

    for (final String name : names) {
      final Organization org = new Organization(); //NOPMD

      org.setName(name);

      organizationService.save(org);
    }
  }

  /**
   * Test 05.
   */
  @Test
  @Rollback(false)
  public void test05() {
    final Iterable<Person> people = personService.findAll();
    final Iterable<Organization> orgs = organizationService.findAll(); //NOPMD
    
    for (final Person person : people) {
      for (final Organization org : orgs) {
        final OrganizationalMember orgMember = new OrganizationalMember(); //NOPMD

        orgMember.setMember(person);
        orgMember.setMemberOf(org);

        orgMemberRepos.save(orgMember);
      }
    }
  }

  /**
   * Test 06.
   */
  @Test
  @Rollback(false)
  public void test06() {
    final Iterable<Team> teams = teamService.findAll();
    final Iterable<Organization> orgs = organizationService.findAll(); //NOPMD
    
    for (final Team team : teams) {
      for (final Organization org : orgs) {
        final OrganizationalTeam orgTeamMember = new OrganizationalTeam(); //NOPMD

        orgTeamMember.setMember(team);
        orgTeamMember.setMemberOf(org);

        orgTeamRepos.save(orgTeamMember);
      }
    }
  }
}
