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

package net.ljcomputing.ecsr.domain;

/**
 * Enumeration of contact order type. For example: if there are 
 * three phone numbers available, which phone number is supposed to be 
 * called first on a support call.
 * 
 * @author James G. Willmore
 *
 */
public enum OrderType {
  PRIMARY("Primary"), SECONDARY("Secondary"), TERIARY("Teriary"), OTHER("Other");

  /** The display value. */
  private String displayValue;

  /**
   * Instantiates a new contact order type.
   *
   * @param displayedAs the displayed as
   */
  private OrderType(final String displayValue) {
    this.displayValue = displayValue;
  }

  /**
   * @see java.lang.Enum#toString()
   */
  @Override
  public String toString() {
    return displayValue;
  }

}
