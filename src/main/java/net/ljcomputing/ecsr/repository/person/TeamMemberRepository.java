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

package net.ljcomputing.ecsr.repository.person;

import org.springframework.data.neo4j.annotation.Query;

import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.domain.person.Team;
import net.ljcomputing.ecsr.domain.person.TeamMember;
import net.ljcomputing.ecsr.repository.DomainRepository;

/**
 * Team member repository.
 * 
 * @author James G. Willmore
 *
 */
public interface TeamMemberRepository extends DomainRepository<TeamMember> {

  /**
   * Find by member uuid.
   *
   * @param uuid the uuid
   * @return the iterable
   */
  @Query("START n=node(*) " + "MATCH (t:Team)-[:TEAM_MEMBER]-(p:Person) " + "WHERE p.uuid={0} "
      + "RETURN DISTINCT t")
  Iterable<Team> findByMemberUuid(final String uuid);

  /**
   * Find by team name.
   *
   * @param name the name
   * @return the iterable
   */
  @Query("START n=node(*) " + "MATCH (p:Person)-[:TEAM_MEMBER]-(t:Team) " + "WHERE t.name={0} "
      + "RETURN DISTINCT p")
  Iterable<Person> findByTeamName(final String name);
}
