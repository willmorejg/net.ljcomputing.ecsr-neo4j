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

package net.ljcomputing.ecsr.controller.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.ljcomputing.ecsr.controller.ControllerPath;
import net.ljcomputing.ecsr.domain.person.Team;
import net.ljcomputing.ecsr.service.person.TeamService;

/**
 * Person controller.
 * 
 * @author James G. Willmore
 *
 */
@RestController
@RequestMapping(ControllerPath.TEAM)
public class TeamController {

  /** The team service. */
  @Autowired
  private transient TeamService teamSrv;

  /**
   * All teams.
   *
   * @return the list
   */
  @RequestMapping(method = RequestMethod.GET, value = ControllerPath.FIND_ALL, produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE,
      MediaType.APPLICATION_XML_VALUE })
  public List<Team> allTeams() {
    return teamSrv.findAll();
  }

  /**
   * Team by uuid.
   *
   * @param uuid the uuid
   * @return the team
   */
  @RequestMapping(method = RequestMethod.GET, value = ControllerPath.FIND_BY_UUID, produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE,
      MediaType.APPLICATION_XML_VALUE })
  public Team teamByUuid(@PathVariable("uuid") final String uuid) {
    return teamSrv.findByUuid(uuid);
  }

  /**
   * Save.
   *
   * @param team the team
   * @return the team
   */
  @RequestMapping(method = RequestMethod.POST, value = ControllerPath.SAVE, produces = {
      MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE,
      MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
          MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
  public Team save(@RequestBody final Team team) {
    return teamSrv.save(team);
  }

  /**
   * Delete by uuid.
   *
   * @param uuid the uuid
   */
  @RequestMapping(method = RequestMethod.DELETE, value = ControllerPath.DELETE_BY_UUID)
  public void deleteByUuid(@PathVariable("uuid") final String uuid) {
    teamSrv.deleteByUuid(uuid);
  }
}
