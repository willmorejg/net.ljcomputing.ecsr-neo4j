package net.ljcomputing.ecsr.security.handler;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import net.ljcomputing.ecsr.controller.ErrorInfo;

/**
 * AJAX-aware authentication failure handler.
 */
@Component
public class AjaxAwareAuthenticationFailureHandler implements AuthenticationFailureHandler {

  /**
   * @see org.springframework.security.web.authentication
   *    .AuthenticationFailureHandler
   *        #onAuthenticationFailure(javax.servlet.http.HttpServletRequest, 
   *            javax.servlet.http.HttpServletResponse, 
   *                org.springframework.security.core.AuthenticationException)
   */
  @Override
  public void onAuthenticationFailure(final HttpServletRequest request,
      final HttpServletResponse response, final AuthenticationException exception)
      throws IOException, ServletException {
    final LocalDateTime ldt = LocalDateTime.now();
    final DateTimeFormatter fmt = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // NOPMD
    final String timestamp = ldt.format(fmt); // NOPMD
    final String path = request.getRequestURL().toString(); // NOPMD
    final ErrorInfo errorInfo = 
        new ErrorInfo(timestamp, HttpStatus.UNAUTHORIZED, path, exception);
    final Gson gson = new Gson();
    
    response.setStatus(HttpStatus.UNAUTHORIZED.value()); // NOPMD
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    gson.toJson(errorInfo, response.getWriter());
  }
}
