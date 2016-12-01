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

package net.ljcomputing.ecsr.controller;

/**
 * ECSR controller paths; URL path locations.
 * 
 * @author James G. Willmore
 *
 */
public final class ControllerPath {
  /** Path /login. */
  public static final String LOGIN = "/auth/logon";
  
  /** Path /findAll. */
  public static final String FIND_ALL = "/findAll";

  /** Path /findByUuid. */
  public static final String FIND_BY_UUID = "/findByUuid/{uuid}";

  /** Path /save. */
  public static final String SAVE = "/save";

  /** Path /deleteByUuid. */
  public static final String DELETE_BY_UUID = "/deleteByUuid/{uuid}";

  /** Path /people. */
  public static final String PEOPLE = "/people";

  /** Path /team. */
  public static final String TEAM = "/team";
  
  /**
   * Instantiates a new controller paths.
   */
  private ControllerPath() {
    // no-op
  }
}
