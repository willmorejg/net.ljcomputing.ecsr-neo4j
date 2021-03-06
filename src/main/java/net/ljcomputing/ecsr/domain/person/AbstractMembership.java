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

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.StartNode;

import net.ljcomputing.ecsr.domain.AbstractDomain;
import net.ljcomputing.ecsr.domain.Domain;

/**
 * Abstract implementation of a membership relationship.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractMembership<T extends Domain, S extends Domain>
    extends AbstractDomain {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7599973162437955599L;

  /** The member. */
  @StartNode
  private T member;

  /** The member of. */
  @EndNode
  private S memberOf;

  /**
   * Gets the member.
   *
   * @return the member
   */
  public T getMember() {
    return member;
  }

  /**
   * Sets the member.
   *
   * @param member the new member
   */
  public void setMember(final T member) {
    this.member = member;
  }

  /**
   * Gets the member of.
   *
   * @return the member of
   */
  public S getMemberOf() {
    return memberOf;
  }

  /**
   * Sets the member of.
   *
   * @param memberOf the new member of
   */
  public void setMemberOf(final S memberOf) {
    this.memberOf = memberOf;
  }
}
