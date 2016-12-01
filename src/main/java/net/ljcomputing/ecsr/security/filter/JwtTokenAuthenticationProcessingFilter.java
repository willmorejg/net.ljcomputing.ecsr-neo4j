package net.ljcomputing.ecsr.security.filter;

/**
 * Validate JWT Token.
 * 
 * @author James G. Willmore
 */
public class JwtTokenAuthenticationProcessingFilter {//extends AbstractAuthenticationProcessingFilter {
//
//  /** The failure handler. */
//  private final AuthenticationFailureHandler failureHandler; // NOPMD
//
//  /**
//   * Instantiates a new jwt token authentication processing filter.
//   *
//   * @param failureHandler the failure handler
//   * @param matcher the matcher
//   */
//  @Autowired
//  public JwtTokenAuthenticationProcessingFilter(final AuthenticationFailureHandler failureHandler,
//      final RequestMatcher matcher) {
//    super(matcher);
//    this.failureHandler = failureHandler;
//  }
//
//  /**
//   * @see org.springframework.security.web.authentication
//   *    .AbstractAuthenticationProcessingFilter
//   *        #attemptAuthentication(javax.servlet.http.HttpServletRequest, 
//   *            javax.servlet.http.HttpServletResponse)
//   */
//  @Override
//  public Authentication attemptAuthentication(final HttpServletRequest request,
//      final HttpServletResponse response)
//      throws AuthenticationException, IOException, ServletException {
//    final String tokenPayload = request.getHeader(WebSecurityConfiguration.JWT_TOKEN_HEADER);
//    final RawAccessJwtToken token = 
//        new RawAccessJwtToken(RawAccessJwtToken.extractHeader(tokenPayload));
//    return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
//  }
//
//  /**
//   * @see org.springframework.security.web.authentication.
//   *    AbstractAuthenticationProcessingFilter
//   *        #successfulAuthentication(javax.servlet.http.HttpServletRequest, 
//   *            javax.servlet.http.HttpServletResponse, 
//   *                javax.servlet.FilterChain, 
//   *                    org.springframework.security.core.Authentication)
//   */
//  @Override
//  public void successfulAuthentication(final HttpServletRequest request,
//      final HttpServletResponse response, final FilterChain chain, final Authentication authResult)
//      throws IOException, ServletException {
//    final SecurityContext context = SecurityContextHolder.createEmptyContext();
//    context.setAuthentication(authResult); // NOPMD
//    SecurityContextHolder.setContext(context);
//    chain.doFilter(request, response);
//  }
//
//  /**
//   * @see org.springframework.security.web.authentication
//   *    .AbstractAuthenticationProcessingFilter
//   *        #unsuccessfulAuthentication(javax.servlet.http.HttpServletRequest, 
//   *            javax.servlet.http.HttpServletResponse, 
//   *                org.springframework.security.core.AuthenticationException)
//   */
//  @Override
//  public void unsuccessfulAuthentication(final HttpServletRequest request,
//      final HttpServletResponse response, final AuthenticationException failed)
//      throws IOException, ServletException {
//    SecurityContextHolder.clearContext();
//    failureHandler.onAuthenticationFailure(request, response, failed);
//  }
}
