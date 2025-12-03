package sec13;

import common.Util;
import reactor.util.context.Context;
import sec13.client.ExternalServiceClient;

public class Lec04ContextRateLimiterDemo {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        for (int i = 0; i < 20; i++) {
            client.getBook()
                    .contextWrite(Context.of("user", "sam"))
                    .subscribe(Util.subscriber());
            Util.sleep(1);
        }

        Util.sleep(5);
    }
}
