package com.empresa.perretesGatetes.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class RestConfig implements WebMvcConfigurer {
	
	@Value("${app.rest.base-path}")
	private String basePath;
	
	@Autowired
	private JacksonConfig jacksonConfig;
	
	@Override
	/*
	 * Configuración de una ruta base para API Rest configurada en app.rest.base-path
	 */
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix(basePath, HandlerTypePredicate.forAnnotation(RestController.class));
    }

    /*
     * Configuración para que los tipos String que tengan un null se devuelvan como cadena vacía.
     */
	@Bean
	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(ObjectMapper objectMapper){	    
	    MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
	    objectMapper.getSerializerProvider().setNullValueSerializer(jacksonConfig);
	    mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
	    return mappingJackson2HttpMessageConverter;
	}
}
