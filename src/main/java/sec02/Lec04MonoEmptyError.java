package sec02;

import common.Util;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyError {
    public static void main(String[] args) {
        getUserName(2)
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getUserName(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("Sam");
            case 2 -> Mono.empty();
            default ->
                    Mono.error(new IllegalArgumentException("Invalid input"));
        };
    }
}
