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

package net.ljcomputing.ecsr.repository;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.ecsr.configuration.EcsrNeo4JConfiguration;
import net.ljcomputing.ecsr.domain.ci.Database;
import net.ljcomputing.ecsr.domain.ci.Hardware;
import net.ljcomputing.ecsr.domain.ci.Software;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.repository.ci.DatabaseRepository;
import net.ljcomputing.ecsr.repository.ci.HardwareRepository;
import net.ljcomputing.ecsr.repository.ci.SoftwareRepository;
import net.ljcomputing.ecsr.repository.person.PersonRepository;

/**
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EcsrNeo4JConfiguration.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CiCreationUnitTests {

  /** The Constant LOGGER. */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(CiCreationUnitTests.class);
  
  /** The software repos. */
  @Autowired
  private transient SoftwareRepository softwareRepos;
  
  /** The hardware repos. */
  @Autowired
  private transient HardwareRepository hardwareRepos;

  /** The database repos. */
  @Autowired
  private transient DatabaseRepository databaseRepos;
  
  /** The person repos. */
  @Autowired
  private transient PersonRepository personRepos;

  /** The version. */
  final String[] version = {"1", "2", "3"};

  /**
   * Test 01.
   */
  @Test
  @Rollback(false)
  public void test01() {
    final String[] names = { "Linux", "Windows", "Mac OS X" };

    for (final String name : names) {
      final Software software = new Software(); //NOPMD

      software.setName(name);

      softwareRepos.save(software);
    }
  }

  /**
   * Test 02.
   */
  @Test
  @Rollback(false)
  public void test02() {
    final String[] names = { "x86", "amd64", "apple" };

    int inc = 0;
    for (final String name : names) {
      final String iVersion = version[inc];
      final Hardware hardware = new Hardware(); //NOPMD

      hardware.setName(name);
      hardware.setVersion(iVersion);

      hardwareRepos.save(hardware);
      
      inc++;
    }
  }
  
  /**
   * Test 03.
   */
  @Test
  @Rollback(false)
  public void test03() {
    final String[] names = { "Oracle", "MySQL", "PostgreSQL" };

    for (final String name : names) {
      final Database database = new Database(); //NOPMD

      database.setName(name);

      databaseRepos.save(database);
    }
  }
  
  /**
   * Test 04.
   */
  @Test
  @Rollback(false)
  public void test04() {
    final Iterable<Software> softwares = softwareRepos.findAll();
    
    for (final Software software : softwares) {
      final Iterable<Person> people = personRepos.findAll();
      
      for (final Person person : people) {
        software.addOwner(person);
      }
      
      softwareRepos.save(software);
    }
    
    final Software apple = softwareRepos.findByName("Mac OS X"); //NOPMD
    final Iterable<Person> people = personRepos.findAll();
    
    for (final Person person : people) {
      apple.removeOwner(person); //NOPMD
      softwareRepos.save(apple);
    }
  }
}
