package sec07;

import common.Util;
import sec07.client.ExternalServiceClient;

public class Lec06EventLoopIssueFixed {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 0; i < 5; i++) {
            client.getProductName(i)
                    .map(Lec06EventLoopIssueFixed::process)
                    .subscribe(Util.subscriber());
        }
        Util.sleep(20);
    }

    private static String process(String input) {
        Util.sleep(1);
        return "processed-" + input;
    }
}
