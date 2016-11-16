/**
           Copyright 2015-2016, James G. Willmore

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

package net.ljcomputing.ecsr.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * ECSR web MVC configuration class.
 * 
 * @author James G. Willmore
 *
 */
@Configuration
@EnableWebMvc
public class EcsrMvcConfiguration extends WebMvcConfigurerAdapter {
  /**
   * The Constant CLASSPATH_RESOURCE_LOCATIONS - defines where static
   * resources are located.
   */
  private static final String[] RES_LOCATIONS = { "classpath:/META-INF/resources/",
      "classpath:/resources/", "classpath:/static/", "classpath:/public/" };

  /**
   * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
   *    #addResourceHandlers(
   *      org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
   */
  @Override
  public void addResourceHandlers(final ResourceHandlerRegistry registry) {
    if (!registry.hasMappingForPattern("/webjars/**")) {
      registry.addResourceHandler("/webjars/**") //NOPMD
          .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    if (!registry.hasMappingForPattern("/**")) {
      registry.addResourceHandler("/**").addResourceLocations(RES_LOCATIONS); //NOPMD
    }
  }

  /**
   * Jsp view resolver.
   *
   * @return the view resolver
   */
  @Bean
  public ViewResolver jspViewResolver() {
    final InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setViewClass(JstlView.class);
    resolver.setPrefix("/WEB-INF/pages/");
    resolver.setSuffix(".jsp");
    return resolver;
  }

  /**
   * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
   *    #configureDefaultServletHandling(
   *        org.springframework.web.servlet.config
   *        .annotation.DefaultServletHandlerConfigurer)
   */
  @Override
  public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

  /**
   * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
   * #configureContentNegotiation(
   *     org.springframework.web.servlet.config
   *     .annotation.ContentNegotiationConfigurer)
   */
  @Override
  public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
    configurer
      .favorParameter(true)
      .ignoreAcceptHeader(true)
      .defaultContentType(MediaType.APPLICATION_JSON_UTF8)
      .mediaType("xml", MediaType.APPLICATION_XML);
  }

  /**
   * Content negotiating view resolver.
   *
   * @param manager the manager
   * @return the view resolver
   */
  @Bean
  public ViewResolver contentNegotiatingViewResolver(final ContentNegotiationManager manager) {
    final List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
    resolvers.add(jspViewResolver());

    final ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
    resolver.setContentNegotiationManager(manager);

    resolver.setViewResolvers(resolvers);

    return resolver;
  }
}
