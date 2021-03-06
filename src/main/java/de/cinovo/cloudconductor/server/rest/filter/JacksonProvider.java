package de.cinovo.cloudconductor.server.rest.filter;

/*
 * #%L
 * cloudconductor-server
 * %%
 * Copyright (C) 2013 Taimos GmbH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 * #L%
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import de.taimos.springcxfdaemon.MapperFactory;

/**
 * 
 * Copyright 2012 Cinovo AG<br>
 * <br>
 * 
 * @author hoegertn
 * 
 */
@Provider
@Consumes(MediaType.WILDCARD)
@Produces(MediaType.WILDCARD)
public class JacksonProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {
	
	private ObjectMapper mapper = MapperFactory.createDefault();
	
	
	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		if (mediaType.getSubtype().equals("json")) {
			return true;
		}
		return false;
	}
	
	@Override
	public long getSize(Object t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}
	
	@Override
	public void writeTo(Object t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
		entityStream.write(this.mapper.writeValueAsBytes(t));
	}
	
	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		if (mediaType.getSubtype().equals("json")) {
			return true;
		}
		return false;
	}
	
	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream) throws IOException, WebApplicationException {
		if (genericType.equals(null)) {
			return this.mapper.readValue(entityStream, type);
		}
		return this.mapper.readValue(entityStream, TypeFactory.defaultInstance().constructType(genericType));
	}
	
}
