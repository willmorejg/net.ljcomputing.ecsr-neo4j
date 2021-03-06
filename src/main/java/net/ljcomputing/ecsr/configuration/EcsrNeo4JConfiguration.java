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

package net.ljcomputing.ecsr.configuration;

import javax.validation.Validator;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * ECSR Neo4J configuration.
 * 
 * @author James G. Willmore
 *
 */
@Configuration
@ComponentScan(basePackages = { "net.ljcomputing.ecsr" })
@EnableTransactionManagement
@EnableNeo4jRepositories(basePackages = "net.ljcomputing.ecsr.repository")
public class EcsrNeo4JConfiguration extends Neo4jConfiguration {

  /**
   * Gets the session factory.
   *
   * @return the session factory
   */
  @Override
  @Bean
  public SessionFactory getSessionFactory() {
    return new SessionFactory("net.ljcomputing.ecsr.domain");
  }
  
  /**
   * Bean validation.
   *
   * @return the validator
   */
  @Bean
  public Validator beanValidation() {
    return new LocalValidatorFactoryBean();
  }
}
