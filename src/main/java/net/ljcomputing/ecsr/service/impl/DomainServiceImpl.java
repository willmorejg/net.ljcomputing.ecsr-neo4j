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

import org.springframework.beans.factory.annotation.Autowired;

import net.ljcomputing.ecsr.domain.Domain;
import net.ljcomputing.ecsr.repository.DomainRepository;
import net.ljcomputing.ecsr.service.DomainService;

/**
 * Domain service implementation.
 *
 * @author James G. Willmore
 * @param <T> the domain type
 */
public abstract class DomainServiceImpl<T extends Domain, R extends DomainRepository<T>>
    implements DomainService<T> {

  /** The repository. */
  @Autowired
  protected transient R repository;

  /**
   * Iterable as list.
   *
   * @param iterable the iterable
   * @return the list
   */
  protected List<T> asList(Iterable<T> iterable) {
    return (List<T>) iterable;
  }

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#save(net.ljcomputing.ecsr.domain.Domain)
   */
  @Override
  public T save(T domain) {
    return repository.save(domain);
  }

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#delete(net.ljcomputing.ecsr.domain.Domain)
   */
  @Override
  public void delete(T domain) {
    repository.delete(domain);
  }

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#delete(java.lang.Long)
   */
  @Override
  public void delete(final Long id) {
    repository.delete(id);
  }

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#findAll()
   */
  @Override
  public List<T> findAll() {
    return asList(repository.findAll());
  }

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#findByUuid(java.lang.String)
   */
  @Override
  public T findByUuid(final String uuid) {
    return repository.findByUuid(uuid);
  }

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#deleteByUuid(java.lang.String)
   */
  @Override
  public void deleteByUuid(final String uuid) {
    repository.deleteByUuid(uuid);
  }

}
