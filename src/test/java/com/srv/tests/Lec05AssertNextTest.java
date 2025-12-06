package com.srv.tests;

import common.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

public class Lec05AssertNextTest {
    record Book(int id, String author, String title) {

    }

    private Flux<Book> getBooks() {
        return Flux.range(1, 3)
                .map(i -> new Book(i, Util.faker().book().author(), Util.faker().book().title()));
    }

    @Test
    void assertNextTest() {
        StepVerifier.create(getBooks())
                .assertNext(book -> Assertions.assertEquals(1, book.id))
                .thenConsumeWhile(book -> Objects.nonNull(book)
                        && Objects.nonNull(book.title) && book.id >= 2 && book.id <= 3)
                .expectComplete()
                .verify();
    }

    @Test
    void collectAllAndTest() {
        StepVerifier.create(getBooks().collectList())
                .assertNext(bookList -> Assertions.assertEquals(3, bookList.size()))
                .expectComplete()
                .verify();
    }
}
