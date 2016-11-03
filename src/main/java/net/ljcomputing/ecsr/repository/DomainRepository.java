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

package net.ljcomputing.ecsr.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import net.ljcomputing.ecsr.domain.Domain;

/**
 * Domain repository.
 * 
 * @author James G. Willmore
 *
 */
public interface DomainRepository<T extends Domain> extends GraphRepository<T> {

  /**
   * Find by uuid.
   *
   * @param uuid the uuid
   * @return the t
   */
  @Query("MATCH (n) WHERE n.uuid = {0} RETURN n")
  T findByUuid(final String uuid);
}
