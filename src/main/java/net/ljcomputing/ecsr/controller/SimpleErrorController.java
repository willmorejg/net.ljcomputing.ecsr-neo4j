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

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.util.Assert;
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
@RequestMapping("/error")
public class SimpleErrorController implements ErrorController {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleErrorController.class);

  /** The error attributes. */
  private final ErrorAttributes errorAttributes;

  /**
   * Instantiates a new simple error controller.
   *
   * @param errorAttributes the error attributes
   */
  @Autowired
  public SimpleErrorController(final ErrorAttributes errorAttributes) {
    Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
    LOGGER.debug("errorAttributes: {}", errorAttributes);
    this.errorAttributes = errorAttributes;
  }

  /**
   * @see org.springframework.boot.autoconfigure.web.ErrorController#getErrorPath()
   */
  @Override
  public String getErrorPath() {
    return "/error";
  }

  /**
   * Error.
   *
   * @param request the a request
   * @return the map
   */
  @SuppressWarnings("unused")
  @RequestMapping
  public Map<String, Object> error(final HttpServletRequest request) {
    LOGGER.error("Exception thrown: ", request.getParameterMap());

    if (request == null) {
      LOGGER.info("Throwing empty map");

      return Collections.emptyMap();
    }

    final Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
    final String trace = (String) body.get("trace");
    LOGGER.debug("trace: {}", trace);
    if (trace != null) {
      String[] lines = trace.split("\n\t");
      body.put("trace", lines);
    }
    return body;
  }

  /**
   * Gets the trace parameter.
   *
   * @param request the request
   * @return the trace parameter
   */
  private boolean getTraceParameter(final HttpServletRequest request) {
    String parameter = request.getParameter("trace");
    if (parameter == null) {
      return false;
    }
    return !"false".equals(parameter.toLowerCase());
  }

  /**
   * Gets the error attributes.
   *
   * @param request the a request
   * @param includeStackTrace the include stack trace
   * @return the error attributes
   */
  private Map<String, Object> getErrorAttributes(final HttpServletRequest request,
      final boolean includeStackTrace) {
    LOGGER.error("Exception thrown - attributes: ", request.getParameterMap());
    final RequestAttributes requestAttributes = new ServletRequestAttributes(request);
    return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
  }
}