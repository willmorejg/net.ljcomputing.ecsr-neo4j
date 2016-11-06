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

import net.ljcomputing.ecsr.domain.person.Membership;
import net.ljcomputing.ecsr.domain.person.Personality;
import net.ljcomputing.ecsr.repository.DomainRepository;
import net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl;
import net.ljcomputing.ecsr.service.person.MembershipService;

/**
 * Abstract implementation of a membership service.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractMembershipServiceImpl
    <T extends Personality, S extends Personality, 
    M extends Membership<T, S>, R extends DomainRepository<M>>
    extends AbstractDomainServiceImpl<M, R> implements MembershipService<T, S, M> {

  /**
   * @see net.ljcomputing.ecsr.service.person.MembershipService
   *    #addMember(
   *        net.ljcomputing.ecsr.domain.person.Personality, 
   *        net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public abstract void addMember(T member, S membership);

  /**
   * @see net.ljcomputing.ecsr.service.person.MembershipService
   *    #memberOf(net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public abstract List<S> memberOf(T member);

  /**
   * @see net.ljcomputing.ecsr.service.person.MembershipService
   *    #memberOf(java.lang.String)
   */
  @Override
  public abstract List<S> memberOf(String memberUuid);

  /**
   * @see net.ljcomputing.ecsr.service.person.MembershipService
   *    #membershipRoster(net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public abstract List<T> membershipRoster(S membership);

  /**
   * @see net.ljcomputing.ecsr.service.person.MembershipService
   *    #membershipRoster(java.lang.String)
   */
  @Override
  public abstract List<T> membershipRoster(String membershipName);

  /**
   * @see net.ljcomputing.ecsr.service.person.MembershipService
   *    #removeMember(
   *        net.ljcomputing.ecsr.domain.person.Personality, 
   *        net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public abstract void removeMember(T member, S membership);

  /**
   * @see net.ljcomputing.ecsr.service.person.MembershipService
   *    #removeMember(java.lang.String, java.lang.String)
   */
  @Override
  public abstract void removeMember(String memberUuid, String membershipUuid);
}
