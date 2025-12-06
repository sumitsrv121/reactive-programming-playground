package com.srv.tests;

import common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.publisher.TestPublisher;

import java.util.function.UnaryOperator;

public class Lec09PublisherTest {
    private UnaryOperator<Flux<String>> processor() {
        return flux -> flux
                .filter(s -> s.length() > 1)
                .map(String::toUpperCase)
                .map(s -> s + ":" + s.length());
    }

    @Test
    void testPublisher1() {
        TestPublisher<String> publisher = TestPublisher.<String>create();
        Flux<String> flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("Hi", "Hello"))
                .expectNext("HI:2")
                .expectNext("HELLO:5")
                .expectComplete()
                .verify();


    }

    @Test
    void testPublisher2() {
        TestPublisher<String> publisher = TestPublisher.<String>create();
        Flux<String> flux = publisher.flux();

        StepVerifier.create(flux.transform(processor()))
                .then(() -> publisher.emit("a", "b"))
                .expectComplete()
                .verify();


    }
}
