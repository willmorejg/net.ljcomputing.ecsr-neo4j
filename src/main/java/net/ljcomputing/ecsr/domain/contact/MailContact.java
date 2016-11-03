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
 * Mail contact domain.
 * 
 * @author James G. Willmore
 *
 */
public class MailContact extends AbstractContactInformation implements ContactInformation, Domain {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 7909466532692304945L;

  /** The address 1. */
  private String address1;

  /** The address 2. */
  private String address2;

  /** The city. */
  private String city;

  /** The state. */
  private String state;

  /** The zip code. */
  private String zipCode;

  /** The zip plus 4. */
  private String zipPlus4;

  /**
   * Gets the address 1.
   *
   * @return the address 1
   */
  public String getAddress1() {
    return address1;
  }

  /**
   * Sets the address 1.
   *
   * @param address1 the new address 1
   */
  public void setAddress1(final String address1) {
    this.address1 = address1;
  }

  /**
   * Gets the address 2.
   *
   * @return the address 2
   */
  public String getAddress2() {
    return address2;
  }

  /**
   * Sets the address 2.
   *
   * @param address2 the new address 2
   */
  public void setAddress2(final String address2) {
    this.address2 = address2;
  }

  /**
   * Gets the city.
   *
   * @return the city
   */
  public String getCity() {
    return city;
  }

  /**
   * Sets the city.
   *
   * @param city the new city
   */
  public void setCity(final String city) {
    this.city = city;
  }

  /**
   * Gets the state.
   *
   * @return the state
   */
  public String getState() {
    return state;
  }

  /**
   * Sets the state.
   *
   * @param state the new state
   */
  public void setState(final String state) {
    this.state = state;
  }

  /**
   * Gets the zip code.
   *
   * @return the zip code
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * Sets the zip code.
   *
   * @param zipCode the new zip code
   */
  public void setZipCode(final String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * Gets the zip plus 4.
   *
   * @return the zip plus 4
   */
  public String getZipPlus4() {
    return zipPlus4;
  }

  /**
   * Sets the zip plus 4.
   *
   * @param zipPlus4 the new zip plus 4
   */
  public void setZipPlus4(final String zipPlus4) {
    this.zipPlus4 = zipPlus4;
  }
}
