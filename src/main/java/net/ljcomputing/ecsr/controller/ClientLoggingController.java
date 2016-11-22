/**
           Copyright 2015, James G. Willmore

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

package net.ljcomputing.ecsr.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Log messages sent from a JavaScript client.
 * 
 * @author James G. Willmore
 *
 */
@Controller
@RequestMapping("/logger")
public class ClientLoggingController {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ClientLoggingController.class);

  /**
   * Log message from client.
   *
   * @param request
   *            the request
   * @param logMessage
   *            the log message
   */
  @RequestMapping(method = RequestMethod.POST, consumes = { "application/json" })
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void logError(final HttpServletRequest request,
      @RequestBody(required = true) final LogMessage logMessage) {
    final String ipAddress = request.getRemoteAddr();
    final String hostname = request.getRemoteHost();
    LOGGER.warn("Client-side log message ({}/{}) - using [{}] to request [{}]: {}", ipAddress,
        hostname, logMessage.getBrowser(), logMessage.getLocation(), logMessage.getMessage());
  }

}
