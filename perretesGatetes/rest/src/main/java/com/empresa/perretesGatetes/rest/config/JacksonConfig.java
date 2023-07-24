package com.empresa.perretesGatetes.rest.config;

import java.io.IOException;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Serializer que nos permite configurar la transformación de valores null en otros valores por defecto.
 */
@Configuration
public class JacksonConfig extends JsonSerializer<Object> {
    
	@Override
    public void serialize(Object object, JsonGenerator jsonGenerator, 
    		SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString("");
    }
}
