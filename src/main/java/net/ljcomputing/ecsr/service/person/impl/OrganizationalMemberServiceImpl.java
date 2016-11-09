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

import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.domain.person.OrganizationalMember;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.repository.person.OrganizationalMemberRepository;
import net.ljcomputing.ecsr.service.person.OrganizationalMemberService;

/**
 * Organizational member service implementation.
 * 
 * @author James G. Willmore
 *
 */
@Service
@Transactional
public class OrganizationalMemberServiceImpl
    extends AbstractMembershipServiceImpl<Person, Organization, OrganizationalMember>
    implements OrganizationalMemberService {

  /** The organizational member repository. */
  @Autowired
  protected transient OrganizationalMemberRepository orgMemberRepos;

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl
   *    #save(net.ljcomputing.ecsr.domain.Domain)
   */
  @Override
  public OrganizationalMember save(final OrganizationalMember domain) {
    return orgMemberRepos.save(domain);
  }

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl
   *    #delete(java.lang.Long)
   */
  @Override
  public void delete(final Long id) {
    orgMemberRepos.delete(id);
  }

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl#findAll()
   */
  @Override
  public List<OrganizationalMember> findAll() {
    return (List<OrganizationalMember>) orgMemberRepos.findAll();
  }

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl
   *    #findByUuid(java.lang.String)
   */
  @Override
  public OrganizationalMember findByUuid(final String uuid) {
    return orgMemberRepos.findByUuid(uuid);
  }

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl
   *    #deleteByUuid(java.lang.String)
   */
  @Override
  public void deleteByUuid(final String uuid) {
    orgMemberRepos.deleteByUuid(uuid);
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.AbstractMembershipServiceImpl
   *    #addMember(
   *        net.ljcomputing.ecsr.domain.person.Personality, 
   *        net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public void addMember(final Person member, final Organization membership) {
    final OrganizationalMember teamMembership = new OrganizationalMember();
    teamMembership.setMember(member);
    teamMembership.setMemberOf(membership);
    orgMemberRepos.save(teamMembership);
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.MembershipService
   *    #memberOf(net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public List<Organization> memberOf(final Person member) {
    return memberOf(member.getUuid());
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.AbstractMembershipServiceImpl
   *    #memberOf(java.lang.String)
   */
  @Override
  public List<Organization> memberOf(final String memberUuid) {
    return (List<Organization>) orgMemberRepos.findByMemberUuid(memberUuid);
  }

  /** (non-Javadoc)
   * @see net.ljcomputing.ecsr.service.person.impl.AbstractMembershipServiceImpl
   *    #membershipRoster(net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public List<Person> membershipRoster(final Organization membership) {
    return membershipRoster(membership.getName());
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.AbstractMembershipServiceImpl
   *    #membershipRoster(java.lang.String)
   */
  @Override
  public List<Person> membershipRoster(final String membershipName) {
    return (List<Person>) orgMemberRepos.findByOrganizationName(membershipName);
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.AbstractMembershipServiceImpl
   *    #removeMember(
   *        net.ljcomputing.ecsr.domain.person.Personality, 
   *        net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public void removeMember(final Person member, final Organization membership) {
    removeMember(member.getUuid(), membership.getUuid());
  }

  /**
   * @see net.ljcomputing.ecsr.service.person.impl.AbstractMembershipServiceImpl
   *    #removeMember(java.lang.String, java.lang.String)
   */
  @Override
  public void removeMember(final String memberUuid, final String membershipUuid) {
    final OrganizationalMember relationship = orgMemberRepos.findOrganizationMembership(memberUuid,
        membershipUuid);
    orgMemberRepos.delete(relationship);
  }
}
