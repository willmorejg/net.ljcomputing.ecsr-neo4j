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

package net.ljcomputing.ecsr.domain.person;

import net.ljcomputing.ecsr.domain.Domain;

/**
 * The Interface Membership.
 *
 * @author James G. Willmore
 * @param <T> the member (a Person or Team)
 * @param <S> the member of (an Organization or Team)
 */
public interface Membership<T extends Personality, S extends Personality> extends Domain {

  /**
   * Gets the member.
   *
   * @return the member
   */
  T getMember();

  /**
   * Sets the member.
   *
   * @param member the new member
   */
  void setMember(T member);

  /**
   * Gets the member of.
   *
   * @return the member of
   */
  S getMemberOf();

  /**
   * Sets the member of.
   *
   * @param memberOf the new member of
   */
  void setMemberOf(S memberOf);
}
