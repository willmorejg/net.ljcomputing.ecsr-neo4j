package net.ljcomputing.ecsr.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.google.gson.Gson;

import net.ljcomputing.ecsr.security.model.LoginRequest;

/**
 * AJAX login processing filter.
 *
 * @author James G. Willmore
 */
public class AjaxLoginProcessingFilter extends AbstractAuthenticationProcessingFilter {

  /** The success handler. */
  private final transient AuthenticationSuccessHandler successHandler;

  /** The failure handler. */
  private final transient AuthenticationFailureHandler failureHandler;


  /**
   * Instantiates a new ajax login processing filter.
   *
   * @param defaultProcessUrl the default process url
   * @param successHandler the success handler
   * @param failureHandler the failure handler
   */
  public AjaxLoginProcessingFilter(final String defaultProcessUrl,
      final AuthenticationSuccessHandler successHandler,
      final AuthenticationFailureHandler failureHandler) {
    super(defaultProcessUrl);
    this.successHandler = successHandler;
    this.failureHandler = failureHandler;
  }

  /**
   * @see org.springframework.security.web.authentication.
   *    AbstractAuthenticationProcessingFilter
   *        #attemptAuthentication(javax.servlet.http.HttpServletRequest, 
   *            javax.servlet.http.HttpServletResponse)
   */
  @Override
  public Authentication attemptAuthentication(final HttpServletRequest request,
      final HttpServletResponse response)
      throws AuthenticationException, IOException, ServletException {
    if (!HttpMethod.POST.name().equals(request.getMethod()) // NOPMD
        || !"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) { // NOPMD
      if (logger.isDebugEnabled()) {
        logger.debug("Authentication method not supported. Request method: " + request.getMethod());
      }
      throw new AuthenticationServiceException("Authentication method not supported");
    }

    final Gson gson = new Gson();
    final LoginRequest loginRequest = gson.fromJson(request.getReader(), LoginRequest.class);

    if (loginRequest == null || StringUtils.isBlank(loginRequest.getUsername()) // NOPMD
        || StringUtils.isBlank(loginRequest.getPassword())) { // NOPMD
      throw new AuthenticationServiceException("Username or Password not provided");
    }

    final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        loginRequest.getUsername(), loginRequest.getPassword()); // NOPMD

    return this.getAuthenticationManager().authenticate(token);
  }

  /**
   * @see org.springframework.security.web.authentication.
   *    AbstractAuthenticationProcessingFilter
   *        #successfulAuthentication(javax.servlet.http.HttpServletRequest, 
   *            javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain, 
   *                org.springframework.security.core.Authentication)
   */
  @Override
  public void successfulAuthentication(final HttpServletRequest request,
      final HttpServletResponse response, final FilterChain chain, final Authentication authResult)
      throws IOException, ServletException {
    successHandler.onAuthenticationSuccess(request, response, authResult);
  }

  /**
   * @see org.springframework.security.web.authentication.
   *    AbstractAuthenticationProcessingFilter
   *        #unsuccessfulAuthentication(javax.servlet.http.HttpServletRequest, 
   *            javax.servlet.http.HttpServletResponse, 
   *                org.springframework.security.core.AuthenticationException)
   */
  @Override
  public void unsuccessfulAuthentication(final HttpServletRequest request,
      final HttpServletResponse response, final AuthenticationException failed)
      throws IOException, ServletException {
    SecurityContextHolder.clearContext();
    failureHandler.onAuthenticationFailure(request, response, failed);
  }
}
