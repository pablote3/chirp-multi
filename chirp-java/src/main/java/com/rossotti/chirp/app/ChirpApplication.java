package com.rossotti.chirp.app;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.rossotti.chirp.resources.HelloResource;

@ApplicationPath("/")
public class ChirpApplication extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public ChirpApplication() {
        singletons.add(new HelloResource());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}