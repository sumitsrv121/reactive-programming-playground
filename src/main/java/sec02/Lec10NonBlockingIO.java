package sec02;

import common.ExternalServiceClient;
import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lec10NonBlockingIO {
    private static final Logger log = LoggerFactory.getLogger(Lec10NonBlockingIO.class);
    public static void main(String[] args) {
        var client = new ExternalServiceClient();
        log.info("Starting...");
        for (int i = 1; i <= 100; i++) {
            client.getProductName(i)
                    .subscribe(Util.subscriber());
        }
        Util.sleep(2);
    }
}
