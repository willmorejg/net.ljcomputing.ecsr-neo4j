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

import net.ljcomputing.ecsr.domain.Domain;

/**
 * Interface shared by all people, teams, and organizations. 
 * The name of the interface is derived from the legal term 
 * "legal personality", which encompasses different types of 
 * entities that can enter into contracts, or for our 
 * purposes, can have such attributes as a mailing address.
 * 
 * @author James G. Willmore
 *
 */
public interface Personality extends Domain {

}
