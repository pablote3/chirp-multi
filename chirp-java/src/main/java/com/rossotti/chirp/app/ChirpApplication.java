package com.rossotti.chirp.app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.rossotti.chirp.app.providers.NoSuchEntityExceptionMapper;
import com.rossotti.chirp.app.resources.HelloResource;
import com.rossotti.chirp.app.resources.UserResource;
import com.rossotti.chirp.store.exceptions.DuplicateEntityException;

public class ChirpApplication extends Application {

	protected final Set<Class<?>> classes = new HashSet<>();
    private Set<Object> singletons = new HashSet<>();
    protected final Map<String, Object> properties = new HashMap<>();

    public ChirpApplication() {
    	registerClasses();
    }
    
    private void registerClasses() {
    	
    	// Resources
    	classes.add(HelloResource.class);
    	classes.add(UserResource.class);
    	
    	// Exception Mappers
    	classes.add(DuplicateEntityException.class);
    	classes.add(NoSuchEntityExceptionMapper.class);
    }
    
    @Override
    public Set<Class<?>> getClasses() {
      return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
    
    @Override
    public Map<String, Object> getProperties() {
      return properties;
    }
    
    public void setProperty(String name, Object value) {
        properties.put(name, value);
    }
}