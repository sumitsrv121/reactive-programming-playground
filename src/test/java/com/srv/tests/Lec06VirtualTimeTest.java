package com.srv.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Lec06VirtualTimeTest {
    private Flux<Integer> getItems() {
        return Flux.range(1, 5)
                .delayElements(Duration.ofSeconds(10));
    }

    @Test
    void testDelayedItems() {
        // test will complete in 50 seconds,
        // as producer wait 10 seconds after emitting each item
        StepVerifier.create(getItems())
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();
    }

    @Test
    void virtualTimerTest1() {
        // thenAwait will simulate delay of 51 seconds but will not wait for 51 seconds.
        // will be executed, and will have data what publisher would have emitted till 51
        // seconds.
        StepVerifier.withVirtualTime(this::getItems)
                .thenAwait(Duration.ofSeconds(51))
                .expectNext(1, 2, 3, 4, 5)
                .expectComplete()
                .verify();

    }

    @Test
    void virtualTimerTest2() {
        // till 9 seconds no items and after 51 seconds [1, 2, 3, 4, 5]
        StepVerifier.withVirtualTime(this::getItems)
                .expectSubscription()
                .expectNoEvent(Duration.ofSeconds(9))
                .thenAwait(Duration.ofSeconds(1))
                .expectNext(1)
                .thenAwait(Duration.ofSeconds(41))
                .expectNext(2, 3, 4, 5)
                .expectComplete()
                .verify();

    }
}
