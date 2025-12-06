package com.srv.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lec03FluxTest {
    private Flux<Integer> getItems() {
        return Flux.just(1, 2, 3)
                .log();
    }

    @Test
    void fluxTest1() {
        // request only 1 item, in create we have passed 1.
        // Expect the value and then cancel.
        StepVerifier.create(getItems(), 1)
                .expectNext(1)
                .thenCancel()
                .verify();

    }

    @Test
    void fluxTest2() {
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3)
                .expectComplete()
                .verify();
    }

    @Test
    void fluxTest3() {
        StepVerifier.create(getItems())
                .expectNext(1)
                .expectNext(2)
                .expectNext(3)
                .expectComplete()
                .verify();
    }
}
