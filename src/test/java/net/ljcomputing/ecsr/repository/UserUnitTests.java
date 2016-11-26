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
import net.ljcomputing.ecsr.domain.person.EcsrRole;
import net.ljcomputing.ecsr.domain.person.Person;
import net.ljcomputing.ecsr.domain.person.User;
import net.ljcomputing.ecsr.domain.person.UserPassword;
import net.ljcomputing.ecsr.domain.person.UserRoles;
import net.ljcomputing.ecsr.security.service.UserService;
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
public class UserUnitTests {

  /** The Constant LOGGER. */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(UserUnitTests.class);

  /** The person service. */
  @Autowired
  private transient PersonService personService;

  /** The user service. */
  @Autowired
  private transient UserService userService;

  /**
   * Test 01.
   */
  @Test
  @Rollback(false)
  public void test01() {
    final String[] names = { "Jim", "Willmore", "Alice", "Conner", "Bob", "Smith", "Charlie",
        "Pace" };

    for (int n = 0; n < names.length; n++) {
      final Person person = new Person(); //NOPMD

      person.setFirstName(names[n]); //NOPMD

      n++;

      person.setLastName(names[n]); //NOPMD

      personService.save(person);
    }
  }

  /**
   * Test 02.
   */
  @Test
  @Rollback(false)
  public void test02() {
    final String[] roleNames = { "USER", "ADMIN" };

    for (final String roleName : roleNames) {
      final EcsrRole role = new EcsrRole(); // NOPMD
      role.setRoleName(roleName);
      userService.saveRole(role);
    }
  }

  /**
   * Test 03.
   */
  @Test
  @Rollback(false)
  public void test03() {
    final Iterable<Person> people = personService.findAll();

    for (final Person person : people) {
      final User user = new User(); // NOPMD

      user.setPerson(person);
      user.setUsername(person.getFirstName() + person.getLastName());

      userService.saveUser(user);

      final UserPassword userPassword = new UserPassword(); // NOPMD
      userPassword.setUser(user);
      userPassword.setPassword("P@ssW0rd");
      
      userService.savePassword(userPassword);

      final UserRoles userRoles = new UserRoles(); // NOPMD
      userRoles.setUser(user);

      for (final EcsrRole role : userService.findAllEcsrRoles()) {
        userRoles.addRole(role);
      }
      
      userService.saveUserRoles(userRoles);
    }
  }
}
