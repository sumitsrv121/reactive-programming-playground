package com.srv.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lec02EmptyErrorTest {
    Mono<String> getUserName(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("invalid input"));
        };
    }

    @Test
    void userTest() {
        StepVerifier.create(getUserName(1))
                .expectNext("Sam")
                .expectComplete()
                .verify();
    }

    @Test
    void emptyTest() {
        StepVerifier.create(getUserName(2))
                .expectComplete()
                .verify();
    }

    @Test
    void errorTest1() {
        StepVerifier.create(getUserName(3))
                .expectError(RuntimeException.class)
                .verify();
    }

    @Test
    void errorTest2() {
        StepVerifier.create(getUserName(3))
                .expectError() // allow any error signal
                .verify();
    }

    @Test
    void errorTest3() {
        StepVerifier.create(getUserName(3))
                .expectErrorMessage("invalid input")
                .verify();
    }

    @Test
    void errorTest4() {
        StepVerifier.create(getUserName(3))
                .consumeErrorWith(ex -> {
                    Assertions.assertEquals(RuntimeException.class, ex.getClass());
                    Assertions.assertEquals("invalid input", ex.getMessage());
                })
                .verify();
    }
}
