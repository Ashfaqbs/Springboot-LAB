package com.ashfaq.dev.libs.micronaut;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.runtime.Micronaut;

@Controller("/hello")
public class MicronautExample {

    @Get("/")
    public String index() {
        return "Hello, Micronaut!";
    }

    public static void main(String[] args) {
        Micronaut.run(MicronautExample.class);
    }
}
