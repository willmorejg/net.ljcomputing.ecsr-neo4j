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

package net.ljcomputing.ecsr.service.ci.impl;

import net.ljcomputing.ecsr.domain.ci.ConfigurationItem;
import net.ljcomputing.ecsr.repository.ci.ConfigurationItemRepository;
import net.ljcomputing.ecsr.service.ci.ConfigurationItemService;
import net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl;

/**
 * Abstract implementation of configuration items services.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractConfigurationItemService
    <T extends ConfigurationItem, R extends ConfigurationItemRepository<T>>
    extends AbstractDomainServiceImpl<T, R>
    implements ConfigurationItemService<T, R> {
  
  /**
   * Instantiates a new abstract configuration item service.
   */
  protected AbstractConfigurationItemService() {
    super();
  }

  /**
   * Find by name.
   *
   * @param name the name
   * @return the database
   */
  @Override
  public abstract T findByName(String name);
}
