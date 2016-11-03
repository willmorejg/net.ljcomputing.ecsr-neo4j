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

import org.springframework.beans.factory.annotation.Autowired;

import net.ljcomputing.ecsr.repository.person.OrganizationRepository;
import net.ljcomputing.ecsr.repository.person.PersonRepository;
import net.ljcomputing.ecsr.repository.person.TeamRepository;

/**
 * @author James G. Willmore
 *
 */
public abstract class AbstractContactCreationUnitTests { //NOPMD

  /** The person repository. */
  @Autowired
  protected transient PersonRepository personRepos;

  /** The team repository. */
  @Autowired
  protected transient TeamRepository teamRepos;

  /** The organization repos. */
  @Autowired
  protected transient OrganizationRepository organizationRepos;

  /**
   * Instantiates a new abstract contact creation unit tests.
   */
  protected AbstractContactCreationUnitTests() {
    //noop
  }
}
