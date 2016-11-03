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

package net.ljcomputing.ecsr.domain.contact;

import net.ljcomputing.ecsr.domain.Domain;

/**
 * Phone contact domain.
 * 
 * @author James G. Willmore
 *
 */
public class PhoneContact extends AbstractContactInformation implements ContactInformation, Domain {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -6457539275815265838L;

  /** The county code. */
  private String countyCode;

  /** The area code. */
  private String areaCode;

  /** The prefix. */
  private String prefix;

  /** The number. */
  private String number;

  /**
   * Gets the county code.
   *
   * @return the county code
   */
  public String getCountyCode() {
    return countyCode;
  }

  /**
   * Sets the county code.
   *
   * @param countyCode the new county code
   */
  public void setCountyCode(final String countyCode) {
    this.countyCode = countyCode;
  }

  /**
   * Gets the area code.
   *
   * @return the area code
   */
  public String getAreaCode() {
    return areaCode;
  }

  /**
   * Sets the area code.
   *
   * @param areaCode the new area code
   */
  public void setAreaCode(final String areaCode) {
    this.areaCode = areaCode;
  }

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
   * Gets the number.
   *
   * @return the number
   */
  public String getNumber() {
    return number;
  }

  /**
   * Sets the number.
   *
   * @param number the new number
   */
  public void setNumber(final String number) {
    this.number = number;
  }
}
