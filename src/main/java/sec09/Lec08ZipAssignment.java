package sec09;

import common.Util;
import sec09.assignment.ExternalServiceClient;

public class Lec08ZipAssignment {
    public static void main(String[] args) {
        var client = new ExternalServiceClient();

        for (int i = 1; i <= 10; i++) {
            client.getProductDetails(i)
                    .subscribe(Util.subscriber());
        }

        Util.sleep(20);
    }
}
