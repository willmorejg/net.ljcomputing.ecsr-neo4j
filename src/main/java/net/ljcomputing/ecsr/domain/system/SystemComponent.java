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

package net.ljcomputing.ecsr.domain.system;

import net.ljcomputing.ecsr.domain.Domain;
import net.ljcomputing.ecsr.domain.ci.ConfigurationItem;

/**
 * Interface shared by all system components.
 * 
 * @author James G. Willmore
 *
 */
public interface SystemComponent extends Domain {
  
  /**
   * Configuration item associated with the system component.
   *
   * @return the configuration item
   */
  ConfigurationItem configurationItem();
  
  /**
   * Configuration item associated with the system component.
   *
   * @param configurationItem the configuration item
   */
  void configurationItem(ConfigurationItem configurationItem);

  /**
   * The system the component is part of.
   *
   * @return the system
   */
  System componentOf();

  /**
   * The system the component is part of.
   *
   * @param system the system
   */
  void componentOf(System system);
}
