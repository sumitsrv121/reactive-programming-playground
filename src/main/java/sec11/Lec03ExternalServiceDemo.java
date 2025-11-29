package sec11;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.util.retry.Retry;
import sec11.client.ExternalServiceClient;
import sec11.client.ServerError;

import java.time.Duration;

public class Lec03ExternalServiceDemo {
    private static final Logger log = LoggerFactory.getLogger(Lec03ExternalServiceDemo.class);

    public static void main(String[] args) {
        retry();

        Util.sleep(60);
    }

    private static void repeat() {
        var client = new ExternalServiceClient();

        client.getCountry()
                .repeat()
                .takeUntil("canada"::equalsIgnoreCase)
                .subscribe(Util.subscriber());
    }

    private static void retry() {
        var client = new ExternalServiceClient();

        client.getProductName(2)
                .retryWhen(retryOnServerError())
                .subscribe(Util.subscriber());
    }

    private static Retry retryOnServerError() {
        return Retry.fixedDelay(20, Duration.ofMillis(1000))
                .filter(err -> err instanceof ServerError)
                .doBeforeRetry(retrySignal -> log.info("retrying {} and error {}", retrySignal.totalRetries(), retrySignal.failure().getMessage()));
    }
}
