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

package net.ljcomputing.ecsr.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The Class SimpleErrorController.
 *
 * @author James G. Willmore
 */
@RestController
public class SimpleErrorController implements ErrorController {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleErrorController.class);

  /** The Constant PATH. */
  private static final String PATH = "/error";

  /** The debug. */
  @Value("${debug:true}")
  private boolean debug;

  /** The error attributes. */
  @Autowired
  private ErrorAttributes errorAttributes;

  /** The timestamp format. */
  private static final DateTimeFormatter FORMAT = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm:ss"); // NOPMD

  /**
   * Gets the current timestamp.
   *
   * @return the current timestamp
   */
  private static String getCurrentTimestamp() {
    return FORMAT.format(LocalDateTime.now());
  }

  /**
   * Gets the request path.
   *
   * @param req the req
   * @return the request path
   */
  private static String getRequestPath(final HttpServletRequest req) {
    final StringBuffer reqPath = req.getRequestURL();
    return reqPath.toString(); // NOPMD
  }

  /**
   * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
   */

  @Override
  public String getErrorPath() {
    return PATH;
  }

  /**
   * Error.
   *
   * @param request the a request
   * @return the map
   */
  @SuppressWarnings("unused")
  @RequestMapping(PATH)
  public ErrorInfo error(final HttpServletRequest request, final HttpServletResponse response) {
    return new ErrorInfo(getCurrentTimestamp(), HttpStatus.valueOf(response.getStatus()),
        getRequestPath(request), 
          new Exception((String)getErrorAttributes(request, debug).get("trace")));
  }

  /**
   * Gets the error attributes.
   *
   * @param request the a request
   * @param includeStackTrace the include stack trace
   * @return the error attributes
   */

  private Map<String, Object> getErrorAttributes(HttpServletRequest request,
      boolean includeStackTrace) {
    final RequestAttributes requestAttributes = new ServletRequestAttributes(request);
    return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
  }
}
