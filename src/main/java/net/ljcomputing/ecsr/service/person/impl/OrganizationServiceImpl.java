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

package net.ljcomputing.ecsr.service.person.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.ecsr.domain.person.Organization;
import net.ljcomputing.ecsr.repository.person.OrganizationRepository;
import net.ljcomputing.ecsr.service.person.OrganizationService;

/**
 * Organization service implementation.
 * 
 * @author James G. Willmore
 *
 */
@Service
@Transactional
public class OrganizationServiceImpl extends AbstractPersonalityServiceImpl<Organization>
    implements OrganizationService {

  /** The person repository. */
  @Autowired
  protected transient OrganizationRepository orgRepos;

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl
   *    #save(net.ljcomputing.ecsr.domain.Domain)
   */
  @Override
  public Organization save(final Organization domain) {
    return orgRepos.save(domain);
  }

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl
   *    #delete(java.lang.Long)
   */
  @Override
  public void delete(final Long id) {
    orgRepos.delete(id);
  }

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl#findAll()
   */
  @Override
  public List<Organization> findAll() {
    return (List<Organization>) orgRepos.findAll();
  }

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl
   *    #findByUuid(java.lang.String)
   */
  @Override
  public Organization findByUuid(final String uuid) {
    return orgRepos.findByUuid(uuid);
  }

  /**
   * @see net.ljcomputing.ecsr.service.impl.AbstractDomainServiceImpl
   *    #deleteByUuid(java.lang.String)
   */
  @Override
  public void deleteByUuid(final String uuid) {
    orgRepos.deleteByUuid(uuid);
  }
}
