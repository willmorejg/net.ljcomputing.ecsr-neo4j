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

import java.util.List;

import net.ljcomputing.ecsr.domain.Domain;
import net.ljcomputing.ecsr.domain.person.Personality;

/**
 * Interface shared by all systems.
 * 
 * @author James G. Willmore
 *
 */
public interface System extends Domain {

  /**
   * Gets the name.
   *
   * @return the name
   */
  String getName();

  /**
   * Sets the name.
   *
   * @param name the new name
   */
  void setName(String name);

  /**
   * Gets the description.
   *
   * @return the description
   */
  String getDescription();

  /**
   * Sets the description.
   *
   * @param description the new description
   */
  void setDescription(String description);

  /**
   * Gets the owners of the configuration item.
   *
   * @return the owners
   */
  List<Personality> getOwners();

  /**
   * Sets the owners.
   *
   * @param owners the new owners
   */
  void setOwners(List<Personality> owners);

  /**
   * Adds the owner.
   *
   * @param owner the owner
   */
  void addOwner(Personality owner);

  /**
   * Removes the owner.
   *
   * @param owner the owner
   */
  void removeOwner(Personality owner);

  /**
   * Gets the system components.
   *
   * @return the system components
   */
  List<SystemComponent> getSystemComponents();

  /**
   * Sets the system components.
   *
   * @param components the new system components
   */
  void setSystemComponents(List<SystemComponent> components);

  /**
   * Adds the system component.
   *
   * @param component the component
   */
  void addSystemComponent(SystemComponent component);

  /**
   * Removes the system component.
   *
   * @param component the component
   */
  void removeSystemComponent(SystemComponent component);
}
