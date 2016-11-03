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

package net.ljcomputing.ecsr.domain.ci;

import java.util.List;

import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.domain.person.Personality;

/**
 * Interface shared by all configuration items.
 * 
 * @author James G. Willmore
 *
 */
public interface ConfigurationItem {

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
   * Gets the manufacturer.
   *
   * @return the manufacturer
   */
  Organization getManufacturer();

  /**
   * Sets the manufacturer.
   *
   * @param manufacturer the new manufacturer
   */
  void setManufacturer(Organization manufacturer);

  /**
   * Gets the serial number.
   *
   * @return the serial number
   */
  String getSerialNumber();

  /**
   * Sets the serial number.
   *
   * @param serialNumber the new serial number
   */
  void setSerialNumber(String serialNumber);

  /**
   * Gets the license number.
   *
   * @return the license number
   */
  String getLicenseNumber();

  /**
   * Sets the license number.
   *
   * @param licenseNumber the new license number
   */
  void setLicenseNumber(String licenseNumber);
  
  /**
   * Gets the version.
   *
   * @return the version
   */
  String getVersion();
  
  /**
   * Sets the version.
   *
   * @param version the new version
   */
  void setVersion(String version);
}
