package sec03;

import common.ExternalServiceClient;
import common.Util;

public class Lec08NonBlockingStreamingMessages {
    public static void main(String[] args) {
        ExternalServiceClient client = new ExternalServiceClient();
        client.getNames()
                .subscribe(Util.subscriber("sub1"));

        client.getNames()
                .subscribe(Util.subscriber("sub2"));

        Util.sleep(6);
    }
}
