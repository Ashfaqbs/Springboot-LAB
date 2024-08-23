package com.ashfaq.dev.libs.vertex;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class VertxExample extends AbstractVerticle {

    @Override
    public void start() {
        vertx.createHttpServer().requestHandler(req -> {
            req.response().end("Hello from Vert.x!");
        }).listen(1111);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new VertxExample());
    }
}
