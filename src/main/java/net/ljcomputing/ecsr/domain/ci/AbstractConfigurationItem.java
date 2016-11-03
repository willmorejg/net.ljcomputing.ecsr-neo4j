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

import java.util.ArrayList;
import java.util.List;

import org.neo4j.ogm.annotation.Relationship;

import net.ljcomputing.ecsr.domain.AbstractDomain;
import net.ljcomputing.ecsr.domain.Domain;
import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.domain.person.Personality;

/**
 * The Class AbstractConfigurationItem.
 *
 * @author James G. Willmore
 */
public abstract class AbstractConfigurationItem extends AbstractDomain
    implements Domain, ConfigurationItem {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 15423254305895739L;

  /** The name. */
  private String name;

  /** The description. */
  private String description;

  /** The owners. */
  @Relationship(direction = Relationship.INCOMING)
  private List<Personality> owners = new ArrayList<Personality>();
  
  /** The manufacturer. */
  private Organization manufacturer;
  
  /** The serial number. */
  private String serialNumber;
  
  /** The license number. */
  private String licenseNumber;
  
  /** The version. */
  private String version;

  /**
   * Gets the name.
   *
   * @return the name
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem#getName()
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Sets the name.
   *
   * @param name the new name
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem#setName(java.lang.String)
   */
  @Override
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Gets the description.
   *
   * @return the description
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem#getDescription()
   */
  @Override
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description.
   *
   * @param description the new description
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem
   *    #setDescription(java.lang.String)
   */
  @Override
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Gets the owners.
   *
   * @return the owners
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem#getOwners()
   */
  @Override
  public List<Personality> getOwners() {
    return owners;
  }

  /**
   * Sets the owners.
   *
   * @param owners the new owners
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem#setOwners(java.util.List)
   */
  @Override
  public void setOwners(final List<Personality> owners) {
    this.owners = owners;
  }

  /**
   * Adds the owner.
   *
   * @param owner the owner
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem
   *    #addOwner(net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public void addOwner(final Personality owner) {
    owners.add(owner);
  }

  /**
   * Removes the owner.
   *
   * @param owner the owner
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem
   *    #removeOwner(net.ljcomputing.ecsr.domain.person.Personality)
   */
  @Override
  public void removeOwner(final Personality owner) {
    owners.remove(owner);
  }

  /**
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem#getManufacturer()
   */
  @Override
  public Organization getManufacturer() {
    return manufacturer;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem
   *    #setManufacturer(net.ljcomputing.ecsr.domain.person.Organization)
   */
  @Override
  public void setManufacturer(final Organization manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem#getSerialNumber()
   */
  @Override
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem
   *    #setSerialNumber(java.lang.String)
   */
  @Override
  public void setSerialNumber(final String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem#getLicenseNumber()
   */
  @Override
  public String getLicenseNumber() {
    return licenseNumber;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem
   *    #setLicenseNumber(java.lang.String)
   */
  @Override
  public void setLicenseNumber(final String licenseNumber) {
    this.licenseNumber = licenseNumber;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem#getVersion()
   */
  @Override
  public String getVersion() {
    return version;
  }

  /**
   * @see net.ljcomputing.ecsr.domain.ci.ConfigurationItem
   *    #setVersion(java.lang.String)
   */
  @Override
  public void setVersion(final String version) {
    this.version = version;
  }
}
