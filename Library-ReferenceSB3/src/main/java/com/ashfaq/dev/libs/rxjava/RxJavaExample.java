package com.ashfaq.dev.libs.rxjava;

import io.reactivex.rxjava3.core.Observable;

public class RxJavaExample {
//	RxJava is a library for composing asynchronous and event-based programs using observable sequences for the JVM. It is heavily used for reactive programming.
    public static void main(String[] args) {
        Observable<String> observable = Observable.just("Hello", "Reactive", "World");

        observable.subscribe(
            item -> System.out.println(item),
            error -> System.err.println("Error: " + error),
            () -> System.out.println("Done")
        );
    }
}
