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

/**
 * Person repository.
 * 
 * @author James G. Willmore
 *
 */
public interface PersonRepository extends PersonalityRepository<Person> {

  /**
   * Find by first name and last name - 
   * case insensitive and contains the given characters.
   *
   * @param firstName the first name
   * @param lastName the last name
   * @return the person
   */
  @Query("MATCH (p:Person) " + "WHERE LOWER(p.firstName) CONTAINS LOWER({0}) "
      + "AND LOWER(p.lastName) CONTAINS LOWER({1}) " + "RETURN p")
  Iterable<Person> locateByFirstLast(String firstName, String lastName);

  /**
   * Locate by username.
   *
   * @param username the username
   * @return the person
   */
  @Query("MATCH (n:Person)-[]-(u:User) WHERE u.username={0} RETURN DISTINCT n")
  Person locateByUsername(String username);
}
