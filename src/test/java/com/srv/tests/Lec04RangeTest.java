package com.srv.tests;

import common.Util;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec04RangeTest {
    private Flux<Integer> getItems() {
        return Flux.range(1, 50)
                .log();
    }

    private Flux<Integer> getRandomItems() {
        return Flux.range(1, 50)
                .map(i -> Util.faker().random().nextInt(1, 100))
                .log();
    }

    @Test
    void rangeTest1() {
        // validate first 3 items and remaining 47 items onNext will be called.
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectNextCount(47)
                .expectComplete()
                .verify();
    }

    @Test
    void rangeTest2() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectNextCount(22)
                .expectNext(26, 27, 28)
                .expectNextCount(22)
                .expectComplete()
                .verify();
    }


    @Test
    void rangeTest3() {
        // expectNextMatches will validate the first item
        // and then remaining 49 times onNext will be called.
        StepVerifier.create(getRandomItems())
                .expectNextMatches(value -> value >= 1 && value <= 100)
                .expectNextCount(49)
                .expectComplete()
                .verify();
    }

    @Test
    void rangeTest4() {
        // consume the items till the condition is met.
        // we have emitted 50 items in the specified range
        // so all the items will be consumed.
        StepVerifier.create(getRandomItems())
                .thenConsumeWhile(value -> value >= 1 && value <= 100)
                .expectComplete()
                .verify();
    }
}
