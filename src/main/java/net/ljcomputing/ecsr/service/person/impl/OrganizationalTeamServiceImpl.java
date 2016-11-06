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

import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.domain.person.OrganizationalTeam;
import net.ljcomputing.ecsr.domain.person.Team;
import net.ljcomputing.ecsr.repository.person.OrganizationalTeamRepository;
import net.ljcomputing.ecsr.service.person.OrganizationalTeamService;

/**
 * Organizational member service implementation.
 * 
 * @author James G. Willmore
 *
 */
@Service
@Transactional
public class OrganizationalTeamServiceImpl
    extends MembershipServiceImpl<Team, Organization, OrganizationalTeam, OrganizationalTeamRepository>
    implements OrganizationalTeamService {

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.MembershipServiceImpl
   *    #addMember(
   *        net.ljcomputing.ecsr.domain.person.Personality, 
   *        net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public void addMember(final Team member, final Organization membership) {
    final OrganizationalTeam teamMembership = new OrganizationalTeam();
    teamMembership.setMember(member);
    teamMembership.setMemberOf(membership);
    repository.save(teamMembership);
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.MembershipService
   *    #memberOf(net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public List<Organization> memberOf(final Team member) {
    return memberOf(member.getUuid());
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.MembershipServiceImpl#memberOf(java.lang.String)
   */
  @Override
  public List<Organization> memberOf(final String memberUuid) {
    return (List<Organization>) repository.findByMemberUuid(memberUuid);
  }

  /** (non-Javadoc)
   * @see net.ljcomputing.ecsr.service.person.impl.MembershipServiceImpl
   *    #membershipRoster(net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public List<Team> membershipRoster(final Organization membership) {
    return membershipRoster(membership.getName());
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.MembershipServiceImpl#membershipRoster(java.lang.String)
   */
  @Override
  public List<Team> membershipRoster(final String membershipName) {
    return (List<Team>) repository.findByOrganizationName(membershipName);
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.MembershipServiceImpl
   *    #removeMember(
   *        net.ljcomputing.ecsr.domain.person.Personality, 
   *        net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public void removeMember(final Team member, final Organization membership) {
    removeMember(member.getUuid(), membership.getUuid());
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.MembershipServiceImpl
   *    #removeMember(java.lang.String, java.lang.String)
   */
  @Override
  public void removeMember(final String memberUuid, final String membershipUuid) {
    final OrganizationalTeam memberRelationship = 
        repository.findOrganizationalTeamMembership(memberUuid, membershipUuid);
    
    repository.delete(memberRelationship);
  }
}
