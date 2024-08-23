package com.ashfaq.dev.libs.akka;
import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

//Akka is a toolkit for building highly concurrent, distributed, and resilient message-driven applications on the JVM. It's based on the actor model.

public class AkkaExample {
    static class HelloActor extends AbstractActor {
        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(String.class, s -> {
                        System.out.println("Hello " + s);
                    })
                    .build();
        }
    }

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("hello-akka");
        ActorRef helloActor = system.actorOf(Props.create(HelloActor.class), "helloActor");

        helloActor.tell("World", ActorRef.noSender());

        system.terminate();
    }
}
