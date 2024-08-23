package com.ashfaq.dev.libs.quarkus;

import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/hello")
public class QuarkusExample {

    @GET
    @Produces
    public String hello() {
        return "Hello, Quarkus!";
    }
}
