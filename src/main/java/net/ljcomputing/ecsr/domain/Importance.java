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
 * Enumeration that describes the importance of a person, place, or thing.
 * 
 * @author James G. Willmore
 *
 */
public enum Importance {
  CRITICAL("Critical"), NON_CRITICAL("Non-Critical"), NONE("None");

  /** The display value. */
  private String displayValue;

  /**
   * Instantiates a new importance.
   *
   * @param displayValue the display value
   */
  private Importance(final String displayValue) {
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
