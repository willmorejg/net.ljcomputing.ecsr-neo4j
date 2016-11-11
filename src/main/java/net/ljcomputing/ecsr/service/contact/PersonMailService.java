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

package net.ljcomputing.ecsr.service.contact;

import net.ljcomputing.ecsr.domain.contact.MailContact;
import net.ljcomputing.ecsr.domain.contact.PersonalityContactImpl;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.repository.contact.PersonalityContactRepository;

/**
 * @author James G. Willmore
 *
 */
public interface PersonMailService extends
    PersonalityContactService
    <PersonalityContactImpl<Person, MailContact>, Person, 
    MailContact, 
    PersonalityContactRepository<PersonalityContactImpl<Person, MailContact>>> {
}
