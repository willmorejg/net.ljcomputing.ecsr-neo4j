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

package net.ljcomputing.ecsr.aspect;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import net.ljcomputing.ecsr.domain.Domain;

/**
 * Neo4J update Aspect. 
 * 
 * @author James G. Willmore
 *
 */
@Aspect
@Component
public class SaveAspect {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(SaveAspect.class);

  /**
   * Update method.
   *
   * @param point the point
   * @throws Throwable the throwable
   */
  @Around("execution(* org.springframework.data.repository.*.save(Object)) && args(entity)")
  public void updateMethod(final ProceedingJoinPoint point, final Object entity) throws Throwable {

    if (entity instanceof Domain) {
      final Domain domain = (Domain) entity;
      final String entityClassName = entity.getClass().getName(); //NOPMD
      final Long id = domain.getId(); //NOPMD

      if (id != null) { //NOPMD
        domain.modified(); //NOPMD
        LOGGER.debug(" ... updating entity: [{}]{}", entityClassName,
            ToStringBuilder.reflectionToString(domain, ToStringStyle.JSON_STYLE));
      } else {
        domain.create(); //NOPMD
        LOGGER.debug(" ... creating entity: [{}]{}", entityClassName,
            ToStringBuilder.reflectionToString(domain, ToStringStyle.JSON_STYLE));
      }

      point.proceed();
    }
  }
}
