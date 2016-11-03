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
import net.ljcomputing.ecsr.repository.person.OrganizationRepository;
import net.ljcomputing.ecsr.repository.person.OrganizationalMemberRepository;
import net.ljcomputing.ecsr.repository.person.OrganizationalTeamRepository;
import net.ljcomputing.ecsr.repository.person.PersonRepository;
import net.ljcomputing.ecsr.repository.person.TeamMemberRepository;
import net.ljcomputing.ecsr.repository.person.TeamRepository;

/**
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EcsrNeo4JConfiguration.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PersonDeletionUnitTests {

  /** The Constant LOGGER. */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(PersonDeletionUnitTests.class);

  /** The person repository. */
  @Autowired
  private transient PersonRepository personRepos;

  /** The team repository. */
  @Autowired
  private transient TeamRepository teamRepos;

  /** The team member repository. */
  @Autowired
  private transient TeamMemberRepository teamMemberRepos;
  
  /** The organization repos. */
  @Autowired
  private transient OrganizationRepository organizationRepos;
  
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
    final Iterable<OrganizationalTeam> orgTeams = orgTeamRepos.findAll();
    
    for (final OrganizationalTeam orgTeam : orgTeams) {
      orgTeam.delete();
      orgTeamRepos.delete(orgTeam);
    }
    
    final Iterable<OrganizationalMember> orgMembers = orgMemberRepos.findAll();
    
    for (final OrganizationalMember orgMember : orgMembers) {
      orgMember.delete();
      orgMemberRepos.delete(orgMember);
    }
    
    final Iterable<Organization> orgs = organizationRepos.findAll();
    
    for (final Organization org : orgs) {
      org.delete();
      organizationRepos.delete(org);
    }
    
    final Iterable<TeamMember> teamMembers = teamMemberRepos.findAll();
    
    for (final TeamMember teamMember : teamMembers) {
      teamMember.delete();
      teamMemberRepos.delete(teamMember);
    }
    
    final Iterable<Team> teams = teamRepos.findAll();
    
    for (final Team team : teams) {
      team.delete();
      teamRepos.delete(team);
    }
    
    final Iterable<Person> people = personRepos.findAll();
    
    for (final Person person : people) {
      person.delete();
      personRepos.delete(person);
    }
  }
}
