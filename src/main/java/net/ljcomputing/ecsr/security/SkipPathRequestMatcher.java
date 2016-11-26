package net.ljcomputing.ecsr.security;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

/**
 * SkipPathRequestMatcher
 * 
 * @author James G. Willmore
 */
public class SkipPathRequestMatcher implements RequestMatcher {
  
  /** The matchers. */
  private OrRequestMatcher matchers; // NOPMD
  
  /** The processing matcher. */
  private RequestMatcher processingMatcher; // NOPMD

  /**
   * Instantiates a new skip path request matcher.
   *
   * @param pathsToSkip the paths to skip
   * @param processingPath the processing path
   */
  public SkipPathRequestMatcher(final List<String> pathsToSkip, 
      final String processingPath) {
    Assert.notNull(pathsToSkip);
    List<RequestMatcher> matchList = pathsToSkip.stream() // NOPMD
        .map(path -> new AntPathRequestMatcher(path)).collect(Collectors.toList());
    matchers = new OrRequestMatcher(matchList);
    processingMatcher = new AntPathRequestMatcher(processingPath);
  }

  /**
   * @see org.springframework.security.web.util.matcher.RequestMatcher
   *    #matches(javax.servlet.http.HttpServletRequest)
   */
  @Override
  public boolean matches(final HttpServletRequest request) {
    if (matchers.matches(request)) {
      return false; // NOPMD
    }

    return processingMatcher.matches(request) ? true : false;
  }
}
