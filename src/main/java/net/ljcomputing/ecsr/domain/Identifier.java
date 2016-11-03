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

import java.io.Serializable;

/**
 * Interface defining domain identifier methods.
 * 
 * @author James G. Willmore
 *
 */
public interface Identifier extends Serializable {

  /**
   * Gets the id.
   *
   * @return the id
   */
  Long getId();

  /**
   * Sets the id.
   *
   * @param id the new id
   */
  void setId(Long id);

  /**
   * Gets the uuid.
   *
   * @return the uuid
   */
  String getUuid();

  /**
   * Creates the identifier with member values set appropriately.
   */
  void create();
}
