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
import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.domain.person.OrganizationalMember;
import net.ljcomputing.ecsr.domain.person.OrganizationalTeam;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.domain.person.Team;
import net.ljcomputing.ecsr.domain.person.TeamMember;
import net.ljcomputing.ecsr.service.person.OrganizationService;
import net.ljcomputing.ecsr.service.person.OrganizationalMemberService;
import net.ljcomputing.ecsr.service.person.OrganizationalTeamService;
import net.ljcomputing.ecsr.service.person.PersonService;
import net.ljcomputing.ecsr.service.person.TeamMemberService;
import net.ljcomputing.ecsr.service.person.TeamService;

/**
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EcsrNeo4JConfiguration.class)
@WebAppConfiguration
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonDeletionUnitTests {

  /** The Constant LOGGER. */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(PersonDeletionUnitTests.class);

  /** The person repository. */
  @Autowired
  private transient PersonService personService;

  /** The team repository. */
  @Autowired
  private transient TeamService teamService;

  /** The team member repository. */
  @Autowired
  private transient TeamMemberService teamMemberService;
  
  /** The organization repos. */
  @Autowired
  private transient OrganizationService orgService;
  
  /** The org member repos. */
  @Autowired
  private transient OrganizationalMemberService orgMemberService;
  
  /** The org team repos. */
  @Autowired
  private transient OrganizationalTeamService orgTeamService;

  /**
   * Test 01.
   */
  @Test
  @Rollback(false)
  public void test01() {
    final Iterable<OrganizationalTeam> orgTeams = orgTeamService.findAll();
    
    for (final OrganizationalTeam orgTeam : orgTeams) {
      orgTeam.delete();
      orgTeamService.delete(orgTeam);
    }
    
    final Iterable<OrganizationalMember> orgMembers = orgMemberService.findAll();
    
    for (final OrganizationalMember orgMember : orgMembers) {
      orgMember.delete();
      orgMemberService.delete(orgMember);
    }
    
    final Iterable<Organization> orgs = orgService.findAll();
    
    for (final Organization org : orgs) {
      org.delete();
      orgService.delete(org);
    }
    
    final Iterable<TeamMember> teamMembers = teamMemberService.findAll();
    
    for (final TeamMember teamMember : teamMembers) {
      teamMember.delete();
      teamMemberService.delete(teamMember);
    }
    
    final Iterable<Team> teams = teamService.findAll();
    
    for (final Team team : teams) {
      team.delete();
      teamService.delete(team);
    }
    
    final Iterable<Person> people = personService.findAll();
    
    for (final Person person : people) {
      person.delete();
      personService.delete(person);
    }
  }
}
