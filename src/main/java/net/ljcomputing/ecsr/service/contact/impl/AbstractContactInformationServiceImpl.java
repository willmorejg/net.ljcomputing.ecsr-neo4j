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

package net.ljcomputing.ecsr.service.contact.impl;

import net.ljcomputing.ecsr.domain.contact.AbstractContactInformation;
import net.ljcomputing.ecsr.repository.contact.ContactInformationRepository;
import net.ljcomputing.ecsr.service.contact.ContactInformationService;
import net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl;

/**
 * Abstract implementation of the contact information service.
 * 
 * @author James G. Willmore
 *
 */
public abstract class AbstractContactInformationServiceImpl
    <T extends AbstractContactInformation, R extends ContactInformationRepository<T>>
    extends AbstractDomainServiceImpl<T, R>
    implements ContactInformationService<T, R> {
  
  /**
   * Instantiates a new abstract contact information service impl.
   */
  protected AbstractContactInformationServiceImpl() {
    super();
  }
}
