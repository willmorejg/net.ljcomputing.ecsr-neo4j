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

package net.ljcomputing.ecsr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * ECSR application classes.
 * 
 * @author James G. Willmore
 *
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class EcsrApplication { //NOPMD

  /**
   * The main method.
   *
   * @param args the arguments
   */
  public static void main(final String[] args) {
    SpringApplication.run(EcsrApplication.class, args);
  }
}
