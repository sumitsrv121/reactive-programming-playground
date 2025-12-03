package sec13;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class Lec02ContextAppendUpdate {
    private static final Logger log = LoggerFactory.getLogger(Lec02ContextAppendUpdate.class);

    public static void main(String[] args) {
        update();
    }

    private static void update() {
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.put("authUser", ctx.get("authUser").toString().toUpperCase()))
                .contextWrite(Context.of("a", "b", "c", "d"))
                .contextWrite(Context.of("authUser", "sam"))
                .subscribe(Util.subscriber());
    }

    private static void delete() {
        // delete "a"(key) from context map
        getWelcomeMessage()
                .contextWrite(ctx -> ctx.delete("a"))
                .contextWrite(Context.of("a", "b", "c", "d"))
                .contextWrite(Context.of("authUser", "sam"))
                .subscribe(Util.subscriber());
    }

    private static void override() {
        // override context to {"authUser" = "mike"}
        getWelcomeMessage()
                .contextWrite(ctx -> Context.of("authUser", "mike"))
                .contextWrite(Context.of("a", "b", "c", "d"))
                .contextWrite(Context.of("authUser", "sam"))
                .subscribe(Util.subscriber());
    }

    private static void append() {
        getWelcomeMessage()
                .contextWrite(Context.of("a", "b").put("c", "d").put("e", "f"))
                .contextWrite(Context.of("authUser", "sam"))
                .subscribe(Util.subscriber());
    }

    private static Mono<String> getWelcomeMessage() {
        return Mono.deferContextual(ctx -> {
            log.info("{}", ctx);
            if (ctx.hasKey("authUser")) {
                return Mono.fromSupplier(() -> "Welcome %s".formatted(ctx.get("authUser").toString()));
            }
            return Mono.error(new RuntimeException("Unauthorized access"));
        });
    }
}
