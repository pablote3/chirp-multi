package com.rossotti.chirp.app;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.rossotti.chirp.resources.HelloResource;

@ApplicationPath("/")
public class ChirpApplication extends Application {

	protected final Set<Class<?>> classes = new HashSet<>();
    private Set<Object> singletons = new HashSet<>();
    protected final Map<String, Object> properties = new HashMap<>();

    public ChirpApplication() {
    	registerClasses();
    }
    
    private void registerClasses() {
    	classes.add(HelloResource.class);
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