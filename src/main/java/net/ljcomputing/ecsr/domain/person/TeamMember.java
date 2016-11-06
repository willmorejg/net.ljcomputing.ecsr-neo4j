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

import org.neo4j.ogm.annotation.RelationshipEntity;

/**
 * Team member domain.
 * 
 * @author James G. Willmore
 *
 */
@RelationshipEntity(type = "TEAM_MEMBER")
public class TeamMember extends AbstractMembership<Person, Team>
    implements Membership<Person, Team> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -7898874532807616879L;
}
