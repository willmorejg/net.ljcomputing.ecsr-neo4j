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

package net.ljcomputing.ecsr.service.person;

import java.util.List;

import net.ljcomputing.ecsr.domain.person.Membership;
import net.ljcomputing.ecsr.domain.person.Personality;
import net.ljcomputing.ecsr.repository.DomainRepository;
import net.ljcomputing.ecsr.service.DomainService;

/**
 * Interface shared by all membership services.
 * 
 * @author James G. Willmore
 *
 */
public interface MembershipService<T extends Personality, S extends Personality, 
    M extends Membership<T, S>, R extends DomainRepository<M>>
    extends DomainService<M, R> {

  /**
   * Adds the member.
   *
   * @param member the member
   * @param membership the membership
   */
  void addMember(T member, S membership);

  /**
   * List membership of a member.
   *
   * @param member the member
   * @return the list
   */
  List<S> memberOf(T member);

  /**
   * List membership of a member.
   *
   * @param memberUuid the member uuid
   * @return the list
   */
  List<S> memberOf(String memberUuid);

  /**
   * Membership roster of the given membership.
   *
   * @param membership the membership
   * @return the list
   */
  List<T> membershipRoster(S membership);

  /**
   * Membership roster of the given membership.
   *
   * @param membershipName the membership name
   * @return the list
   */
  List<T> membershipRoster(String membershipName);

  /**
   * Removes the member.
   *
   * @param member the member
   * @param membership the membership
   */
  void removeMember(T member, S membership);

  /**
   * Removes the member.
   *
   * @param memberUuid the member uuid
   * @param membershipUuid the membership uuid
   */
  void removeMember(String memberUuid, String membershipUuid);
}
