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
import net.ljcomputing.ecsr.domain.person.Team;
import net.ljcomputing.ecsr.domain.person.TeamMember;
import net.ljcomputing.ecsr.repository.person.TeamMemberRepository;
import net.ljcomputing.ecsr.service.person.TeamMemberService;

/**
 * Team member service implementation.
 * 
 * @author James G. Willmore
 *
 */
@Service
@Transactional
public class TeamMemberServiceImpl
    extends AbstractMembershipServiceImpl<Person, Team, TeamMember, TeamMemberRepository>
    implements TeamMemberService {

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl
   *    #setRepository(net.ljcomputing.ecsr.repository.DomainRepository)
   */
  @Autowired
  @Override
  public void setRepository(final TeamMemberRepository repository) {
    this.repository = repository;
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.AbstractMembershipServiceImpl
   *    #addMember(
   *        net.ljcomputing.ecsr.domain.person.Personality, 
   *        net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public void addMember(final Person member, final Team membership) {
    final TeamMember teamMembership = new TeamMember();
    teamMembership.setMember(member);
    teamMembership.setMemberOf(membership);
    getRepository().save(teamMembership); //NOPMD
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.AbstractMembershipServiceImpl
   *    #memberOf(java.lang.String)
   */
  @Override
  public List<Team> memberOf(final String memberUuid) {
    return (List<Team>) getRepository().findByMemberUuid(memberUuid); //NOPMD
  }

  /** (non-Javadoc)
   * @see net.ljcomputing.ecsr.service.person.impl.AbstractMembershipServiceImpl
   *    #membershipRoster(net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public List<Person> membershipRoster(final Team membership) {
    return membershipRoster(membership.getName());
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.AbstractMembershipServiceImpl
   *    #membershipRoster(java.lang.String)
   */
  @Override
  public List<Person> membershipRoster(final String membershipName) {
    return (List<Person>) getRepository().findByTeamName(membershipName); //NOPMD
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.AbstractMembershipServiceImpl
   *    #removeMember(java.lang.String, java.lang.String)
   */
  @Override
  public void removeMember(final String memberUuid, final String membershipUuid) {
    final TeamMember relationship = 
        getRepository().findTeamMembership(memberUuid, membershipUuid); //NOPMD
    getRepository().delete(relationship); //NOPMD
  }
}
