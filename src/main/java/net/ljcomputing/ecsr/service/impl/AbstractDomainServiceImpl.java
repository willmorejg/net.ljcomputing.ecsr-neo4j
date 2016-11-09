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

package net.ljcomputing.ecsr.service.impl;

import java.util.List;

import net.ljcomputing.ecsr.domain.Domain;
import net.ljcomputing.ecsr.service.DomainService;

/**
 * Domain service implementation.
 *
 * @author James G. Willmore
 * @param <T> the domain type
 */
public abstract class AbstractDomainServiceImpl<T extends Domain>
    implements DomainService<T> {

  /**
   * @see net.ljcomputing.ecsr.service.DomainService
   *    #save(net.ljcomputing.ecsr.domain.Domain)
   */
  @Override
  public abstract T save(final T domain);

  /**
   * @see net.ljcomputing.ecsr.service.DomainService
   *    #delete(net.ljcomputing.ecsr.domain.Domain)
   */
  @Override
  public void delete(final T domain) {
    delete(domain.getId());
  }

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#delete(java.lang.Long)
   */
  @Override
  public abstract void delete(final Long id);

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#findAll()
   */
  @Override
  public abstract List<T> findAll();

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#findByUuid(java.lang.String)
   */
  @Override
  public abstract T findByUuid(final String uuid);

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#deleteByUuid(java.lang.String)
   */
  @Override
  public abstract void deleteByUuid(final String uuid);
}
