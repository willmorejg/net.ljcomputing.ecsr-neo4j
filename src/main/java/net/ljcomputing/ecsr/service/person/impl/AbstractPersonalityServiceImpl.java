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

import net.ljcomputing.ecsr.domain.person.Personality;
import net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl;
import net.ljcomputing.ecsr.service.person.PersonalityService;

/**
 * Personality service implementation.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractPersonalityServiceImpl
    <T extends Personality>
    extends AbstractDomainServiceImpl<T> implements PersonalityService<T> {
  
  /**
   * Instantiates a new abstract personality service impl.
   */
  protected AbstractPersonalityServiceImpl() {
    super();
  }
}
