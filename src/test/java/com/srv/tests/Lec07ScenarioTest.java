package com.srv.tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.test.StepVerifierOptions;

public class Lec07ScenarioTest {
    private Flux<Integer> getItems() {
        return Flux.range(1, 3);
    }

    @Test
    void scenarioNameTest() {
        StepVerifierOptions options = StepVerifierOptions.create().scenarioName("1 to 3 items test");
        StepVerifier.create(getItems(), options)
                .expectNext(1)
                .as("Expect 1")
                .expectNext(2, 3)
                .as("Another step expects 2 and 3")
                .expectComplete()
                .verify();
    }
}
