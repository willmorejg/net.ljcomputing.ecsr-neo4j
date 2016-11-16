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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import net.ljcomputing.ecsr.configuration.EcsrNeo4JConfiguration;
import net.ljcomputing.ecsr.domain.ci.Database;
import net.ljcomputing.ecsr.domain.ci.Hardware;
import net.ljcomputing.ecsr.domain.ci.Software;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.service.ci.DatabaseCiService;
import net.ljcomputing.ecsr.service.ci.HardwareCiService;
import net.ljcomputing.ecsr.service.ci.SoftwareCiService;
import net.ljcomputing.ecsr.service.person.PersonService;

/**
 * @author James G. Willmore
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EcsrNeo4JConfiguration.class)
@WebAppConfiguration
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CiCreationUnitTests {

  /** The Constant LOGGER. */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(CiCreationUnitTests.class);
  
  /** The software repos. */
  @Autowired
  private transient SoftwareCiService softwareService;
  
  /** The hardware repos. */
  @Autowired
  private transient HardwareCiService hardwareService;

  /** The database repos. */
  @Autowired
  private transient DatabaseCiService databaseService;
  
  /** The person repos. */
  @Autowired
  private transient PersonService personService;

  /** The version. */
  private static final String[] VERSION = {"1", "2", "3"};

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

      softwareService.save(software);
    }
  }

  /**
   * Test 02.
   */
  @Test
  @Rollback(false)
  public void test02() {
    final String[] names = { "x86", "amd64", "apple" };

    int inc = 0; //NOPMD
    for (final String name : names) {
      final String iVersion = VERSION[inc];
      final Hardware hardware = new Hardware(); //NOPMD

      hardware.setName(name);
      hardware.setVersion(iVersion);

      hardwareService.save(hardware);
      
      inc++; //NOPMD
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

      databaseService.save(database);
    }
  }
  
  /**
   * Test 04.
   */
  @Test
  @Rollback(false)
  public void test04() {
    final Iterable<Software> softwares = softwareService.findAll();
    
    for (final Software software : softwares) {
      final Iterable<Person> people = personService.findAll();
      
      for (final Person person : people) {
        software.addOwner(person);
      }
      
      softwareService.save(software);
    }
    
    final Software apple = softwareService.findByName("Mac OS X"); //NOPMD
    final Iterable<Person> people = personService.findAll();
    
    for (final Person person : people) {
      apple.removeOwner(person); //NOPMD
      softwareService.save(apple);
    }
  }
}
