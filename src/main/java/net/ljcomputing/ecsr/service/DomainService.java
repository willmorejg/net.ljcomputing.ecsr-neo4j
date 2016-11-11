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

package net.ljcomputing.ecsr.service;

import java.util.List;

import net.ljcomputing.ecsr.domain.Domain;
import net.ljcomputing.ecsr.repository.DomainRepository;

/**
 * Interface shared by all domain services.
 *
 * @author James G. Willmore
 * @param <T> the domain type
 */
public interface DomainService<T extends Domain, R extends DomainRepository<T>> {
  
  /**
   * Gets the repository.
   *
   * @return the repository
   */
  R getRepository();
  
  /**
   * Sets the repository.
   *
   * @param repository the new repository
   */
  void setRepository(R repository);
  
  /**
   * Save the doamin.
   *
   * @param domain the domain
   * @return the t
   */
  T save(T domain);
  
  /**
   * Delete the domain.
   *
   * @param domain the domain
   */
  void delete(T domain);
  
  /**
   * Delete the domain by id.
   *
   * @param id the id
   */
  void delete(Long id);
  
  /**
   * Find all the domains.
   *
   * @return the list
   */
  List<T> findAll();
  
  /**
   * Find by uuid.
   *
   * @param uuid the uuid
   * @return the domain
   */
  T findByUuid(String uuid);

  /**
   * Delete by uuid.
   *
   * @param uuid the uuid
   */
  void deleteByUuid(String uuid);
}
