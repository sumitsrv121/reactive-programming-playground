package sec13;

import common.Util;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec01Context {

    public static void main(String[] args) {
        getWelcomeMessage()
                .contextWrite(Context.of("authUser", "sam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            if (ctx.hasKey("authUser")) {
                return Mono.fromSupplier(() -> "Welcome %s".formatted(ctx.get("authUser").toString()));
            }
            return Mono.error(new RuntimeException("Unauthorized access"));
        });
    }
}
