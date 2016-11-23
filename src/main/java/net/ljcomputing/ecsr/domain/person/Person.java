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

package net.ljcomputing.ecsr.domain.person;

import javax.validation.constraints.NotNull;

import net.ljcomputing.ecsr.domain.AbstractDomain;

/**
 * Person domain.
 * 
 * @author James G. Willmore
 *
 */
public class Person extends AbstractDomain implements Personality {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 2273510496398616906L;

  /** The prefix. */
  private String prefix;

  /** The first name. */
  @NotNull(message = "First name may not be null or empty.")
  private String firstName;

  /** The middle name. */
  private String middleName;

  /** The last name. */
  @NotNull(message = "Last name may not be null or empty.")
  private String lastName;

  /** The suffix. */
  private String suffix;

  /**
   * Gets the prefix.
   *
   * @return the prefix
   */
  public String getPrefix() {
    return prefix;
  }

  /**
   * Sets the prefix.
   *
   * @param prefix the new prefix
   */
  public void setPrefix(final String prefix) {
    this.prefix = prefix;
  }

  /**
   * Gets the first name.
   *
   * @return the first name
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * Sets the first name.
   *
   * @param firstName the new first name
   */
  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  /**
   * Gets the middle name.
   *
   * @return the middle name
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * Sets the middle name.
   *
   * @param middleName the new middle name
   */
  public void setMiddleName(final String middleName) {
    this.middleName = middleName;
  }

  /**
   * Gets the last name.
   *
   * @return the last name
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * Sets the last name.
   *
   * @param lastName the new last name
   */
  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  /**
   * Gets the suffix.
   *
   * @return the suffix
   */
  public String getSuffix() {
    return suffix;
  }

  /**
   * Sets the suffix.
   *
   * @param suffix the new suffix
   */
  public void setSuffix(final String suffix) {
    this.suffix = suffix;
  }
}
