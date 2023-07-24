package com.empresa.perretesGatetes.web.config;

import java.io.IOException;

import javax.servlet.ServletContextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${app.path.static}")
	private String pathStatic;
	
	@Autowired
	private ServletContextListenerConfig servletConfig;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
        	.addResourceHandler("/**")
	        .addResourceLocations("classpath:"+pathStatic)
	        .resourceChain(true)
	        .addResolver(new PathResourceResolver() {
	            @Override
	            protected Resource getResource(String resourcePath, Resource location) throws IOException {
	                Resource requestedResource = location.createRelative(resourcePath);
	                return requestedResource.exists() && requestedResource.isReadable()? requestedResource : new ClassPathResource(pathStatic + "index.html");
	            }
	        });
	}
	
	/**
	 * Log filter built in in Spring Boot to logging request HTTP.
	 * @return
	 */
	@Bean
    CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(false);
        filter.setAfterMessagePrefix("[Client] REQUEST DATA : ");
        return filter;
    }
	
	/**
	 * Define listener when context is destroyed to prevent possible memory leak with registered JDBC drivers during redeploys.
	 * @return
	 */
	@Bean
	ServletContextListener listener() {
		return this.servletConfig;
	}
}
