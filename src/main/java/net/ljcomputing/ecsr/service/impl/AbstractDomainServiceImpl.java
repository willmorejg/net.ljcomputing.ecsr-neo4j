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
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.neo4j.cypher.ConstraintValidationException;
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
public abstract class AbstractDomainServiceImpl<T extends Domain, R extends DomainRepository<T>>
    implements DomainService<T, R> {

  /** The repository for the entities related to the service. */
  protected R repository;
  
  /** The validator. */
  @Autowired
  protected transient Validator validator;
  
  /**
   * @see net.ljcomputing.ecsr.service.DomainService#getRepository()
   */
  @Override
  public R getRepository() {
    return repository;
  }

  /**
   * @see net.ljcomputing.ecsr.service.DomainService
   *    #setRepository(net.ljcomputing.ecsr.repository.DomainRepository)
   */
  @Override
  public abstract void setRepository(R repository);

  /**
   * Gets the data violations.
   *
   * @param violations the violations
   * @return the data violations
   */
  public final String getDataViolations(final Set<ConstraintViolation<T>> violations) {
    final StringBuilder builder = new StringBuilder();
    
    for (final ConstraintViolation<T> violation : violations) {
      builder.append("<li>" + violation.getMessage() + "</li>"); // NOPMD
    }
    
    return builder.toString();
  }
  
  /**
   * @see net.ljcomputing.ecsr.service.DomainService
   *    #save(net.ljcomputing.ecsr.domain.Domain)
   */
  @Override
  public T save(final T domain) {
    // if the domain exists, and the save is actually an update, set the 
    // createdTs to what is already persisted; this removes the need to 
    // always make sure the createdTs is set after the update
    if (domain.getId() != null && domain.getUuid() != null) {
      final Set<ConstraintViolation<T>> violations = validator.validate(domain);

      if (violations != null && !violations.isEmpty()) { // NOPMD
        throw new ConstraintValidationException(
            "The values provided are invalid.<br/><ul>" + getDataViolations(violations) + "</ul>");
      }
      
      final Domain old = getRepository().findByUuid(domain.getUuid()); //NOPMD
      domain.setCreatedTs(old.getCreatedTs()); //NOPMD
    }
    
    return getRepository().save(domain); //NOPMD
  }

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
  public void delete(final Long id) {
    getRepository().delete(id); //NOPMD
  }

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#findAll()
   */
  @Override
  public List<T> findAll() {
    return (List<T>) getRepository().findAll(); //NOPMD
  }

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#findByUuid(java.lang.String)
   */
  @Override
  public T findByUuid(final String uuid) {
    return getRepository().findByUuid(uuid); //NOPMD
  }

  /**
   * @see net.ljcomputing.ecsr.service.DomainService#deleteByUuid(java.lang.String)
   */
  @Override
  public void deleteByUuid(final String uuid) {
    getRepository().deleteByUuid(uuid); //NOPMD
  }
}
