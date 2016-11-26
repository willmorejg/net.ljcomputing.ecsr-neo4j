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

import net.ljcomputing.ecsr.domain.person.EcsrRole;
import net.ljcomputing.ecsr.repository.DomainRepository;

/**
 * ECSR role repository.
 * 
 * @author James G. Willmore
 *
 */
public interface EcsrRoleRepository extends DomainRepository<EcsrRole> {
  
  /**
   * Find by username.
   *
   * @param username the username
   * @return the iterable
   */
  @Query("MATCH (e:EcsrRole)-[]-(r:UserRoles)-[]-(u:User) WHERE u.username={0} RETURN e")
  Iterable<EcsrRole> findByUsername(String username);
}
