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

package net.ljcomputing.ecsr.service.person;

import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.domain.person.OrganizationalMember;
import net.ljcomputing.ecsr.domain.person.Person;

/**
 * Organizational member service.
 * 
 * @author James G. Willmore
 *
 */
public interface OrganizationalMemberService
    extends MembershipService<Person, Organization, OrganizationalMember> {
}
