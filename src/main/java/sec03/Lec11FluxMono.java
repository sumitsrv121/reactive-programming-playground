package sec03;

import common.Util;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

public class Lec11FluxMono {
    public static void main(String[] args) {
        fluxToMono();
        monoToFlux();

    }

    private static void fluxToMono() {
        var flux = Flux.range(1, 10);
        flux.next()
                .subscribe(Util.subscriber());
        // or
        Mono.from(flux)
                .subscribe(Util.subscriber());
    }

    private static void monoToFlux() {
        var mono = getUserName(1);
        Flux<String> flux = Flux.from(mono);
        save(flux);
    }

    private static Mono<String> getUserName(int userId) {
        return switch (userId) {
            case 1 -> Mono.just("sam");
            case 2 -> Mono.empty();
            default -> Mono.error(new RuntimeException("Invalid input"));
        };
    }

    private static void save(Flux<String> flux) {
        // save the data
        flux.subscribe(Util.subscriber());
    }
}
