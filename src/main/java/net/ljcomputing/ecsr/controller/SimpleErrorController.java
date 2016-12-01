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

/**
 * @author James G. Willmore
 *
 */
//@RestController
//@RequestMapping("/error")
public class SimpleErrorController {//implements ErrorController {
//
//  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleErrorController.class);
//
//  private final ErrorAttributes errorAttributes;
//
//  @Autowired
//  public SimpleErrorController(ErrorAttributes errorAttributes) {
//    Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
//    LOGGER.debug("errorAttributes: {}", errorAttributes);
//    this.errorAttributes = errorAttributes;
//  }
//
//  @Override
//  public String getErrorPath() {
//    return "/error";
//  }
//
//  @RequestMapping
//  public Map<String, Object> error(HttpServletRequest aRequest) {
//    LOGGER.error("Exception thrown: ", aRequest.getParameterMap());
//    
//    if (aRequest == null) {
//      LOGGER.info("Throwing empty map");
//      return new HashMap<String, Object>();
//    }
//    
//    Map<String, Object> body = getErrorAttributes(aRequest, getTraceParameter(aRequest));
//    String trace = (String) body.get("trace");
//    LOGGER.debug("trace: {}", trace);
//    if (trace != null) {
//      String[] lines = trace.split("\n\t");
//      body.put("trace", lines);
//    }
//    return body;
//  }
//
//  private boolean getTraceParameter(HttpServletRequest request) {
//    String parameter = request.getParameter("trace");
//    if (parameter == null) {
//      return false;
//    }
//    return !"false".equals(parameter.toLowerCase());
//  }
//
//  private Map<String, Object> getErrorAttributes(HttpServletRequest aRequest,
//      boolean includeStackTrace) {
//    LOGGER.error("Exception thrown - attributes: ", aRequest.getParameterMap());
//    RequestAttributes requestAttributes = new ServletRequestAttributes(aRequest);
//    return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
//  }
}